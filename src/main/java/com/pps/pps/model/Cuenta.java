package com.pps.pps.model;
import com.pps.pps.controller.CuentaDto;
import com.pps.pps.controller.MovimientoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    private long numeroCuenta;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCuenta tipoCuenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMoneda moneda;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dniCliente")
    private Cliente cliente;

    @JsonManagedReference
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Movimiento> movimientos = new LinkedHashSet<>();

    // Constructores
    public Cuenta() {
        int max = 9999999;
        int aux = new Random().nextInt(max); 
        if (aux<0){
            numeroCuenta = aux * -1;        //nos aseguramos de que el numero de cuenta random siempre sea positivo
        } else {
            numeroCuenta=aux;
        }
        this.fechaCreacion = LocalDate.now();
        this.balance = 0;
    }

    public Cuenta(CuentaDto cuentaDto) {
        this.tipoCuenta = TipoCuenta.fromString(cuentaDto.getTipoCuenta());
        this.moneda = TipoMoneda.fromString(cuentaDto.getMoneda());
        this.fechaCreacion = LocalDate.now();
        this.balance = 50000;
        int max = 9999999;
        int aux = new Random().nextInt(max); 
        if (aux<0){
            this.numeroCuenta = aux * -1;        //nos aseguramos de que el numero de cuenta random siempre sea positivo
        } else {
            this.numeroCuenta=aux;
        }
    }

    // Getters y Setters
    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public TipoMoneda getMoneda() {
        return moneda;
    }

    public void setMoneda(TipoMoneda moneda) {
        this.moneda = moneda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void addMovimiento(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }
}

