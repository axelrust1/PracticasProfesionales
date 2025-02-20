package com.pps.pps.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.pps.pps.model.TipoPersona;

public class ClienteDto{
    private String nombre;
    private String apellido;
    private long dni;
    private LocalDate fechaNacimiento;
    private String tipoPersona;
    private List<CuentaDto> cuentas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public List<CuentaDto> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaDto> cuentas) {
        this.cuentas = cuentas;
    }
}
