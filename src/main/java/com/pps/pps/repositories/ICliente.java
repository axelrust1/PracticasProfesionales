package com.pps.pps.repositories;

import com.pps.pps.models.clienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICliente extends JpaRepository<clienteModel, Long> {
    
}
