package com.academia.backend.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.academia.backend.entity.Producto;
import com.academia.backend.repository.ProductosRepository;
import com.academia.backend.web.model.ProductoModel;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductosService {
	
	private final ProductosRepository productoRepository;
	
	public ResponseEntity<List<Producto>> getAllProducts(){
		List<Producto> productos = this.productoRepository.findAll();
		return ResponseEntity.ok(productos);
	}
	
	public ResponseEntity<Producto> getProductById(Long productoId) {
		Optional<Producto> producto = this.productoRepository.findById(productoId);
		
		
		if(producto.isPresent()) {
			return ResponseEntity.ok(producto.get());
		}
		else {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Producto> createProduct(Producto producto) {
		Producto productoCreated = this.productoRepository.save(producto);
		return new ResponseEntity<Producto>(productoCreated,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<Producto> updateProduct(Long productoId,Producto productoUpdate){
		Optional<Producto> producto = this.productoRepository.findById(productoId);
		
		if(producto.isPresent()) {
			productoUpdate.setId(productoId);
			Producto productoActualizado = this.productoRepository.save(productoUpdate);
			return ResponseEntity.ok(productoActualizado);
		}
		else {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	public ResponseEntity<Producto> updateProductPatch(Long productoId,Map<String, Object> productUpdate){
		Optional<Producto> productoOpcional = this.productoRepository.findById(productoId);
		
		if(productoOpcional.isPresent()) {
			
			Producto producto = productoOpcional.get();
			
			this.leerCamposAactualizar(productUpdate,producto);

	        producto = this.productoRepository.save(producto);
	        return ResponseEntity.ok(producto);
		    
		}
		else {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	} 
	
	
	public ResponseEntity<Object> deleteProduct(Long productoId) {
		Optional<Producto> productoOpcional = this.productoRepository.findById(productoId);
		
		if(productoOpcional.isPresent()) {
			
			Producto producto = productoOpcional.get();
			producto.setEstatus(false);
			
			this.productoRepository.save(producto);
			return new ResponseEntity<Object>(HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	private void leerCamposAactualizar(Map<String, Object> productUpdate, Producto producto) {

        for (Map.Entry<String, Object> entry : productUpdate.entrySet()) {
	            String campo = entry.getKey();
	            Object valor = entry.getValue();

	            try {
	                Field field = Producto.class.getDeclaredField(campo);
	                field.setAccessible(true);
	                field.set(producto, valor);
	            } catch (NoSuchFieldException | IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	}

}
