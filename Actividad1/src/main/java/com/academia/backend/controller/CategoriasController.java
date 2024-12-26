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

import com.academia.backend.entity.Categoria;
import com.academia.backend.entity.Categoria;
import com.academia.backend.service.CategoriasService;
import com.academia.backend.web.model.CategoriaModel;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "v1/categorias")
@AllArgsConstructor
public class CategoriasController {
	
	
	private final CategoriasService categoriasService;
	
	@Operation(description = "Regresa una lista de todas las categorias")
	@GetMapping
	private ResponseEntity<List<Categoria>> getAllCategories() {
		return this.categoriasService.getAllCategories();
	}
	
	@Operation(description = "Regresa una categoria enviando el ID")
	@GetMapping("/{id}")
	private ResponseEntity<Categoria> getCategory(@PathVariable Long id) {
		return this.categoriasService.getCategoryById(id);
	}
	
	@Operation(description = "Crea una nueva categoria")
	@PostMapping
	private ResponseEntity<Categoria> createCategory(@RequestBody CategoriaModel categoriaModel) {
		return this.categoriasService.createCategory(categoriaModel.getCategoria());
	}
	
	@Operation(description = "Actualiza una categoria")
	@PutMapping("/{id}")
	private ResponseEntity<Categoria> updateCategory(@PathVariable Long id,@RequestBody CategoriaModel categoriaModel){
		return this.categoriasService.updateCategory(id, categoriaModel.getCategoria());
	}
	
	@Operation(description = "Actualiza parcialmente una categoria")
	@PatchMapping("/{id}")
	private ResponseEntity<Categoria> patchCategory(@PathVariable Long id,@RequestBody Map<String, Object> Categoria){
		return this.categoriasService.updateCategoryPatch(id, Categoria);
	}
	
	@Operation(description = "Elimina una categoria")
	@DeleteMapping("/{id}")
	private ResponseEntity<Object> deleteCategory(@PathVariable Long id){
		return this.categoriasService.deleteCategory(id);
	}

}
