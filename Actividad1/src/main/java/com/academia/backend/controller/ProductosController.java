package com.academia.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.backend.entity.Producto;
import com.academia.backend.service.ProductosService;
import com.academia.backend.web.model.ProductoModel;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "v1/productos")
@AllArgsConstructor
public class ProductosController {
	
	private ProductosService productosService;
	
	
	
	@Operation(description = "Regresa una lista de todos los productos activos")
	@GetMapping
	private ResponseEntity<List<Producto>> getAllProducts() {
		return this.productosService.getAllProducts();
	}
	
	
	@Operation(description = "Regresa un producto enviando el ID")
	@GetMapping("/{id}")
	private ResponseEntity<Producto> getProduct(@PathVariable Long id) {
		return this.productosService.getProductById(id);
	}
	
	@Operation(description = "Crea un nuevo producto")
	@PostMapping
	private ResponseEntity<Producto> createProduct(@RequestBody ProductoModel productoModel) {
		return this.productosService.createProduct(productoModel.getProducto());
	}
	
	
	@Operation(description = "Actualiza un producto")
	@PutMapping("/{id}")
	private ResponseEntity<Producto> updateProduct(@PathVariable Long id,@RequestBody ProductoModel productoModel){
		return this.productosService.updateProduct(id, productoModel.getProducto());
	}
	
	@Operation(description = "Actualiza parcialmente un producto")
	@PatchMapping("/{id}")
	private ResponseEntity<Producto> patchProduct(@PathVariable Long id,@RequestBody Map<String, Object> producto){
		return this.productosService.updateProductPatch(id, producto);
	}
	
	
	@Operation(description = "Elimina un producto")
	@DeleteMapping("/{id}")
	private ResponseEntity<Object> deleteProduct(@PathVariable Long id){
		return this.productosService.deleteProduct(id);
	}

}
