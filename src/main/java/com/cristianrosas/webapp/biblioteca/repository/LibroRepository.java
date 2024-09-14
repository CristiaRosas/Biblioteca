package com.cristianrosas.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianrosas.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long> {

}
