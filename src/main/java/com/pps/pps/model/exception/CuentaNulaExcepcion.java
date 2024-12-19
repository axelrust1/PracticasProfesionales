package com.pps.pps.model.exception;

public class CuentaNulaExcepcion extends Exception {
    public CuentaNulaExcepcion(String message){
        super("La cuenta no puede ser nula.");
    }
}
