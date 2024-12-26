package com.academia.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@Table(name = "CATEGORI")
public class Categoria {
	@Id
	private Long id;
	private String nombre;
	private boolean estatus;
}
