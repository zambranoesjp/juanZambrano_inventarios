package com.pichincha.caso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.caso.model.dto.ReportNumTransDTO;

@Repository
public interface Report1Repository extends JpaRepository<ReportNumTransDTO, Long> {
	@Query(value = "select row_number() over(order by name_store) as id, x.* from ("
			+ "select count(1) quantity, date(o.date_order) date_order, s.name name_store "
			+ "from  orders o, order_detail od, store s "
			+ "where o.id_order = od.id_order  and od.id_store = s.id_store "
			+ "group by date(o.date_order), s.name) x", nativeQuery = true)
	List<ReportNumTransDTO> reporte1();
}