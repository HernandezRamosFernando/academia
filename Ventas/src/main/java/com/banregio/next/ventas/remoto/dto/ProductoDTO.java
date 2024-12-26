package com.banregio.next.ventas.remoto.dto;

import lombok.Data;

@Data
public class ProductoDTO {
	
	private Long id;
	private String nombre;
	private String sku;
	private Integer stock;
	private boolean estatus;
	
	private CategoriaDTO categoria;

}
