package com.pichincha.caso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.caso.model.dto.ReportMontoVentaDTO;

@Repository
public interface ReportMontoVentaRepository extends JpaRepository<ReportMontoVentaDTO, Long> {
	@Query(value = "select row_number() over(order by name_store) as id , x.* from ("
			+ "select sum(od.quantity * p.price) quantity, s.name name_store, p.name name_product "
			+ "from  order_detail od, store s, product p "
			+ "where od.id_store = s.id_store and od.id_product = p.id_product "
			+ "group by s.name, p.name) x;", nativeQuery = true)
	List<ReportMontoVentaDTO> reportMontoVenta();
}