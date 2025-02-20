package com.pps.pps.controller.validator;

import org.springframework.stereotype.Component;

import com.pps.pps.controller.CuentaDto;
import com.pps.pps.model.exception.TipoDeCuentaIncorrectoExcepcion;
import com.pps.pps.model.exception.TipoDeMonedaIncorrectoExcepcion;


@Component
public class CuentaValidator {

    public void validate(CuentaDto cuentaDto) throws TipoDeMonedaIncorrectoExcepcion, TipoDeCuentaIncorrectoExcepcion {
        validateTipoCuenta(cuentaDto);
        validateMoneda(cuentaDto);
    }

    private void validateTipoCuenta(CuentaDto cuentaDto) throws TipoDeCuentaIncorrectoExcepcion {
        if (!"C".equals(cuentaDto.getTipoCuenta()) && !"A".equals(cuentaDto.getTipoCuenta())) { //modifico el or por el and
            throw new TipoDeCuentaIncorrectoExcepcion("El tipo de cuenta no es correcto");
        }
    }

    private void validateMoneda(CuentaDto cuentaDto) throws TipoDeMonedaIncorrectoExcepcion{
        if (!"P".equals(cuentaDto.getMoneda()) && !"D".equals(cuentaDto.getMoneda())) {
            throw new TipoDeMonedaIncorrectoExcepcion("El tipo de moneda no es correcto");
        }
    }
}
