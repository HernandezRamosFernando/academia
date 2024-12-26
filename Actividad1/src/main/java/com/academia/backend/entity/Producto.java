package com.academia.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@Table(name = "PRODUCTO")
public class Producto {
	
	@Id
	private Long id;
	private String nombre;
	private String sku;
	private Integer stock;
	private boolean estatus;
	
	@ManyToOne
	private Categoria categoria;
	

}
