package com.proyecto.transaccion.infrastructure.service;

import com.proyecto.transaccion.domain.Cuenta;
import com.proyecto.transaccion.infrastructure.dto.CuentaCreateRequestDTO;
import com.proyecto.transaccion.infrastructure.dto.CuentaResponseDTO;
import com.proyecto.transaccion.infrastructure.dto.CuentaUpdateRequestDTO;
import com.proyecto.transaccion.infrastructure.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
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
class CuentaServiceTest {

   @Mock
   private CuentaRepository cuentaRepository;

   @InjectMocks
   private CuentaService cuentaService;

   private Cuenta cuentaEjemplo;
   private CuentaCreateRequestDTO createDto;

   @BeforeEach
   void setUp() {
      cuentaEjemplo = new Cuenta(1L, "123456", "AHORRO", 1000.0, "ACTIVA", 10L);
      createDto = new CuentaCreateRequestDTO("123456", "AHORRO", 1000.0, 10L, "ACTIVA");
   }

   @Test
   void createCuenta_Success() {
      when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuentaEjemplo);

      CuentaResponseDTO response = cuentaService.createCuenta(createDto);

      assertNotNull(response);
      assertEquals("123456", response.getNumeroCuenta());
      assertEquals(1000.0, response.getSaldoInicial());
      verify(cuentaRepository, times(1)).save(any(Cuenta.class));
   }

   @Test
   void getAllCuentas_Success() {
      when(cuentaRepository.findAll()).thenReturn(Arrays.asList(cuentaEjemplo));

      List<CuentaResponseDTO> response = cuentaService.getAllCuentas();

      assertFalse(response.isEmpty());
      assertEquals(1, response.size());
      assertEquals("123456", response.get(0).getNumeroCuenta());
   }

   @Test
   void getCuentaByNumero_Success() {
      when(cuentaRepository.findByNumeroCuenta("123456")).thenReturn(Optional.of(cuentaEjemplo));

      CuentaResponseDTO response = cuentaService.getCuentaByNumero("123456");

      assertNotNull(response);
      assertEquals("123456", response.getNumeroCuenta());
   }

   @Test
   void getCuentaByNumero_NotFound_ThrowsException() {
      when(cuentaRepository.findByNumeroCuenta("000000")).thenReturn(Optional.empty());

      assertThrows(RuntimeException.class, () -> {
         cuentaService.getCuentaByNumero("000000");
      });
   }

   @Test
    void updateCuenta_Success() {
        CuentaUpdateRequestDTO updateDto = new CuentaUpdateRequestDTO("CORRIENTE", "INACTIVA");
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaEjemplo));
        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuentaEjemplo);

        CuentaResponseDTO response = cuentaService.updateCuenta(1L, updateDto);

        assertEquals("CORRIENTE", response.getTipo());
        assertEquals("INACTIVA", response.getEstado());
        verify(cuentaRepository).save(any(Cuenta.class));
    }
}