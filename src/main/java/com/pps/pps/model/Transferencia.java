package com.pps.pps.model;

import com.pps.pps.controller.TransferenciaDto;
public class Transferencia {
    private long cuentaOrigen;
    private long cuentaDestino;
    private double monto;
    private String tipoMoneda;

    public Transferencia(TransferenciaDto transferenciaDto){
        this.cuentaOrigen = transferenciaDto.getCuentaOrigen();
        this.cuentaDestino = transferenciaDto.getCuentaDestino();
        this.monto = transferenciaDto.getMonto();
        this.tipoMoneda = transferenciaDto.getMoneda();
    }

    public long getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(long cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public long getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(long cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

}