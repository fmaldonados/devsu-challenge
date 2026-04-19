package com.proyecto.transaccion.api;

import com.proyecto.transaccion.infrastructure.dto.CuentaCreateRequestDTO;
import com.proyecto.transaccion.infrastructure.dto.CuentaResponseDTO;
import com.proyecto.transaccion.infrastructure.dto.CuentaUpdateRequestDTO;
import com.proyecto.transaccion.infrastructure.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaController {

   private final CuentaService cuentaService;

   public CuentaController(CuentaService cuentaService) {
      this.cuentaService = cuentaService;
   }

   @PostMapping
   public ResponseEntity<CuentaResponseDTO> createCuenta(@Valid @RequestBody CuentaCreateRequestDTO dto) {
      return new ResponseEntity<>(cuentaService.createCuenta(dto), HttpStatus.CREATED);
   }

   @GetMapping
   public ResponseEntity<List<CuentaResponseDTO>> getAllCuentas() {
      return ResponseEntity.ok(cuentaService.getAllCuentas());
   }

   @GetMapping("/{numeroCuenta}")
   public ResponseEntity<CuentaResponseDTO> getCuentaByNumero(@PathVariable String numeroCuenta) {
      return ResponseEntity.ok(cuentaService.getCuentaByNumero(numeroCuenta));
   }

   @PutMapping("/{id}")
   public ResponseEntity<CuentaResponseDTO> updateCuenta(
         @PathVariable Long id,
         @Valid @RequestBody CuentaUpdateRequestDTO dto) {
      return ResponseEntity.ok(cuentaService.updateCuenta(id, dto));
   }

}