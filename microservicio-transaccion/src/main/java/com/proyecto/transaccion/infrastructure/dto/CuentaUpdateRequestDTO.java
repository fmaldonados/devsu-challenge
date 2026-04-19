package com.proyecto.transaccion.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public class CuentaUpdateRequestDTO {
   @NotBlank(message = "El tipo de cuenta es obligatorio")
   private String tipo;

   @NotBlank(message = "El estado es obligatorio")
   private String estado;

   public CuentaUpdateRequestDTO() {
   }

   public CuentaUpdateRequestDTO(String tipo, String estado) {
      this.tipo = tipo;
      this.estado = estado;
   }

   // Getters and Setters
   public String getTipo() {
      return tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public String getEstado() {
      return estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }
}