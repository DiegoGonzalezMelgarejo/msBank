package com.bank.diego.repository;


import com.bank.diego.entities.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimientos,Long> {

    List<Movimientos> findByFechaBetween(Date fechaInicio, Date fechaFinal);
}
