package com.proyecto.transaccion.infrastructure.service;

import com.proyecto.transaccion.domain.Cuenta;
import com.proyecto.transaccion.domain.Movimiento;
import com.proyecto.transaccion.infrastructure.dto.MovimientoCreateRequestDTO;
import com.proyecto.transaccion.infrastructure.dto.MovimientoReporteDTO;
import com.proyecto.transaccion.infrastructure.repository.CuentaRepository;
import com.proyecto.transaccion.infrastructure.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService {

   private final CuentaRepository cuentaRepository;
   private final MovimientoRepository movimientoRepository;
   private final ClienteServiceClient clienteServiceClient;

   public MovimientoService(CuentaRepository cuentaRepository,
         MovimientoRepository movimientoRepository,
         ClienteServiceClient clienteServiceClient) {
      this.cuentaRepository = cuentaRepository;
      this.movimientoRepository = movimientoRepository;
      this.clienteServiceClient = clienteServiceClient;
   }

   @Transactional
   public Movimiento registrarMovimiento(MovimientoCreateRequestDTO request) {
      Cuenta cuenta = cuentaRepository.findById(request.getCuentaId())
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

      double valorMovimiento = request.getValor();
      double saldoAnterior = cuenta.getSaldoInicial();
      double nuevoSaldo;

      if ("DEPOSITO".equalsIgnoreCase(request.getTipoMovimiento())) {
         nuevoSaldo = saldoAnterior + valorMovimiento;
      } else if ("COMPRA".equalsIgnoreCase(request.getTipoMovimiento())) {
         nuevoSaldo = saldoAnterior - valorMovimiento;
      } else {
         throw new IllegalArgumentException("Tipo de movimiento no soportado: " + request.getTipoMovimiento());
      }

      if (nuevoSaldo < 0) {
         throw new RuntimeException("Saldo insuficiente: La operación dejaría la cuenta en negativo");
      }

      // Actualizamos el saldo de la cuenta
      cuenta.setSaldoInicial(nuevoSaldo);
      cuentaRepository.save(cuenta);

      Movimiento movimiento = new Movimiento();
      movimiento.setCuenta(cuenta);
      movimiento.setFecha(LocalDateTime.now());
      movimiento.setTipoMovimiento(request.getTipoMovimiento().toUpperCase());
      movimiento.setValor(valorMovimiento);
      movimiento.setSaldoResultante(nuevoSaldo);

      return movimientoRepository.save(movimiento);
   }

   public List<MovimientoReporteDTO> obtenerReporte(LocalDateTime fechaInicial,
         LocalDateTime fechaFin,
         Long clienteId) {
      if (!clienteServiceClient.clienteExisteYEstaActivo(clienteId)) {
         throw new RuntimeException("Cliente no válido o no se pudo verificar su ==su estado");
      }

      List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

      return cuentas.stream()
            .flatMap(cuenta -> movimientoRepository.findByCuentaIdAndFechaBetween(
                  cuenta.getId(), fechaInicial, fechaFin).stream())
            .map(m -> new MovimientoReporteDTO(
                  m.getId(), m.getFecha(), m.getTipoMovimiento(),
                  m.getValor(), m.getSaldoResultante(), m.getCuenta().getId()))
            .collect(Collectors.toList());
   }
}