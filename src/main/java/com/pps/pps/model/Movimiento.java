package com.pps.pps.model;
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
@Table(name = "movimientos")  // Especifica el nombre de la tabla en la base de datos
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generación automática del ID
    private long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, length = 255)
    private String descripcionBreve;

    @Column(nullable = false)
    private double monto;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    public Movimiento() {
        // Constructor por defecto para JPA
    }

    public Movimiento(MovimientoDto movimientoDto) {
        this.fecha = movimientoDto.getFecha();
        this.tipo = movimientoDto.getTipo();
        this.descripcionBreve = movimientoDto.getDescripcionBreve();
        this.monto = movimientoDto.getMonto();
    }

    // Getters y Setters

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
}
