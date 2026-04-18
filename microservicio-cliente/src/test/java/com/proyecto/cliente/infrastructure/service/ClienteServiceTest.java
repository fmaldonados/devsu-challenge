package com.proyecto.cliente.infrastructure.service;

import com.proyecto.cliente.domain.Cliente;
import com.proyecto.cliente.infrastructure.dto.ClienteCreateRequestDTO;
import com.proyecto.cliente.infrastructure.dto.ClienteResponseDTO;
import com.proyecto.cliente.infrastructure.dto.ClienteUpdateRequestDTO;
import com.proyecto.cliente.infrastructure.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

   @Mock
   private ClienteRepository clienteRepository;

   @InjectMocks
   private ClienteService clienteService;

   @Test
   void createCliente_Success() {
      // Arrange
      ClienteCreateRequestDTO dto = new ClienteCreateRequestDTO(
            "Juan Perez", "Masculino", 30, "12345678", "Calle Falsa 123", "123456789", "password123", "ACTIVO");

      Cliente savedCliente = new Cliente(1L, "Juan Perez", "Masculino", 30, "12345678", "Calle 123",
            "123456789", "password123", "ACTIVO");

      when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCliente);

      // Act
      Cliente result = clienteService.createCliente(dto);

      // Assert
      assertNotNull(result);
      assertEquals("Juan Perez", result.getNombre());
      verify(clienteRepository).save(any(Cliente.class));
   }

   @Test
   void getAllClientes_Success() {
      // Arrange
      Cliente cliente1 = new Cliente(1L, "Juan", "M", 30, "1", "Dir1", "123", "pass", "ACTIVO");
      Cliente cliente2 = new Cliente(2L, "Maria", "F", 25, "2", "Dir2", "456", "pass", "ACTIVO");

      when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

      // Act
      List<ClienteResponseDTO> result = clienteService.getAllClientes();

      // Assert
      assertEquals(2, result.size());
      assertEquals("Juan", result.get(0).getNombre());
      assertEquals("Maria", result.get(1).getNombre());
   }

   @Test
   void getClienteById_Success() {
      // Arrange
      Cliente cliente = new Cliente(1L, "Juan", "M", 30, "1", "Dir1", "123", "pass", "ACTIVO");
      when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

      // Act
      ClienteResponseDTO result = clienteService.getClienteById(1L);

      // Assert
      assertNotNull(result);
      assertEquals(1L, result.getId());
      assertEquals("Juan", result.getNombre());
   }

   @Test
   void getClienteById_NotFound_ThrowsException() {
      // Arrange
      when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

      // Act & Assert
      RuntimeException exception = assertThrows(RuntimeException.class, () -> {
         clienteService.getClienteById(99L);
      });

      assertTrue(exception.getMessage().contains("Cliente no encontrado con ID: 99"));
   }

   @Test
   void updateCliente_NotFound_ThrowsException() {
      // Arrange
      Long clienteId = 99L;
      ClienteUpdateRequestDTO updateDto = new ClienteUpdateRequestDTO("pass", "ACTIVO");
      when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

      // Act & Assert
      RuntimeException exception = assertThrows(RuntimeException.class, () -> {
         clienteService.updateCliente(clienteId, updateDto);
      });

      assertTrue(exception.getMessage().contains("Cliente no encontrado con ID: 99"));
   }

   @Test
   void deleteCliente_Success() {
      // Arrange
      Long clienteId = 1L;
      Cliente clienteExistente = new Cliente(1L, "Juan", "M", 30, "1", "Dir1", "123", "pass", "ACTIVO");

      lenient().when(clienteRepository.existsById(anyLong())).thenReturn(true);
      doNothing().when(clienteRepository).deleteById(anyLong());

      // Act
      clienteService.deleteCliente(clienteId);
      // Assert
      verify(clienteRepository, times(1)).deleteById(clienteId);
   }

   @Test
   void deleteCliente_NotFound_ThrowsException() {
      // Arrange
      Long clienteId = 99L;
      lenient().when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

      // Act & Assert
      RuntimeException exception = assertThrows(RuntimeException.class, () -> {
         clienteService.deleteCliente(clienteId);
      });

      assertTrue(exception.getMessage().contains("Cliente no encontrado con ID: 99"));
      verify(clienteRepository, never()).deleteById(anyLong());
   }
}