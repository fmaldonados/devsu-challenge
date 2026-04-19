package com.proyecto.transaccion.infrastructure.repository;

import com.proyecto.transaccion.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
   List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, java.time.LocalDateTime fechaInicio,
         java.time.LocalDateTime fechaFin);
}