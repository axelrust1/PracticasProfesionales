package com.pps.pps.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pps.pps.controller.MovimientoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDate;

@Entity
@Table(name = "movimientos") 
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, length = 255)
    private String descripcionBreve;

    @Column(nullable = false)
    private double monto;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "numeroCuenta")
    private Cuenta cuenta;

    public Movimiento() {
    }

    public Movimiento(MovimientoDto movimientoDto) {
        this.fecha = movimientoDto.getFecha();
        this.tipo = movimientoDto.getTipo();
        this.descripcionBreve = movimientoDto.getDescripcionBreve();
        this.monto = movimientoDto.getMonto();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcionBreve() {
        return descripcionBreve;
    }

    public void setDescripcionBreve(String descripcionBreve) {
        this.descripcionBreve = descripcionBreve;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }
}
