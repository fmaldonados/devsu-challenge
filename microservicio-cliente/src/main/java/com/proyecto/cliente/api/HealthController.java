package com.proyecto.cliente.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class HealthController {

   @GetMapping("/")
   public ResponseEntity<String> healthCheck() {
      // Devuelve un 200 OK con un mensaje simple
      return ResponseEntity.ok("Service is up and running");
   }
}