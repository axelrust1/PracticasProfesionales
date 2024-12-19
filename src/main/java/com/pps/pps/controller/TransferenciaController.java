package com.pps.pps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pps.pps.controller.validator.TransferenciaValidator;
import com.pps.pps.controller.validator.DepositoRetiroValidator;
import com.pps.pps.controller.handler.TransferMensaje;
import com.pps.pps.model.exception.CuentaDestinoNoExisteExcepcion;
import com.pps.pps.model.exception.CuentaNulaExcepcion;
import com.pps.pps.model.exception.CuentaOrigenNoExisteExcepcion;
import com.pps.pps.model.exception.CuentaOrigenyDestinoIguales;
import com.pps.pps.model.exception.CuentasOrigenDestinoNulas;
import com.pps.pps.model.exception.MonedaErroneaTransferenciaExcepcion;
import com.pps.pps.model.exception.MonedaVaciaExcepcion;
import com.pps.pps.model.exception.MonedasDistintasTransferenciaExcepcion;
import com.pps.pps.model.exception.MontoMenorIgualQueCero;
import com.pps.pps.model.exception.SaldoInsuficienteExcepcion;
import com.pps.pps.model.exception.TranferenciaBanelcoFalladaExcepcion;
import com.pps.pps.model.exception.TipoDeMonedaIncorrectoExcepcion;
import com.pps.pps.service.DepositoRetiroService;
import com.pps.pps.service.TransferenciaService;

@RestController
@RequestMapping("/api")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService; 

    @Autowired
    private DepositoRetiroService depositoRetiroService;

    @Autowired
    private TransferenciaValidator transferenciaValidator;

    @Autowired
    private DepositoRetiroValidator depositoRetiroValidator;

    @PostMapping ("/transfer")
    public TransferMensaje crearTransferencia(@RequestBody TransferenciaDto transferenciaDto)
    {
        try {
            transferenciaValidator.validate(transferenciaDto);
            transferenciaService.realizarTransferencia(transferenciaDto);
            return new TransferMensaje("EXITOSA", "Transferencia exitosa");
     } catch (TipoDeMonedaIncorrectoExcepcion | CuentaOrigenNoExisteExcepcion | MonedasDistintasTransferenciaExcepcion | MonedaErroneaTransferenciaExcepcion | SaldoInsuficienteExcepcion | TranferenciaBanelcoFalladaExcepcion | CuentaDestinoNoExisteExcepcion | CuentasOrigenDestinoNulas | MontoMenorIgualQueCero | CuentaOrigenyDestinoIguales | MonedaVaciaExcepcion excepcion) { //multicatch
            return new TransferMensaje("FALLIDA", excepcion.getMessage());
        }
    }

    @PostMapping("/deposito")
    public TransferMensaje hacerDeposito(@RequestBody DepositoRetiroDto depositoRetiroDto){
        try{
            depositoRetiroValidator.validate(depositoRetiroDto);
            depositoRetiroService.realizarDeposito(depositoRetiroDto);
            return new TransferMensaje("EXITOSO", "Deposito Exitoso");
        } catch (TipoDeMonedaIncorrectoExcepcion | CuentaOrigenNoExisteExcepcion | MonedaErroneaTransferenciaExcepcion | CuentaNulaExcepcion | MonedaVaciaExcepcion | MontoMenorIgualQueCero excepcion ){
            return new TransferMensaje("FALLIDA", excepcion.getMessage());
        }
    }

    @PostMapping("/retiro")
    public TransferMensaje hacerRetiro(@RequestBody DepositoRetiroDto depositoRetiroDto){
        try{
            depositoRetiroValidator.validate(depositoRetiroDto);
            depositoRetiroService.realizarRetiro(depositoRetiroDto);
            return new TransferMensaje("EXITOSO", "Retiro Exitoso");
        } catch (TipoDeMonedaIncorrectoExcepcion | SaldoInsuficienteExcepcion | CuentaOrigenNoExisteExcepcion | MonedaErroneaTransferenciaExcepcion | CuentaNulaExcepcion | MonedaVaciaExcepcion | MontoMenorIgualQueCero excepcion ){
            return new TransferMensaje("FALLIDA", excepcion.getMessage());
        }
    }
    }
