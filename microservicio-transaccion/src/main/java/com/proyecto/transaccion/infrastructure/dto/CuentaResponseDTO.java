package com.proyecto.transaccion.infrastructure.dto;

public class CuentaResponseDTO {
   private Long id;
   private String numeroCuenta;
   private String tipo;
   private Double saldoInicial;
   private String estado;
   private Long clienteId;

   public CuentaResponseDTO() {
   }

   public CuentaResponseDTO(Long id, String numeroCuenta, String tipo, Double saldoInicial, String estado,
         Long clienteId) {
      this.id = id;
      this.numeroCuenta = numeroCuenta;
      this.tipo = tipo;
      this.saldoInicial = saldoInicial;
      this.estado = estado;
      this.clienteId = clienteId;
   }

   // Getters and Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

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

   public void setSaldoCan(Double saldoInicial) {
      this.saldoInicial = saldoInicial;
   }

   public String getEstado() {
      return estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(Long clienteId) {
      this.clienteId = clienteId;
   }
}