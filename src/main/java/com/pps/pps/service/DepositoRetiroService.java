package com.pps.pps.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pps.pps.controller.DepositoRetiroDto;
import com.pps.pps.controller.MovimientoDto;
import com.pps.pps.model.Cuenta;
import com.pps.pps.model.DepositoRetiro;
import com.pps.pps.controller.validator.DepositoRetiroValidator;
import com.pps.pps.model.Movimiento;
import com.pps.pps.model.TipoMoneda;
import com.pps.pps.model.exception.*;
import com.pps.pps.repositories.ICuenta;
import com.pps.pps.repositories.IMovimiento;

@Service
public class DepositoRetiroService {
    
    @Autowired
    private ICuenta cuentaRepository;
    
    @Autowired
    private IMovimiento movimientoRepository;

    @Autowired
    private DepositoRetiroValidator validator;

    @Transactional
    public DepositoRetiro realizarDeposito(DepositoRetiroDto depositoRetiroDto) throws 
            MontoMenorIgualQueCero, 
            MonedaVaciaExcepcion, 
            TipoDeMonedaIncorrectoExcepcion, 
            CuentaNulaExcepcion, 
            CuentaOrigenNoExisteExcepcion, 
            MonedaErroneaTransferenciaExcepcion {
        
        // Validaciones básicas
        validarDatosOperacion(depositoRetiroDto);
        
        // Obtener cuenta
        Cuenta cuenta = cuentaRepository.findById(depositoRetiroDto.getCuenta())
                .orElseThrow(() -> new CuentaOrigenNoExisteExcepcion("La cuenta en la que quiere depositar no existe."));
        
        // Validar moneda
        validarMoneda(depositoRetiroDto, cuenta);
        
        // Crear depósito
        DepositoRetiro deposito = new DepositoRetiro(depositoRetiroDto);
        
        // Actualizar saldo
        cuenta.setBalance(cuenta.getBalance() + deposito.getMonto());
        cuentaRepository.save(cuenta);
        
        // Registrar movimiento
        MovimientoDto movimientoAux = new MovimientoDto(LocalDate.now(), "DEPOSITO", "Deposito Entrante", deposito.getMonto());
        Movimiento movimiento = new Movimiento(movimientoAux);
        movimientoRepository.save(movimiento);
        movimiento.setCuenta(cuenta);
        cuenta.addMovimiento(movimiento);
        return deposito;
    }

    @Transactional
    public DepositoRetiro realizarRetiro(DepositoRetiroDto depositoRetiroDto) throws 
            SaldoInsuficienteExcepcion, 
            MontoMenorIgualQueCero, 
            MonedaVaciaExcepcion, 
            TipoDeMonedaIncorrectoExcepcion, 
            CuentaOrigenNoExisteExcepcion, 
            MonedaErroneaTransferenciaExcepcion, CuentaNulaExcepcion {
        
        // Validaciones básicas
        validator.validate(depositoRetiroDto);
        
        // Obtener cuenta
        Cuenta cuenta = cuentaRepository.findById(depositoRetiroDto.getCuenta())
                .orElseThrow(() -> new CuentaOrigenNoExisteExcepcion("La cuenta de la que quiere hacer el retiro no existe."));
        
        // Validar moneda y saldo
        validarMoneda(depositoRetiroDto, cuenta);
        validarSaldo(depositoRetiroDto, cuenta);
        
        // Crear retiro
        DepositoRetiro retiro = new DepositoRetiro(depositoRetiroDto);
        
        // Actualizar saldo
        cuenta.setBalance(cuenta.getBalance() - retiro.getMonto());
        cuentaRepository.save(cuenta);
        
        // Registrar movimiento
        MovimientoDto movimientoAux = new MovimientoDto(LocalDate.now(), "RETIRO", "Retiro de dinero", retiro.getMonto());
        Movimiento movimiento = new Movimiento(movimientoAux);
        movimientoRepository.save(movimiento);
        movimiento.setCuenta(cuenta);
        cuenta.addMovimiento(movimiento);
        return retiro;
    }
    
    private void validarDatosOperacion(DepositoRetiroDto operacion) throws 
            MonedaVaciaExcepcion, 
            MontoMenorIgualQueCero, 
            CuentaNulaExcepcion, 
            TipoDeMonedaIncorrectoExcepcion {
        
        if (operacion.getMoneda() == null) {
            throw new MonedaVaciaExcepcion("La moneda no puede ser vacia");
        }
        
        if (operacion.getMonto() <= 0) {
            throw new MontoMenorIgualQueCero("El monto debe ser mayor a 0");
        }
        
        if (operacion.getCuenta() == 0) {
            throw new CuentaNulaExcepcion("La cuenta no puede ser nula.");
        }
        
        if (!"PESOS".equals(operacion.getMoneda()) && !"DOLARES".equals(operacion.getMoneda())) {
            throw new TipoDeMonedaIncorrectoExcepcion("Tipo de moneda " + operacion.getMoneda() + " es incorrecto");
        }
    }
    
    private void validarMoneda(DepositoRetiroDto operacion, Cuenta cuenta) throws MonedaErroneaTransferenciaExcepcion {
        String tipoMoneda = String.valueOf(cuenta.getMoneda());
        if (!operacion.getMoneda().equals(tipoMoneda)) {
            throw new MonedaErroneaTransferenciaExcepcion("Error en la moneda seleccionada para la operación");
        }
    }
    
    private void validarSaldo(DepositoRetiroDto operacion, Cuenta cuenta) throws SaldoInsuficienteExcepcion {
        if (cuenta.getBalance() < operacion.getMonto()) {
            throw new SaldoInsuficienteExcepcion("No hay saldo suficiente para realizar el retiro.");
        }
    }
}