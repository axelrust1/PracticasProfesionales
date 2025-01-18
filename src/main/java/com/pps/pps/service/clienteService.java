package com.pps.pps.service;

import com.pps.pps.controller.ClienteDto;
import com.pps.pps.model.Cliente;
import com.pps.pps.model.Cuenta;
import com.pps.pps.model.exception.ClienteAlreadyExistsException;
import com.pps.pps.model.exception.ClienteNoExisteException;
import com.pps.pps.model.exception.CuentaAlreadyExistsException;
import com.pps.pps.repositories.ICliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class clienteService {

    private final ICliente clienteRepository;

    public clienteService(ICliente clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Transactional
    public Cliente darDeAltaCliente(ClienteDto clienteDto) throws ClienteAlreadyExistsException {
        Cliente cliente = new Cliente(clienteDto);

        if (clienteRepository.findByDni(cliente.getDni())!=null) {
            throw new ClienteAlreadyExistsException("Ya existe un cliente con DNI " + cliente.getDni());
        }

        if (cliente.getEdad() < 18) {
            throw new IllegalArgumentException("El cliente debe ser mayor a 18 años");
        }

        //guardar cliente en la base de datos
        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public Cliente buscarClientePorDni(long dni) throws ClienteNoExisteException {
        //buscar cliente x id
        Cliente cliente = clienteRepository.findByDni(dni);

        return cliente;
    }
}
