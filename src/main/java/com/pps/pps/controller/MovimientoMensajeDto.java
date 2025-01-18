package com.pps.pps.controller;

import java.util.Set;
import com.pps.pps.model.Movimiento;

public class MovimientoMensajeDto {
    private long numeroCuenta;
    private Set<Movimiento> transacciones;

    public long getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public Set<Movimiento> getTransacciones() {
        return transacciones;
    }
    public void setTransacciones(Set<Movimiento> transacciones) {
        this.transacciones = transacciones;
    }
}
