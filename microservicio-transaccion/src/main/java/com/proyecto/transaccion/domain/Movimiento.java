package com.proyecto.transaccion.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "movimiento")
public class Movimiento {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private LocalDateTime fecha;

   @Column(name = "tipo_movimiento", nullable = false)
   private String tipoMovimiento; // DEBITO, CREDITO

   @Column(nullable = false)
   private Double valor;

   @Column(name = "saldo_resultante", nullable = false)
   private Double saldoResultante;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "cuenta_id", nullable = false)
   private Cuenta cuenta;

   public Movimiento() {
   }

   public Movimiento(Long id, LocalDateTime fecha, String tipoMovimiento, Double valor, Double saldoResultante,
         Cuenta cuenta) {
      this.id = id;
      this.fecha = fecha;
      this.tipoMovimiento = tipoMovimiento;
      this.valor = valor;
      this.saldoResultante = saldoResultante;
      this.cuenta = cuenta;
   }

   // Getters and Setters
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

   public Double getValor() {
      return valor;
   }

   public void setValor(Double valor) {
      this.valor = valor;
   }

   public Double getSaldoResultante() {
      return saldoResultante;
   }

   public void setSaldoResultante(Double saldoResultante) {
      this.saldoResultante = saldoResultante;
   }

   public Cuenta getCuenta() {
      return cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (null == o)
         return false;
      if (getClass() != o.getClass())
         return false;
      Movimiento that = (Movimiento) o;
      return Objects.equals(id, that.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Movimiento{" +
            "id=" + id +
            ", fecha=" + fecha +
            ", tipoMovimiento='" + tipoMovimiento + '\'' +
            ", valor=" + valor +
            ", saldoResultante=" + saldoResultante +
            ", cuentaId=" + (cuenta != null ? cuenta.getId() : "null") +
            '}';
   }
}