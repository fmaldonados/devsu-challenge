package com.proyecto.cliente.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nombre;
   private String genero;
   private Integer edad;
   private String identificacion;
   private String direccion;
   private String telefono;

   // Constructor vacío requerido por JPA
   public Persona() {
   }

   // Constructor con todos los campos

   public Persona(Long id, String nombre, String genero, Integer edad, String identificacion, String direccion,
         String telefono) {
      this.id = id;
      this.nombre = nombre;
      this.genero = genero;
      this.edad = edad;
      this.identificacion = identificacion;
      this.direccion = direccion;
      this.telefono = telefono;
   }

   // Getters y Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

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

   // equals, hashCode y toString
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Persona persona = (Persona) o;
      return Objects.equals(id, persona.id) &&
            Objects.equals(identificacion, persona.identificacion);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, identificacion);
   }

   @Override
   public String toString() {
      return "Persona{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", identificacion='" + identificacion + '\'' +
            '}';
   }
}