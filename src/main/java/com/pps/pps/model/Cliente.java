package com.pps.pps.model;

import com.pps.pps.controller.ClienteDto;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")  // Especifica el nombre de la tabla en la base de datos
public class Cliente {

    @Id
    @Column(name = "dni")
    private long dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private LocalDate fechaAlta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPersona tipoPersona;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cuenta> cuentas = new LinkedHashSet<>();

    public Cliente() {
        super();
        this.fechaAlta = LocalDate.now();
    }

    public Cliente(long dni, String nombre, String apellido, String fechaNacimiento, TipoPersona tipoPersona) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
        this.fechaAlta = LocalDate.now();
        this.tipoPersona = tipoPersona;
    }

    public Cliente(ClienteDto clienteDto) {
        this.dni = clienteDto.getDni();
        this.nombre = clienteDto.getNombre();
        this.apellido = clienteDto.getApellido();
        this.fechaNacimiento = clienteDto.getFechaNacimiento();
        this.fechaAlta = LocalDate.now(); // Se asigna la fecha actual
        this.tipoPersona = TipoPersona.fromString(clienteDto.getTipoPersona()); // Conversión del tipo de persona
    }

    // Getters y setters


    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Set<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    

    public int getEdad() {
        LocalDate currentDate = LocalDate.now();
        Period agePeriod = Period.between(fechaNacimiento, currentDate);
        return agePeriod.getYears();
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
        cuenta.setCliente(this); // Relación bidireccional
    }


    public boolean tieneCuenta(TipoCuenta tipoCuenta, TipoMoneda moneda) {
        for (Cuenta cuenta:
                cuentas) {
            if (tipoCuenta.equals(cuenta.getTipoCuenta()) && moneda.equals(cuenta.getMoneda())) {
                return true;
            }
        }
        return false;
    }
}
