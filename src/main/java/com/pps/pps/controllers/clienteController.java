package com.pps.pps.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.pps.pps.models.clienteModel;
import com.pps.pps.service.clienteService;

@RestController
@RequestMapping("/cliente")
public class clienteController {

    @Autowired
    private clienteService clienteservice;

    @GetMapping
    public ArrayList<clienteModel> getClientes(){
        return this.clienteservice.getClientes();
    }

    @PostMapping
    public clienteModel guardarCliente(@RequestBody clienteModel cliente){
        return this.clienteservice.guardarCliente(cliente);
    }

    @GetMapping(path="/{dni}")
    public Optional<clienteModel> buscarPorDni(@PathVariable("dni") Long dni){
        return this.clienteservice.buscarPorDni(dni);
    }

    @PatchMapping(path = "/{dni}")
public clienteModel editarPorDni(@RequestBody clienteModel cliente, @PathVariable("dni") Long dni) {
    return this.clienteservice.editarPorDni(cliente, dni);
}

    @DeleteMapping(path="/{dni}")
    public String eliminarCliente(@PathVariable("dni") Long dni){
        boolean aux = this.clienteservice.eliminarCliente(dni);
        if(aux){
            return "cliente con dni " + dni + " fue eliminado";
        } else {
            return "Error";
        }
    }
}
