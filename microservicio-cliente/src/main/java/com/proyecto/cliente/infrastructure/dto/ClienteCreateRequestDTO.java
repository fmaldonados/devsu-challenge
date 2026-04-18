package com.proyecto.cliente.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClienteCreateRequestDTO {

   @NotBlank(message = "El nombre es obligatorio")
   private String nombre;

   @NotBlank(message = "El género es obligatorio")
   private String genero;

   @NotNull(message = "La edad es obligatoria")
   private Integer edad;

   @NotBlank(message = "La identificación es obligatoria")
   private String identificacion;

   private String direccion;

   @NotBlank(message = "El teléfono es obligatorio")
   private String telefono;

   @NotBlank(message = "La contraseña es obligatoria")
   @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
   private String contrasena;

   @NotBlank(message = "El estado es obligatorio")
   private String estado;

   // Constructor vacío
   public ClienteCreateRequestDTO() {
   }

   // Constructor con todos los campos
   public ClienteCreateRequestDTO(String nombre, String genero, Integer edad, String identificacion,
         String direccion, String telefono, String contrasena, String estado) {
      this.nombre = nombre;
      this.genero = genero;
      this.edad = edad;
      this.identificacion = identificacion;
      this.direccion = direccion;
      this.telefono = telefono;
      this.contrasena = contrasena;
      this.estado = estado;
   }

   // Getters y Setters
   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getGenero() {
      return genero;
   }

   public void setGenero(String genero) {
      this.genero = genero;
   }

   public Integer getEdad() {
      return edad;
   }

   public void setEdad(Integer edad) {
      this.edad = edad;
   }

   public String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(String identificacion) {
      this.identificacion = identificacion;
   }

   public String getDireccion() {
      return direccion;
   }

   public void setDireccion(String direccion) {
      this.direccion = direccion;
   }

   public String getTelefono() {
      return telefono;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
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
}