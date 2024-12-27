package com.academia.backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.academia.backend.entity.Categoria;
import com.academia.backend.repository.CategoriasRepository;
import com.academia.backend.service.CategoriasService;

class CategoriaServiceTest {
	
	@Mock
	private CategoriasRepository categoriasRepository;

	@InjectMocks
	private CategoriasService categoriasService;
	
	private Categoria categoria1;
	private Categoria categoria2;
	
	@BeforeEach
	 public void setup() {
		
		MockitoAnnotations.openMocks(this);
		
		this.categoria1 = Categoria.builder()
				.id(1L)
				.nombre("Electronicos")
				.estatus(true)
				.build();
		
		this.categoria2 = Categoria.builder()
				.id(2L)
				.nombre("Electrodomesticos")
				.estatus(true)
				.build();
		
	 }

	@Test
    public void testGetAllCategories() {
        when(categoriasRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));

        ResponseEntity<List<Categoria>> response = categoriasService.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(categoriasRepository, times(1)).findAll();
    }

    @Test
    public void testGetCategoryById_Success() {
        when(categoriasRepository.findById(1L)).thenReturn(Optional.of(this.categoria1));

        ResponseEntity<Categoria> response = categoriasService.getCategoryById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testGetCategoryById_NotFound() {
        when(categoriasRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<Categoria> response = categoriasService.getCategoryById(999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateCategory() {
        Categoria categoriaGuardada = Categoria.builder()
        		.id(1L)
        		.nombre("Nueva Categoria")
        		.estatus(true)
        		.build();
        when(categoriasRepository.save(any(Categoria.class))).thenReturn(categoriaGuardada);

        ResponseEntity<Categoria> response = categoriasService.createCategory(this.categoria1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testUpdateCategory_Success() {
        Categoria categoriaExistente = Categoria.builder()
        		.id(1L)
        		.nombre("Categoria Existente")
        		.estatus(true)
        		.build();
        
        Categoria categoriaUpdate = Categoria.builder()
        		.id(1L)
        		.nombre("Categoria Actualizada")
        		.estatus(true)
        		.build();
        
        when(categoriasRepository.findById(1L)).thenReturn(Optional.of(categoriaExistente));
        when(categoriasRepository.save(any(Categoria.class))).thenReturn(categoriaUpdate);

        ResponseEntity<Categoria> response = categoriasService.updateCategory(1L, categoriaUpdate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Categoria Actualizada", response.getBody().getNombre());
    }

    @Test
    public void testUpdateCategory_NotFound() {
        when(categoriasRepository.findById(999L)).thenReturn(Optional.empty());

        Categoria categoriaUpdate = Categoria.builder()
        		.id(1L)
        		.nombre("Categoria No Existente")
        		.estatus(true)
        		.build();

        ResponseEntity<Categoria> response = categoriasService.updateCategory(999L, categoriaUpdate);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteCategory_Success() {
        Categoria categoriaExistente = Categoria.builder()
        		.id(1L)
        		.nombre("Categoria Existente")
        		.estatus(true)
        		.build();
        
        when(categoriasRepository.findById(1L)).thenReturn(Optional.of(categoriaExistente));

        ResponseEntity<Object> response = categoriasService.deleteCategory(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(categoriasRepository, times(1)).save(categoriaExistente);
    }

    @Test
    public void testDeleteCategory_NotFound() {
        when(categoriasRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = categoriasService.deleteCategory(999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
