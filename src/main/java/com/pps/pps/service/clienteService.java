package com.pps.pps.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pps.pps.repositories.ICliente;
import com.pps.pps.models.clienteModel;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class clienteService {
    
    @Autowired
    ICliente clienteRepository;

    public ArrayList<clienteModel> getClientes(){
        return (ArrayList<clienteModel>) clienteRepository.findAll();
    }

    public clienteModel guardarCliente (clienteModel cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<clienteModel>buscarPorDni(Long dni){
        return clienteRepository.findById(dni);
    }

    public clienteModel editarPorDni(clienteModel cliente, Long dni) {
        clienteModel clienteAux = clienteRepository.findById(dni)
                                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clienteAux.setNombre(cliente.getNombre());
        clienteAux.setApellido(cliente.getApellido());
        clienteAux.setFechaNacimiento(cliente.getFechaNacimiento());
        return clienteRepository.save(clienteAux); // Guarda los cambios
    }

    public Boolean eliminarCliente (Long dni){
        try {
            clienteRepository.deleteById(dni);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
