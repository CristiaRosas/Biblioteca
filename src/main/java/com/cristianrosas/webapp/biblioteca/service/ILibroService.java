package com.cristianrosas.webapp.biblioteca.service;

import java.util.List;

import com.cristianrosas.webapp.biblioteca.model.Libro;

public interface ILibroService {
    public List<Libro> listarLibros();

    public Libro guardarLibro(Libro libro);

    public Libro buscarLibroPorId(Long Id);

    public void eliminarLibro(Libro libro);
}
