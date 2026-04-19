package com.proyecto.transaccion.infrastructure.dto;

import java.time.LocalDateTime;

public class MovimientoReporteDTO {
   private Long id;
   private LocalDateTime fecha;
   private String tipoMovimiento;
   private double valor;
   private double saldoResultante;
   private Long cuentaId;

   public MovimientoReporteDTO() {
   }

   public MovimientoReporteDTO(Long id, LocalDateTime fecha, String tipoMovimiento,
         double valor, double saldoResultante, Long cuentaId) {
      this.id = id;
      this.fecha = fecha;
      this.tipoMovimiento = tipoMovimiento;
      this.valor = valor;
      this.saldoResultante = saldoResultante;
      this.cuentaId = cuentaId;
   }

   // Getters y Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDateTime getFecha() {
      return fecha;
   }

   public void setFecha(LocalDateTime fecha) {
      this.fecha = fecha;
   }

   public String getTipoMovimiento() {
      return tipoMovimiento;
   }

   public void setTipoMovimiento(String tipoMovimiento) {
      this.tipoMovimiento = tipoMovimiento;
   }

   public double getValor() {
      return valor;
   }

   public void setValor(double valor) {
      this.valor = valor;
   }

   public double getSaldoResultante() {
      return saldoResultante;
   }

   public void setSaldoResultante(double saldoResultante) {
      this.saldoResultante = saldoResultante;
   }

   public Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(Long cuentaId) {
      this.cuentaId = cuentaId;
   }
}