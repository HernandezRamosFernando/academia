package com.academia.backend.web.model;

import com.academia.backend.entity.Categoria;
import com.academia.backend.entity.Producto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductoModel {
	
	@Schema(description = "Nombre del producto", requiredMode = RequiredMode.REQUIRED)
	private String nombre;
	
	@Schema(description = "SKU del producto", requiredMode = RequiredMode.REQUIRED)
	private String sku;
	
	@Schema(description = "Cantidad de producto en existencias", requiredMode = RequiredMode.REQUIRED)
	private Integer stock;
	
	@Schema(description = "Estatus del producto", requiredMode = RequiredMode.REQUIRED)
	private boolean estatus;
	
	@Schema(description = "Id de la categoria a la que pertenece el producto", requiredMode = RequiredMode.REQUIRED)
	private Long categoriaId;
	
	
	
	@Hidden
	public Producto getProducto(){
		return Producto.builder()
				.nombre(this.nombre)
				.sku(this.sku)
				.stock(this.stock)
				.estatus(this.estatus)
				.categoria(Categoria.builder().id(categoriaId).build())
				.build();
	}

}
