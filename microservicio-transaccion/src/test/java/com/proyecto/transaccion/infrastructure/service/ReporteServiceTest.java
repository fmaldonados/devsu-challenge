package com.proyecto.transaccion.infrastructure.service;

import com.proyecto.transaccion.domain.Cuenta;
import com.proyecto.transaccion.domain.Movimiento;
import com.proyecto.transaccion.infrastructure.dto.MovimientoCreateRequestDTO;
import com.proyecto.transaccion.infrastructure.repository.CuentaRepository;
import com.proyecto.transaccion.infrastructure.repository.MovimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

   @Mock
   private CuentaRepository cuentaRepository;

   @Mock
   private MovimientoRepository movimientoRepository;

   @Mock
   private ClienteServiceClient clienteServiceClient;

   @InjectMocks
   private MovimientoService movimientoService;

   private Cuenta cuentaDummy;
   private MovimientoCreateRequestDTO requestDummy;

   @BeforeEach
   void setUp() {
      cuentaDummy = new Cuenta();
      cuentaDummy.setId(1L);
      cuentaDummy.setNumeroCuenta("123456");
      cuentaDummy.setSaldoInicial(1000.0);
      cuentaDummy.setEstado("ACTIVO");

      requestDummy = new MovimientoCreateRequestDTO();
      requestDummy.setCuentaId(1L);
      requestDummy.setValor(200.0);
      requestDummy.setTipoMovimiento("DEPOSITO");
   }

   @Test
   void registrarMovimiento_DeveSuccess_WhenDataIsValid() {
      when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaDummy));
      when(movimientoRepository.save(any(Movimiento.class))).thenAnswer(invocation -> invocation.getArgument(0));

      Movimiento result = movimientoService.registrarMovimiento(requestDummy);

      assertNotNull(result);
      assertEquals(1200.0, result.getSaldoResultante());
   }

   @Test
   void registrarMovimiento_ShouldThrowException_WhenSaldoInsuficiente() {
      requestDummy.setTipoMovimiento("COMPRA");
      requestDummy.setValor(2000.0);
      when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaDummy));

      RuntimeException exception = assertThrows(RuntimeException.class, () -> {
         movimientoService.registrarMovimiento(requestDummy);
      });

      assertTrue(exception.getMessage().contains("Saldo insuficiente"));
   }

   @Test
   void registrarMovimiento_DeveSuccess_WhenValueIsNegative() {
      requestDummy.setTipoMovimiento("DEPOSITO");
      requestDummy.setValor(-200.0);

      when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaDummy));
      when(movimientoRepository.save(any(Movimiento.class))).thenAnswer(invocation -> invocation.getArgument(0));

      Movimiento result = movimientoService.registrarMovimiento(requestDummy);

      assertEquals(800.0, result.getSaldoResultante());
   }

   @Test
   void obtenerReporte_ShouldThrowException_WhenClienteNotActive() {
      Long clienteId = 1L;

      when(clienteServiceClient.clienteExisteYEstaActivo(clienteId)).thenReturn(false);

      assertThrows(RuntimeException.class, () -> {
         movimientoService.obtenerReporte(LocalDateTime.now(), LocalDateTime.now(), clienteId);
      });
   }
}