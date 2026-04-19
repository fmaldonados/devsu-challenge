package com.proyecto.transaccion.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MovimientoCreateRequestDTO {
   @NotNull
   private Long cuentaId;

   @NotNull
   private String tipoMovimiento;

   @NotNull
   @Positive
   private Double valor;

   public MovimientoCreateRequestDTO() {
   }

   public MovimientoCreateRequestDTO(Long cuentaId, String tipoMovimiento, Double valor) {
      this.cuentaId = cuentaId;
      this.tipoMovimiento = tipoMovimiento;
      this.valor = valor;
   }

   public Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   public String getTipoMovimiento() {
      return tipoMovimiento;
   }

   public void setTipoMovimiento(String tipoMovimiento) {
      this.tipoMovimiento = tipoMovimiento;
   }

   public Double getValor() {
      return valor;
   }

   public void setValor(Double valor) {
      this.valor = valor;
   }
}