package com.proyecto.cliente.api;

import com.proyecto.cliente.infrastructure.dto.ClienteCreateRequestDTO;
import com.proyecto.cliente.infrastructure.dto.ClienteUpdateRequestDTO;
import com.proyecto.cliente.infrastructure.dto.ClienteResponseDTO;
import com.proyecto.cliente.infrastructure.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

   private final ClienteService clienteService;

   public ClienteController(ClienteService clienteService) {
      this.clienteService = clienteService;
   }

   @PostMapping
   public ResponseEntity<Void> createCliente(@Valid @RequestBody ClienteCreateRequestDTO dto) {
      clienteService.createCliente(dto);
      return new ResponseEntity<>(HttpStatus.CREATED);
   }

   @PutMapping("/{id}")
   public ResponseEntity<ClienteResponseDTO> updateCliente(
         @PathVariable Long id,
         @Valid @RequestBody ClienteUpdateRequestDTO dto) {
      return ResponseEntity.ok(clienteService.updateCliente(id, dto));
   }

   @GetMapping
   public ResponseEntity<List<ClienteResponseDTO>> getAllClientes() {
      return ResponseEntity.ok(clienteService.getAllClientes());
   }

   @GetMapping("/{id}")
   public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Long id) {
      return ResponseEntity.ok(clienteService.getClienteById(id));
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
      clienteService.deleteCliente(id);
      return ResponseEntity.noContent().build();
   }

}