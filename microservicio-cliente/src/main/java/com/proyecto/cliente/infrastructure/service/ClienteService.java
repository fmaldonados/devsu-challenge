package com.proyecto.cliente.infrastructure.service;

import com.proyecto.cliente.domain.Cliente;
import com.proyecto.cliente.infrastructure.dto.ClienteCreateRequestDTO;
import com.proyecto.cliente.infrastructure.dto.ClienteResponseDTO;
import com.proyecto.cliente.infrastructure.dto.ClienteUpdateRequestDTO;
import com.proyecto.cliente.infrastructure.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

   private final ClienteRepository clienteRepository;

   public ClienteService(ClienteRepository clienteRepository) {
      this.clienteRepository = clienteRepository;
   }

   @Transactional
   public Cliente createCliente(ClienteCreateRequestDTO dto) {
      Cliente cliente = new Cliente();
      cliente.setNombre(dto.getNombre());
      cliente.setGenero(dto.getGenero());
      cliente.setEdad(dto.getEdad());
      cliente.setIdentificacion(dto.getIdentificacion());
      cliente.setDireccion(dto.getDireccion());
      cliente.setTelefono(dto.getTelefono());
      cliente.setContrasena(dto.getContrasena());
      cliente.setEstado(dto.getEstado());

      return clienteRepository.save(cliente);
   }

   @Transactional
   public ClienteResponseDTO updateCliente(Long id, ClienteUpdateRequestDTO dto) {
      return clienteRepository.findById(id)
            .map(cliente -> {
               cliente.setContrasena(dto.getContrasena());
               cliente.setEstado(dto.getEstado());
               Cliente updated = clienteRepository.save(cliente);
               return mapToResponseDTO(updated);
            })
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
   }

   @Transactional(readOnly = true)
   public List<ClienteResponseDTO> getAllClientes() {
      return clienteRepository.findAll()
            .stream()
            .map(this::mapToResponseDTO)
            .collect(Collectors.toList());
   }

   @Transactional(readOnly = true)
   public ClienteResponseDTO getClienteById(Long id) {
      return clienteRepository.findById(id)
            .map(this::mapToResponseDTO)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
   }

   @Transactional
   public void deleteCliente(Long id) {
      if (!clienteRepository.existsById(id)) {
         throw new RuntimeException("Cliente no encontrado con ID: " + id);
      }
      clienteRepository.deleteById(id);
   }

   private ClienteResponseDTO mapToResponseDTO(Cliente cliente) {
      return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNombre(),
            cliente.getGenero(),
            cliente.getEdad(),
            cliente.getIdentificacion(),
            cliente.getDireccion(),
            cliente.getTelefono(),
            cliente.getEstado());
   }
}