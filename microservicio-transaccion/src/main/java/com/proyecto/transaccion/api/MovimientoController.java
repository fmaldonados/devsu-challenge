package com.proyecto.transaccion.api;

import com.proyecto.transaccion.domain.Movimiento;
import com.proyecto.transaccion.infrastructure.dto.MovimientoCreateRequestDTO;
import com.proyecto.transaccion.infrastructure.dto.MovimientoReporteDTO;
import com.proyecto.transaccion.infrastructure.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientoController {

   private final MovimientoService movimientoService;

   public MovimientoController(MovimientoService movimientoService) {
      this.movimientoService = movimientoService;
   }

   @PostMapping
   public ResponseEntity<Movimiento> crearMovimiento(@Valid @RequestBody MovimientoCreateRequestDTO request) {
      Movimiento movimiento = movimientoService.registrarMovimiento(request);
      return ResponseEntity.ok(movimiento);
   }

   @GetMapping("/reportes")
   public ResponseEntity<List<MovimientoReporteDTO>> getReporte(
         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicial,
         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
         @RequestParam Long clienteId) {

      return ResponseEntity.ok(movimientoService.obtenerReporte(fechaInicial, fechaFin, clienteId));
   }
}