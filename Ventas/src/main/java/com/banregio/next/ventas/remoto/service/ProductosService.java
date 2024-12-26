package com.banregio.next.ventas.remoto.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banregio.next.ventas.remoto.dto.ProductoDTO;
import com.banregio.next.ventas.remoto.repository.ProductosRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductosService {
	
	private final ProductosRepository productosRepository;
	
	
	public ResponseEntity<List<ProductoDTO>> getAllProducts(){
		return ResponseEntity.ok(this.productosRepository.getAllProducts());
	}

}
