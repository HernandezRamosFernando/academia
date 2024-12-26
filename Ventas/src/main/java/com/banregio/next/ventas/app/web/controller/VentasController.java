package com.banregio.next.ventas.app.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banregio.next.ventas.remoto.dto.ProductoDTO;
import com.banregio.next.ventas.remoto.service.ProductosService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/ventas")
@AllArgsConstructor
public class VentasController {
	
	private final ProductosService productosService;
	
	@Operation(description = "Regresa una lista de todos los productos activos")
	@GetMapping("/productos")
	public ResponseEntity<List<ProductoDTO>> getAllProducts(){
		return this.productosService.getAllProducts();
	}

}
