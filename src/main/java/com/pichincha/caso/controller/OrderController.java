package com.pichincha.caso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.dto.ReportNumTransDTO;
import com.pichincha.caso.model.dto.ReportMontoVentaDTO;
import com.pichincha.caso.service.OrderService;
import com.pichincha.caso.to.OrderTo;

@RestController
@RequestMapping("/inventory/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/execute")
	public ResponseEntity<?> execute(@RequestBody OrderTo orderTo) {
		try {
			orderService.execute(orderTo);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/reporte1")
	public ResponseEntity<?> reporte1() {
		try {
			List<ReportNumTransDTO> resultado = orderService.reporte1();
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/reporte2")
	public ResponseEntity<?> reporte2() {
		try {
			List<ReportMontoVentaDTO> resultado = orderService.reporte2();
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}