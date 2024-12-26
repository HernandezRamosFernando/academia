package com.academia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.backend.entity.Producto;

@Repository
public interface ProductosRepository  extends JpaRepository<Producto, Long>{

}
