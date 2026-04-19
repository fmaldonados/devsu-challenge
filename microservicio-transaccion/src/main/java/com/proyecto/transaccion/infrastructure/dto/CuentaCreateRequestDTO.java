package com.proyecto.transaccion.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CuentaCreateRequestDTO {

   @NotBlank(message = "El número de cuenta es obligatorio")
   private String numeroCuenta;

   @NotBlank(message = "El tipo de cuenta es obligatorio")
   private String tipo; // AHORRO, CORRIENTE

   @NotNull(message = "El saldo inicial es obligatorio")
   @Positive(message = "El saldo inicial debe ser un valor positivo")
   private Double saldoInicial;

   @NotNull(message = "El cliente ID es obligatorio")
   private Long clienteId;

   @NotBlank(message = "El estado es obligatorio")
   private String estado; // ACTIVA, INACTIVA, BLOQUEADA

   public CuentaCreateRequestDTO() {
   }

   public CuentaCreateRequestDTO(String numeroCuenta, String tipo, Double saldoInicial, Long clienteId, String estado) {
      this.numeroCuenta = numeroCuenta; // Nota: Ten cuidado si hubo error de tipeo aquí también
      this.tipo = tipo;
      this.saldoInicial = saldoInicial;
      this.clienteId = clienteId;
      this.estado = estado;
   }

   // Getters and Setters
   public String getNumeroCuenta() {
      return numeroCuenta;
   }

   public void setNumeroCuenta(String numeroCuenta) {
      this.numeroCuenta = numeroCuenta;
   }

   public String getTipo() {
      return tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public Double getSaldoInicial() {
      return saldoInicial;
   }

   public void setSaldoInicial(Double saldoInicial) {
      this.saldoInicial = saldoInicial;
   }

   public Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(Long clienteId) {
      this.clienteId = clienteId;
   }

   public String getEstado() {
      return estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   @Override
   public String toString() {
      return "CuentaCreateRequestDTO{" +
            "numeroCuenta='" + numeroCuenta + '\'' +
            ", tipo='" + tipo + '\'' +
            ", saldoInicial=" + saldoInicial +
            ", clienteId=" + clienteId +
            ", estado='" + estado + '\'' +
            '}';
   }
}