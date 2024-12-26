package com.academia.backend.web.model;

import com.academia.backend.entity.Categoria;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoriaModel {
	
	@Schema(description = "Nombre de la categoria", requiredMode = RequiredMode.REQUIRED)
	private String nombre;
	
	@Schema(description = "Estatus de la categoria", requiredMode = RequiredMode.NOT_REQUIRED)
	private boolean estatus;
	
	
	@Hidden
	public Categoria getCategoria(){
		
		return Categoria.builder()
				.nombre(this.nombre)
				.estatus(this.estatus)
				.build();
	}

}
