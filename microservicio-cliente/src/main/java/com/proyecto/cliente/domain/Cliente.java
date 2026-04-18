package com.proyecto.cliente.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class Cliente extends Persona {

   private String contrasena;
   private String estado;

   public Cliente() {
      super();
   }

   public Cliente(Long id, String nombre, String genero, Integer edad, String identificacion, String direccion,
         String telefono, String contrasena, String estado) {
      super(id, nombre, genero, edad, identificacion, direccion, telefono);
      this.contrasena = contrasena;
      this.estado = estado;
   }

   public String getContrasena() {
      return contrasena;
   }

   public void setContrasena(String contrasena) {
      this.contrasena = contrasena;
   }

   public String getEstado() {
      return estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      if (!super.equals(o))
         return false;
      Cliente cliente = (Cliente) o;
      return Objects.equals(contrasena, cliente.contrasena) &&
            Objects.equals(estado, cliente.estado);
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), contrasena, estado);
   }

   @Override
   public String toString() {
      return "Cliente{" +
            "estado='" + estado + '\'' +
            ", " + super.toString() +
            '}';
   }
}