package com.academia.backend.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.academia.backend.entity.Categoria;
import com.academia.backend.repository.CategoriasRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriasService {
	
	private final CategoriasRepository categoriaRepository;
	
	

	public ResponseEntity<List<Categoria>> getAllCategories(){
		List<Categoria> Categorias = this.categoriaRepository.findAll();
		return ResponseEntity.ok(Categorias);
	}
	
	public ResponseEntity<Categoria> getCategoryById(Long CategoriaId) {
		Optional<Categoria> Categoria = this.categoriaRepository.findById(CategoriaId);
		
		
		if(Categoria.isPresent()) {
			return ResponseEntity.ok(Categoria.get());
		}
		else {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Categoria> createCategory(Categoria Categoria) {
		Categoria CategoriaCreated = this.categoriaRepository.save(Categoria);
		return new ResponseEntity<Categoria>(CategoriaCreated,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<Categoria> updateCategory(Long CategoriaId,Categoria CategoriaUpdate){
		Optional<Categoria> Categoria = this.categoriaRepository.findById(CategoriaId);
		
		if(Categoria.isPresent()) {
			CategoriaUpdate.setId(CategoriaId);
			Categoria CategoriaActualizado = this.categoriaRepository.save(CategoriaUpdate);
			return ResponseEntity.ok(CategoriaActualizado);
		}
		else {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	public ResponseEntity<Categoria> updateCategoryPatch(Long CategoriaId,Map<String, Object> productUpdate){
		Optional<Categoria> CategoriaOpcional = this.categoriaRepository.findById(CategoriaId);
		
		if(CategoriaOpcional.isPresent()) {
			
			Categoria Categoria = CategoriaOpcional.get();
			
			this.leerCamposAactualizar(productUpdate,Categoria);

	        Categoria = this.categoriaRepository.save(Categoria);
	        return ResponseEntity.ok(Categoria);
		    
		}
		else {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	} 
	
	
	public ResponseEntity<Object> deleteCategory(Long CategoriaId) {
		Optional<Categoria> CategoriaOpcional = this.categoriaRepository.findById(CategoriaId);
		
		if(CategoriaOpcional.isPresent()) {
			
			Categoria Categoria = CategoriaOpcional.get();
			Categoria.setEstatus(false);
			
			this.categoriaRepository.save(Categoria);
			return new ResponseEntity<Object>(HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	private void leerCamposAactualizar(Map<String, Object> categoryUpdate, Categoria Categoria) {

        for (Map.Entry<String, Object> entry : categoryUpdate.entrySet()) {
	            String campo = entry.getKey();
	            Object valor = entry.getValue();

	            try {
	                Field field = Categoria.class.getDeclaredField(campo);
	                field.setAccessible(true);
	                field.set(Categoria, valor);
	            } catch (NoSuchFieldException | IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	}

}
