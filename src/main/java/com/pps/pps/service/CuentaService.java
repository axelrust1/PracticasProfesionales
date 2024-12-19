package com.pps.pps.service;

import com.pps.pps.controller.CuentaDto;
import com.pps.pps.model.Cliente;
import com.pps.pps.model.Cuenta;
import com.pps.pps.model.TipoCuenta;
import com.pps.pps.model.TipoMoneda;
import com.pps.pps.model.exception.ClienteNoExisteException;
import com.pps.pps.model.exception.CuentaAlreadyExistsException;
import com.pps.pps.model.exception.CuentaNoEncontradaExcepcion;
import com.pps.pps.model.exception.CuentaNoSoportadaException;
import com.pps.pps.repositories.ICuenta;
import com.pps.pps.repositories.ICliente;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    private ICuenta cuentaRepository; 

    @Autowired
    private ICliente clienteRepository;

    @Autowired
    private ClienteService clienteService;

    public Cuenta darDeAltaCuenta(CuentaDto cuentaDto) throws ClienteNoExisteException, CuentaAlreadyExistsException, CuentaNoSoportadaException {

       
        // Primero buscamos el cliente por DNI
        Cliente cliente = clienteRepository.findByDni(cuentaDto.getDniCliente());
        if (cliente == null) {
            throw new ClienteNoExisteException("El cliente con DNI " + cuentaDto.getDniCliente() + " no existe.");
        }

        // Creamos la nueva cuenta
        Cuenta cuenta = new Cuenta(cuentaDto);
        cuenta.setCliente(cliente);

        // Verificamos si ya existe una cuenta del mismo tipo
        if (cuentaRepository.findByclienteAndTipoCuentaAndMoneda(
                cliente, cuenta.getTipoCuenta(), cuenta.getMoneda()) != null) {
            throw new CuentaAlreadyExistsException("El cliente ya tiene una cuenta de este tipo.");
        }

        // Verificamos si el tipo de cuenta está soportado
        if (!tipoCuentaEstaSoportada(cuenta)) {
            throw new CuentaNoSoportadaException("El tipo de cuenta " + cuenta.getTipoCuenta() +
                    " con " + cuenta.getMoneda() + " no está soportada.");
        }
        // Guardamos la cuenta
        return cuentaRepository.save(cuenta);
    }

    public boolean tipoCuentaEstaSoportada(Cuenta cuenta) {
        return (cuenta.getTipoCuenta() == TipoCuenta.CUENTA_CORRIENTE && cuenta.getMoneda() == TipoMoneda.PESOS) ||
               (cuenta.getTipoCuenta() == TipoCuenta.CAJA_AHORRO &&
                (cuenta.getMoneda() == TipoMoneda.PESOS || cuenta.getMoneda() == TipoMoneda.DOLARES));
    }

    public Cuenta find(long id) throws CuentaNoEncontradaExcepcion {
        return cuentaRepository.findById(id).orElseThrow(() ->
                new CuentaNoEncontradaExcepcion("Cuenta no encontrada"));
    }

    public Set<Cuenta> obtenerCuentasPorDni(Long dni) {
        // Aquí llamamos al DAO para obtener las cuentas del cliente por su DNI
        Cliente cliente = clienteRepository.findByDni(dni);
        Set<Cuenta> cuentas = cuentaRepository.findByCliente_Dni(cliente.getDni());
        
        return cuentas;
    }
}
