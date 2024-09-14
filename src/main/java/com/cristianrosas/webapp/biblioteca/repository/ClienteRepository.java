package com.cristianrosas.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianrosas.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

}
