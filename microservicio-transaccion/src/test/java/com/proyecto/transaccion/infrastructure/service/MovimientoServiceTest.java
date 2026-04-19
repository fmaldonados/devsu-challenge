package com.proyecto.transaccion.infrastructure.service;

import com.proyecto.transaccion.domain.Cuenta;
import com.proyecto.transaccion.domain.Movimiento;
import com.proyecto.transaccion.infrastructure.dto.MovimientoCreateRequestDTO;
import com.proyecto.transaccion.infrastructure.repository.CuentaRepository;
import com.proyecto.transaccion.infrastructure.repository.MovimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovimientoServiceTest {

   @Mock
   private CuentaRepository cuentaRepository;

   @Mock
   private MovimientoRepository movimientoRepository;

   @InjectMocks
   private MovimientoService movimientoService;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   void registrarMovimiento_Deposito_DebeAumentarSaldo() {
      // Given
      Long cuentaId = 1L;
      Cuenta cuenta = new Cuenta();
      cuenta.setId(cuentaId);
      cuenta.setSaldoInicial(100.0);

      // Un depósito de 50 debe resultar en 150
      MovimientoCreateRequestDTO request = new MovimientoCreateRequestDTO(cuentaId, "DEPOSITO", 50.0);

      when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.of(cuenta));
      when(movimientoRepository.save(any(Movimiento.class))).thenAnswer(invocation -> invocation.getArgument(0));

      // When
      Movimiento resultado = movimientoService.registrarMovimiento(request);

      // Then
      assertNotNull(resultado);
      assertEquals(150.0, resultado.getSaldoResultante());
      verify(movimientoRepository, times(1)).save(any(Movimiento.class));
   }

   @Test
   void registrarMovimiento_Compra_DebeDisminuirSaldo() {
      // Given
      Long cuentaId = 1L;
      Cuenta cuenta = new Cuenta();
      cuenta.setId(cuentaId);
      cuenta.setSaldoInicial(100.0);

      // Una compra de 40 debe resultar en 60
      MovimientoCreateRequestDTO request = new MovimientoCreateRequestDTO(cuentaId, "COMPRA", 40.0);

      when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.of(cuenta));
      when(movimientoRepository.save(any(Movimiento.class))).thenAnswer(invocation -> invocation.getArgument(0));

      // When
      Movimiento resultado = movimientoService.registrarMovimiento(request);

      // Then
      assertNotNull(resultado);
      assertEquals(60.0, resultado.getSaldoResultante());
      verify(movimientoRepository, times(1)).save(any(Movimiento.class));
   }

   @Test
   void registrarMovimiento_Compra_SaldoInsuficiente_DebeLanzarExcepcion() {
      // Given
      Long cuentaId = 1L;
      Cuenta cuenta = new Cuenta();
      cuenta.setId(cuentaId);
      cuenta.setSaldoInicial(10.0);

      // Intentar comprar algo de 40 con solo 10 de saldo
      MovimientoCreateRequestDTO request = new MovimientoCreateRequestDTO(cuentaId, "COMPRA", 40.0);

      when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.of(cuenta));

      // When & Then
      assertThrows(RuntimeException.class, () -> movimientoService.registrarMovimiento(request));
   }

   @Test
   void registrarMovimiento_TipoInvalido_DebeLanzarExcepcion() {
      // Given
      Long cuentaId = 1L;
      Cuenta cuenta = new Cuenta();
      cuenta.setId(cuentaId);
      cuenta.setSaldoInicial(100.0);

      MovimientoCreateRequestDTO request = new MovimientoCreateRequestDTO(cuentaId, "TIPO_INEXISTENTE", 10.0);

      when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.of(cuenta));

      // When & Then
      assertThrows(IllegalArgumentException.class, () -> movimientoService.registrarMovimiento(request));
   }
}