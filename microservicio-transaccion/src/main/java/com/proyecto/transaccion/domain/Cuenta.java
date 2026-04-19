package com.proyecto.transaccion.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cuenta")
public class Cuenta {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "numero_cuenta", unique = true, nullable = false)
   private String numeroCuenta;

   @Column(nullable = false)
   private String tipo; // AHORRO, CORRIENTE

   @Column(name = "saldo_inicial", nullable = false)
   private Double saldoInicial;

   @Column(nullable = false)
   private String estado; // ACTIVA, INACTIVA, BLOQUEADA

   @Column(name = "cliente_id", nullable = false)
   private Long clienteId;

   public Cuenta() {
   }

   public Cuenta(Long id, String numeroCuenta, String tipo, Double saldoInicial, String estado, Long clienteId) {
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

   public void setSaldoInicial(Double saldoInicial) {
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

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (null == o)
         return false;
      if (getClass() != o.getClass())
         return false;
      Cuenta cuenta = (Cuenta) o;
      return Objects.equals(id, cuenta.id) &&
            Objects.equals(numeroCuenta, cuenta.numeroCuenta);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, numeroCuenta);
   }

   @Override
   public String toString() {
      return "Cuenta{" +
            "id=" + id +
            ", numeroCuenta='" + numeroCuenta + '\'' +
            ", tipo='" + tipo + '\'' +
            ", saldoInicial=" + saldoInicial +
            ", estado='" + estado + '\'' +
            ", clienteId=" + clienteId +
            '}';
   }

   public Double getSaldoActual() {

      return saldoInicial;
   }
}