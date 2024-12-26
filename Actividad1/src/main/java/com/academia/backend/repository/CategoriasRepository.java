package com.academia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.backend.entity.Categoria;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Long>{

}
