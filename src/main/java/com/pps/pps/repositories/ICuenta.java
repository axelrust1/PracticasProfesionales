package com.pps.pps.repositories;

import com.pps.pps.model.Cuenta;
import com.pps.pps.model.Cliente;
import com.pps.pps.model.TipoCuenta;
import com.pps.pps.model.TipoMoneda;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuenta extends JpaRepository<Cuenta, Long> {
    Cuenta findByclienteAndTipoCuentaAndMoneda(Cliente cliente, TipoCuenta tipoCuenta, TipoMoneda moneda); // Modificado
    Set<Cuenta> findByCliente_Dni(long dni);
}
