package com.pps.pps.service;

import com.pps.pps.repositories.ICuenta;
import com.pps.pps.repositories.IMovimiento;
import com.pps.pps.repositories.ICliente;
import com.pps.pps.model.Cuenta;
import com.pps.pps.model.Cliente;
import com.pps.pps.model.Movimiento;
import com.pps.pps.model.TipoMoneda;
import com.pps.pps.model.Transferencia;
import com.pps.pps.controller.validator.TransferenciaValidator;
import com.pps.pps.controller.TransferenciaDto;
import com.pps.pps.controller.MovimientoDto;
import com.pps.pps.model.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class TransferenciaService {
    @Autowired
    private ICuenta cuentaRepository;

    @Autowired
    private ICliente clienteRepository;

    @Autowired
    private IMovimiento movimientoRepository;

    @Autowired
    private TransferenciaValidator transValidator;

    public Transferencia realizarTransferencia(TransferenciaDto transferenciaDto) 
            throws TipoDeMonedaIncorrectoExcepcion, MonedaVaciaExcepcion, CuentaOrigenyDestinoIguales, 
                   MontoMenorIgualQueCero, CuentasOrigenDestinoNulas, CuentaOrigenNoExisteExcepcion, 
                   CuentaDestinoNoExisteExcepcion, MonedasDistintasTransferenciaExcepcion, 
                   MonedaErroneaTransferenciaExcepcion, SaldoInsuficienteExcepcion{

        // Validaciones iniciales
        transValidator.validate(transferenciaDto);
        Transferencia trans = new Transferencia(transferenciaDto);

        // Buscar cuentas en la base de datos
        Optional<Cuenta> cuentaOrigenOpt = cuentaRepository.findById(transferenciaDto.getCuentaOrigen());
        Optional<Cuenta> cuentaDestinoOpt = cuentaRepository.findById(transferenciaDto.getCuentaDestino());

        if (cuentaOrigenOpt.isEmpty()) {
            throw new CuentaOrigenNoExisteExcepcion("La cuenta origen no existe.");
        }
        if (cuentaDestinoOpt.isEmpty()) {
            throw new CuentaDestinoNoExisteExcepcion("La cuenta destino no existe.");
        }

        Cuenta cuentaOrigen = cuentaOrigenOpt.get();
        Cuenta cuentaDestino = cuentaDestinoOpt.get();

        // Validar moneda
        TipoMoneda tipoMoneda = TipoMoneda.valueOf(transferenciaDto.getMoneda());
        if (!tipoMoneda.equals(cuentaOrigen.getMoneda()) || !tipoMoneda.equals(cuentaDestino.getMoneda())) {
            throw new MonedasDistintasTransferenciaExcepcion("Las monedas de las cuentas son distintas.");
        }

        if (cuentaOrigen.getBalance() < transferenciaDto.getMonto()) {
            throw new SaldoInsuficienteExcepcion("Saldo insuficiente para realizar la transferencia.");
        }

        
        cuentaOrigen.setBalance(cuentaOrigen.getBalance() - transferenciaDto.getMonto());
        cuentaDestino.setBalance(cuentaDestino.getBalance() + transferenciaDto.getMonto());

        // Registrar movimientos
        MovimientoDto movimientoDebitoDto = new MovimientoDto(LocalDate.now(), "DEBITO", "Transferencia Saliente", trans.getMonto());
        MovimientoDto movimientoCreditoDto = new MovimientoDto(LocalDate.now(), "CREDITO", "Transferencia Entrante", trans.getMonto());

        Movimiento movimientoDebito = new Movimiento(movimientoDebitoDto);
        Movimiento movimientoCredito = new Movimiento(movimientoCreditoDto);
        cuentaOrigen.addMovimiento(movimientoDebito);
        cuentaDestino.addMovimiento(movimientoCredito);

        movimientoRepository.save(movimientoDebito);
        movimientoRepository.save(movimientoCredito);
        movimientoDebito.setCuenta(cuentaOrigen);
        movimientoCredito.setCuenta(cuentaDestino);
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        return trans;
    }
}
