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
import com.academia.backend.entity.Producto;
import com.academia.backend.repository.ProductosRepository;
import com.academia.backend.service.ProductosService;

class ProductoServiceTest {

		@Mock
	    private ProductosRepository productosRepository;

	    @InjectMocks
	    private ProductosService productosService;

	    private Producto producto1;
	    private Producto producto2;
	    
		private Categoria categoria1;
		private Categoria categoria2;

	    @BeforeEach
	    void setUp() {
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
			
	        producto1 = Producto.builder()
	        		.id(1L)
	        		.nombre("Producto Test 1")
	        		.sku("0123456789")
	        		.stock(50)
	        		.estatus(true)
	        		.categoria(categoria1)
	        		.build();
	        
	        producto2 = Producto.builder()
	        		.id(1L)
	        		.nombre("Producto Test 2")
	        		.sku("1123456789")
	        		.stock(100)
	        		.estatus(true)
	        		.categoria(categoria2)
	        		.build();
	        
	        
	    }

	    @Test
	    public void testGetAllProducts() {
	        when(productosRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

	        ResponseEntity<List<Producto>> response = productosService.getAllProducts();

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(2, response.getBody().size());
	        verify(productosRepository, times(1)).findAll();
	    }

	    @Test
	    public void testGetProductById_Success() {
	        when(productosRepository.findById(1L)).thenReturn(Optional.of(this.producto1));

	        ResponseEntity<Producto> response = productosService.getProductById(1L);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	        assertEquals(1L, response.getBody().getId());
	    }

	    @Test
	    public void testGetProductById_NotFound() {
	        when(productosRepository.findById(999L)).thenReturn(Optional.empty());

	        ResponseEntity<Producto> response = productosService.getProductById(999L);

	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    }

	    @Test
	    public void testCreateProduct() {
	    	Producto productoGuardado = Producto.builder()
	        		.id(1L)
	        		.nombre("Producto Test 1")
	        		.sku("0123456789")
	        		.stock(50)
	        		.estatus(true)
	        		.categoria(categoria1)
	        		.build();
	    	
	    	
	        when(productosRepository.save(any(Producto.class))).thenReturn(productoGuardado);

	        ResponseEntity<Producto> response = productosService.createProduct(this.producto1);

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(1L, response.getBody().getId());
	    }

	    @Test
	    public void testUpdateProduct_Success() {
	        
	        Producto productoExistente = Producto.builder()
	        		.id(1L)
	        		.nombre("Producto Existente")
	        		.sku("0123456789")
	        		.stock(50)
	        		.estatus(true)
	        		.categoria(categoria1)
	        		.build();
	        
	        Producto productoUpdate = Producto.builder()
	        		.id(1L)
	        		.nombre("Producto Actualizado")
	        		.sku("1123456789")
	        		.stock(100)
	        		.estatus(true)
	        		.categoria(categoria1)
	        		.build();
	        
	        when(productosRepository.findById(1L)).thenReturn(Optional.of(productoExistente));
	        when(productosRepository.save(any(Producto.class))).thenReturn(productoUpdate);

	        ResponseEntity<Producto> response = productosService.updateProduct(1L, productoUpdate);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Producto Actualizado", response.getBody().getNombre());
	    }

	    @Test
	    public void testUpdateProduct_NotFound() {
	        when(productosRepository.findById(999L)).thenReturn(Optional.empty());

	        Producto productoUpdate = Producto.builder()
	        		.id(1L)
	        		.nombre("Producto No Existente")
	        		.sku("1123456789")
	        		.stock(100)
	        		.estatus(true)
	        		.categoria(categoria1)
	        		.build();

	        ResponseEntity<Producto> response = productosService.updateProduct(999L, productoUpdate);

	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    }

	    @Test
	    public void testDeleteProduct_Success() {
	        Producto productoExistente = Producto.builder()
	        		.id(1L)
	        		.nombre("Producto Existente")
	        		.sku("1123456789")
	        		.stock(100)
	        		.estatus(true)
	        		.categoria(categoria1)
	        		.build();
	        
	        when(productosRepository.findById(1L)).thenReturn(Optional.of(productoExistente));

	        ResponseEntity<Object> response = productosService.deleteProduct(1L);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        verify(productosRepository, times(1)).save(productoExistente);
	    }

	    @Test
	    public void testDeleteProduct_NotFound() {
	        when(productosRepository.findById(999L)).thenReturn(Optional.empty());

	        ResponseEntity<Object> response = productosService.deleteProduct(999L);

	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    }

}
