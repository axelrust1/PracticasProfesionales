package com.pps.pps.controller;

import com.pps.pps.controller.validator.ClienteValidator;
import com.pps.pps.model.Cliente;
import com.pps.pps.model.exception.ClienteAlreadyExistsException;
import com.pps.pps.model.exception.ClienteNoExisteException;
import com.pps.pps.model.exception.FormatoFechaIncorrectoException;
import com.pps.pps.model.exception.TipoDePersonaIncorrectoExcepcion;
import com.pps.pps.service.clienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")

public class ClienteController {

    @Autowired
    private clienteService clienteService;

    @Autowired
    private ClienteValidator clienteValidator;


    @PostMapping
    public Cliente crearCliente(@RequestBody ClienteDto clienteDto) throws ClienteAlreadyExistsException, TipoDePersonaIncorrectoExcepcion, FormatoFechaIncorrectoException {
        clienteValidator.validate(clienteDto); //validamos 
        return clienteService.darDeAltaCliente(clienteDto);//si no hay excepcion retornamos el cliente
    }

    @GetMapping("/{dni}")
    public Cliente buscarClientePorDni(@PathVariable long dni) throws ClienteNoExisteException{
        return clienteService.buscarClientePorDni(dni);
    }

    @DeleteMapping("/{dni}")
    public String eliminarClientePorDni(@PathVariable long dni) throws ClienteNoExisteException {
        clienteService.eliminarClientePorDni(dni);
        return "Cliente con DNI " + dni + " eliminado exitosamente.";
    }

    @PutMapping("/{dni}")
    public Cliente actualizarCliente(@PathVariable long dni, @RequestBody ClienteDto clienteDto) throws ClienteNoExisteException, TipoDePersonaIncorrectoExcepcion, FormatoFechaIncorrectoException {
        clienteValidator.validate(clienteDto);
        return clienteService.actualizarCliente(dni, clienteDto);
    }
}
