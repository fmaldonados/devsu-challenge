package com.proyecto.transaccion.infrastructure.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ClienteServiceClient {

   private final RestTemplate restTemplate;
   private final String clienteServiceUrl;

   public ClienteServiceClient(RestTemplate restTemplate,
         @Value("${microservicios.cliente.url}") String clienteServiceUrl) {
      this.restTemplate = restTemplate;
      this.clienteServiceUrl = clienteServiceUrl;
   }

   public boolean clienteExisteYEstaActivo(Long clienteId) {
      String url = clienteServiceUrl + "/api/v1/clientes/" + clienteId;
      try {

         System.out.println("[DEBUG] Consultando microservicio de clientes en URL: " + url);

         ClienteResponseDTO cliente = restTemplate.getForObject(
               url,
               ClienteResponseDTO.class);

         if (cliente == null) {
            System.out.println("[DEBUG] El microservicio de clientes respondió con cuerpo nulo.");
            return false;
         }

         boolean estaActivo = "ACTIVO".equalsIgnoreCase(cliente.getEstado());
         System.out.println("[DEBUG] Cliente ID " + clienteId + " encontrado. Estado: " + cliente.getEstado()
               + " -> Activo: " + estaActivo);

         return estaActivo;
      } catch (Exception e) {
         // Log detallado del error para debuggear la causa raíz (Connection refused,
         // 404, etc.)
         System.err.println("[ERROR DEBUG] Error crítico al llamar al microservicio de clientes!");
         System.err.println("[ERROR DEBUG] URL fallida: " + url);
         System.err.println("[ERROR DEBUG] Mensaje de la excepción: " + e.getMessage());
         e.printStackTrace();

         throw new RuntimeException("Error al consultar el microservicio de clientes: " + e.getMessage(), e);
      }
   }

   private static class ClienteResponseDTO {
      private Long id;
      private String nombre;
      private String estado;

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

      public String getEstado() {
         return estado;
      }

      public void setEstado(String estado) {
         this.estado = estado;
      }
   }
}