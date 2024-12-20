package com.pps.pps.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pps.pps.controller.validator.CuentaValidator;
import com.pps.pps.model.Cuenta;
import com.pps.pps.model.exception.ClienteNoExisteException;
import com.pps.pps.model.exception.CuentaAlreadyExistsException;
import com.pps.pps.model.exception.CuentaNoEncontradaExcepcion;
import com.pps.pps.model.exception.CuentaNoSoportadaException;
import com.pps.pps.model.exception.TipoDeMonedaIncorrectoExcepcion;
import com.pps.pps.model.exception.TipoDeCuentaIncorrectoExcepcion;
import com.pps.pps.service.CuentaService;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService; 

    @Autowired
    private CuentaValidator cuentaValidator;

    @PostMapping
    public Cuenta crearCuenta(@RequestBody CuentaDto cuentaDto) throws TipoDeCuentaIncorrectoExcepcion, TipoDeMonedaIncorrectoExcepcion, ClienteNoExisteException, CuentaAlreadyExistsException, CuentaAlreadyExistsException, CuentaNoSoportadaException{
        cuentaValidator.validate(cuentaDto);
        return cuentaService.darDeAltaCuenta(cuentaDto);
    }

    @GetMapping ("/{numeroCuenta}")
    public Cuenta buscarCuenta(@PathVariable long numeroCuenta) throws CuentaNoEncontradaExcepcion {
        return cuentaService.find(numeroCuenta);
    }

    @GetMapping ("/dni/{dni}")
    public Set<Cuenta> buscarCuentaPorDni(@PathVariable long dni) throws ClienteNoExisteException {
        return cuentaService.obtenerCuentasPorDni(dni);
    }

    @GetMapping ("/{numeroCuenta}/transacciones")
    public MovimientoMensajeDto listaMovimientos(@PathVariable long numeroCuenta) throws CuentaNoEncontradaExcepcion{
        MovimientoMensajeDto movimiento = new MovimientoMensajeDto();
        Cuenta cuenta = cuentaService.find(numeroCuenta);
        movimiento.setNumeroCuenta(cuenta.getNumeroCuenta());
        movimiento.setTransacciones(cuenta.getMovimientos());
        return movimiento;
    }
}
