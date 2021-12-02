package com.pichincha.caso.service;

import java.util.List;

import com.pichincha.caso.model.dto.ReportNumTransDTO;
import com.pichincha.caso.model.dto.ReportMontoVentaDTO;
import com.pichincha.caso.to.OrderTo;

public interface OrderService {

	void execute(OrderTo orderTo);

	List<ReportNumTransDTO> reporte1();

	List<ReportMontoVentaDTO> reporte2();

}
