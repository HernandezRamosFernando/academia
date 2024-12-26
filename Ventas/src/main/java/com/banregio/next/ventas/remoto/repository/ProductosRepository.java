package com.banregio.next.ventas.remoto.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.banregio.next.ventas.remoto.dto.ProductoDTO;

@FeignClient(name = "productos-service", url = "${external.uri}")
public interface ProductosRepository {
	
	@GetMapping("/productos")
	List<ProductoDTO> getAllProducts();

}
