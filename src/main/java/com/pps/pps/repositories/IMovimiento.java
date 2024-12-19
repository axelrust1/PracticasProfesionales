package com.pps.pps.repositories;

import com.pps.pps.model.Movimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimiento extends JpaRepository<Movimiento, Long> {
    
}
