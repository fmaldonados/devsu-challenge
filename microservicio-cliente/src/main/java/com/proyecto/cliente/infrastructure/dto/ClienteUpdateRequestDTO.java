package com.proyecto.cliente.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class ClienteUpdateRequestDTO {

   @NotBlank(message = "La contraseña no puede estar en blanco")
   private String contrasena;

   @NotNull(message = "El estado no puede ser nulo")
   private String estado;

   public ClienteUpdateRequestDTO() {
   }

   public ClienteUpdateRequestDTO(String contrasena, String estado) {
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
      if (o == null)
         return false;
      if (getClass() != o.getClass())
         return false;
      ClienteUpdateRequestDTO that = (ClienteUpdateRequestDTO) o;
      return Objects.equals(contrasena, that.contrasena) &&
            Objects.equals(estado, that.estado);
   }

   @Override
   public int hashCode() {
      return Objects.hash(contrasena, estado);
   }

   @Override
   public String toString() {
      return "ClienteUpdateRequestDTO{" +
            "contrasena='" + contrasena + '\'' +
            ", estado='" + estado + '\'' +
            '}';
   }
}