package com.pps.pps.controller.handler;

import com.pps.pps.model.exception.CantidadNegativaException;
import com.pps.pps.model.exception.ClienteAlreadyExistsException;
import com.pps.pps.model.exception.ClienteNoExisteException;
import com.pps.pps.model.exception.CuentaAlreadyExistsException;
import com.pps.pps.model.exception.CuentaDestinoNoExisteExcepcion;
import com.pps.pps.model.exception.CuentaNoEncontradaExcepcion;
import com.pps.pps.model.exception.CuentaNoSoportadaException;
import com.pps.pps.model.exception.CuentaNulaExcepcion;
import com.pps.pps.model.exception.CuentaOrigenNoExisteExcepcion;
import com.pps.pps.model.exception.CuentaOrigenyDestinoIguales;
import com.pps.pps.model.exception.CuentasOrigenDestinoNulas;
import com.pps.pps.model.exception.FormatoFechaIncorrectoException;
import com.pps.pps.model.exception.MonedaErroneaTransferenciaExcepcion;
import com.pps.pps.model.exception.MonedasDistintasTransferenciaExcepcion;
import com.pps.pps.model.exception.MonedaVaciaExcepcion;
import com.pps.pps.model.exception.MontoMenorIgualQueCero;
import com.pps.pps.model.exception.SaldoInsuficienteExcepcion;
import com.pps.pps.model.exception.TipoDeCuentaIncorrectoExcepcion;
import com.pps.pps.model.exception.TipoDeMonedaIncorrectoExcepcion;
import com.pps.pps.model.exception.TipoDePersonaIncorrectoExcepcion;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TupResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {CantidadNegativaException.class,
ClienteAlreadyExistsException.class,
ClienteNoExisteException.class,
CuentaAlreadyExistsException.class,
CuentaDestinoNoExisteExcepcion.class,
CuentaNoEncontradaExcepcion.class,
CuentaNoSoportadaException.class,
CuentaNulaExcepcion.class,
CuentaOrigenNoExisteExcepcion.class,
CuentaOrigenyDestinoIguales.class,
CuentasOrigenDestinoNulas.class,
FormatoFechaIncorrectoException.class,
MonedaErroneaTransferenciaExcepcion.class,
MonedasDistintasTransferenciaExcepcion.class,
MonedaVaciaExcepcion.class,
MontoMenorIgualQueCero.class,
SaldoInsuficienteExcepcion.class,
TipoDeCuentaIncorrectoExcepcion.class,
TipoDeMonedaIncorrectoExcepcion.class,
TipoDePersonaIncorrectoExcepcion.class})
    protected ResponseEntity<Object> handleMateriaNotFound(
            Exception ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value
            = { IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(1234);
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (body == null) {
            CustomApiError error = new CustomApiError();
            error.setErrorMessage(ex.getMessage());
            body = error;
        }

        return new ResponseEntity(body, headers, status);
    }

}