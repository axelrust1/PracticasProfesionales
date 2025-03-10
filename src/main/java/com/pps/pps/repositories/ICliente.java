package com.pps.pps.repositories;

import com.pps.pps.model.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICliente extends JpaRepository<Cliente, Long> {
    Cliente findByDni(long dni); 
}
