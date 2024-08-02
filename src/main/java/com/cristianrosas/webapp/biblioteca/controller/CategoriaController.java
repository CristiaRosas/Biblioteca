package com.cristianrosas.webapp.biblioteca.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianrosas.webapp.biblioteca.model.Categoria;
import com.cristianrosas.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping(value = "categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public List<Categoria> listaCategorias(){
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id){
        try{
           return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String, Boolean> response = new HashMap<>();
        try{
            categoriaService.guardarCategoria(categoria);
            response.put("se agregocon exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("se agregocon exito", Boolean.TRUE);
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaNueva){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria());
            categoriaService.guardarCategoria(categoria);
            response.put("message", "La Categoria se ha editado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "La categoria no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }
    }
       


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try{
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "Categoria eliminada con exito");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "La categoria no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }

}