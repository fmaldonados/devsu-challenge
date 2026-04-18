package com.proyecto.transaccion.api.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class HealthController {

   @GetMapping("/")
   public ResponseEntity<String> healthCheck() {
      return ResponseEntity.ok("Microservicio de Transacción está disponible");
   }
}