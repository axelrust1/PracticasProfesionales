package com.pps.pps.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")  // Especifica el nombre de la tabla en la base de datos
public class clienteModel {

    @Id  // Define el campo 'dni' como la clave primaria
    private long dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private LocalDate fechaNacimiento;
    @Column
    private LocalDate fechaAlta;

    public clienteModel(){
        super();
        fechaAlta = LocalDate.now();
    }
    //@Column
    //private Set<Cuenta> cuentas = new LinkedHashSet<>();
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

    // Constructor, getters, setters, etc.
}
