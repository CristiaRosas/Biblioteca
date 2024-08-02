package com.cristianrosas.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianrosas.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
