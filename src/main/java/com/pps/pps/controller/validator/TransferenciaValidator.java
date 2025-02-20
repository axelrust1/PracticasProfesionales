package com.pps.pps.controller.validator;

import org.springframework.stereotype.Component;

import com.pps.pps.controller.TransferenciaDto;
import com.pps.pps.model.exception.CuentaOrigenyDestinoIguales;
import com.pps.pps.model.exception.CuentasOrigenDestinoNulas;
import com.pps.pps.model.exception.MonedaVaciaExcepcion;
import com.pps.pps.model.exception.MontoMenorIgualQueCero;

@Component
public class TransferenciaValidator {
    public void validate(TransferenciaDto transferenciaDto) throws CuentasOrigenDestinoNulas, MontoMenorIgualQueCero, CuentaOrigenyDestinoIguales, MonedaVaciaExcepcion {
        validateCuentaNulas(transferenciaDto);
        validateMonto(transferenciaDto);
        validateCuentas(transferenciaDto);
        validateMoneda(transferenciaDto);
    }

    private void validateCuentaNulas(TransferenciaDto transferenciaDto) throws CuentasOrigenDestinoNulas{
        if (transferenciaDto.getCuentaOrigen() == 0 || transferenciaDto.getCuentaDestino() == 0) {
            throw new CuentasOrigenDestinoNulas("Las cuentas de origen y destino no pueden ser nulas");
        }
    }
    private void validateMonto(TransferenciaDto transferenciaDto) throws MontoMenorIgualQueCero{
        if (transferenciaDto.getMonto() <= 0) {
            throw new MontoMenorIgualQueCero("El monto debe ser mayor a 0");
        }
    }
    private void validateCuentas(TransferenciaDto transferenciaDto) throws CuentaOrigenyDestinoIguales{
        if (transferenciaDto.getCuentaOrigen()==(transferenciaDto.getCuentaDestino())) {
            throw new CuentaOrigenyDestinoIguales("Las cuentas de origen y destino no pueden ser la misma");
        }
    }
    private void validateMoneda(TransferenciaDto transferenciaDto) throws MonedaVaciaExcepcion{
        if (transferenciaDto.getMoneda() == null || transferenciaDto.getMoneda().isEmpty()) {
            throw new MonedaVaciaExcepcion("La moneda no puede ser vacia");
        }
    }
}
