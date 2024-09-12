package com.cristianrosas.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristianrosas.webapp.biblioteca.model.Cliente;
import com.cristianrosas.webapp.biblioteca.service.ClienteService;

@Controller
@RestController
@RequestMapping("")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/cliente")
    public ResponseEntity<Cliente> buscarClientePorDpi(@RequestParam Long dpi){
        try{
            Cliente cliente = clienteService.buscarClientePorDpi(dpi);
            return ResponseEntity.ok(cliente);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<Map<String, String>> agregarCliente(@RequestBody Cliente cliente){
        Map<String, String> response = new HashMap<>();
        
        try { 
            clienteService.guardarCliente(cliente);
            response.put("message", "Cliente creado con éxito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) { 
            response.put("message", "Error!");
            response.put("err", "Hubo un error al crear el cliente!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/cliente")
    public ResponseEntity<Map<String, String>> editarCliente(@RequestParam Long dpi, @RequestBody Cliente clienteNuevo){
        Map<String, String> response = new HashMap<>();
        
        try {
            Cliente cliente = clienteService.buscarClientePorDpi(dpi);
            cliente.setNombreCliente(clienteNuevo.getNombreCliente());
            cliente.setApellidoCliente(clienteNuevo.getApellidoCliente());
            cliente.setTelefonoCliente(clienteNuevo.getTelefonoCliente());
            clienteService.guardarCliente(cliente);
            response.put("message", "El cliente se ha modificado con éxito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al intentar modificar el cliente!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>> eliminarCliente(@RequestParam Long dpi){
        Map<String, String> response = new HashMap<>();
        
        try {
            Cliente cliente = clienteService.buscarClientePorDpi(dpi);
            clienteService.eliminarCliente(cliente);
            response.put("message", "El cliente se ha eliminado con éxito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "No se ha podido eliminar el cliente!");
            return ResponseEntity.badRequest().body(response);
        }
    }
}