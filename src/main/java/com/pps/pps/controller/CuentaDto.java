package com.pps.pps.controller;
import java.util.List;

import com.pps.pps.model.TipoCuenta;
import com.pps.pps.model.TipoMoneda;
public class CuentaDto {

    private String tipoCuenta;
    private long dniCliente;
    private String moneda;
    private List<MovimientoDto> movimientos; //agrego una lista con los movimientos de cada cuenta

    public long getDniCliente() {
        return dniCliente;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setDniCliente(long dniCliente) {
        this.dniCliente = dniCliente;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public List<MovimientoDto> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDto> movimientos) {
        this.movimientos = movimientos;
    }
}
