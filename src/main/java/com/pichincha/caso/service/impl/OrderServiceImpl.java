package com.pichincha.caso.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Client;
import com.pichincha.caso.model.Order;
import com.pichincha.caso.model.OrderDetail;
import com.pichincha.caso.model.Product;
import com.pichincha.caso.model.Store;
import com.pichincha.caso.model.dto.ReportNumTransDTO;
import com.pichincha.caso.model.dto.ReportMontoVentaDTO;
import com.pichincha.caso.repository.OrderDetailRepository;
import com.pichincha.caso.repository.OrderRepository;
import com.pichincha.caso.repository.ProductRepository;
import com.pichincha.caso.repository.Report1Repository;
import com.pichincha.caso.repository.Report2Repository;
import com.pichincha.caso.service.CompensationService;
import com.pichincha.caso.service.OrderService;
import com.pichincha.caso.to.OrderDetailTO;
import com.pichincha.caso.to.OrderTo;

@Service
@Qualifier("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private CompensationService compensationService;
	
	@Autowired
	private Report1Repository report1Repository;
	
	@Autowired
	private Report2Repository report2Repository;

	@Override
	@Transactional
	public void execute(OrderTo orderTo) throws ServiceException {

		Client client = new Client(orderTo.getIdClient());

		Order order = new Order();
		order.setDateOrder(new Date());
		order.setClient(client);

		order = orderRepository.save(order);

		for (OrderDetailTO detail : orderTo.getDetails()) {
			details(order, detail);
		}
	}
	
	
	@Override
	@Transactional(readOnly = true )
	public List<ReportNumTransDTO> reporte1(){
		return report1Repository.reporte1();
	}
	
	@Override
	@Transactional(readOnly = true )
	public List<ReportMontoVentaDTO> reporte2(){
		return report2Repository.reporte2();
	}

	private void details(Order order, OrderDetailTO detail) throws ServiceException {
		Store store = new Store(detail.getIdStore());

		Product product = productRepository.findById(detail.getIdProduct()).get();

		int cant = product.getStock().intValue();

		cant = cant - detail.getQuantity().intValue();

		if (cant < 0) {

			cant = cant * (-1);

			if (cant > 10) {
				throw new ServiceException("Units not available (>10)");
			} else if (cant > 5 && cant < 10) {
				compensationService.function10(product);
			} else if (cant < 5) {
				cant = -(cant) + 5;
				compensationService.function10(product);
			}
		} else {
			product.setStock(cant);
		}

		product = productRepository.save(product);

		OrderDetail ordenDetail = new OrderDetail(order, store, product, detail.getQuantity());

		orderDetailRepository.save(ordenDetail);
	}
}