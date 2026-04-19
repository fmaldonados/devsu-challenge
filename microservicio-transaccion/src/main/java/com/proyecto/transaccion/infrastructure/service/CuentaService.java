package com.proyecto.transaccion.infrastructure.service;

import com.proyecto.transaccion.domain.Cuenta;
import com.proyecto.transaccion.infrastructure.dto.*;
import com.proyecto.transaccion.infrastructure.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {

   private final CuentaRepository cuentaRepository;

   public CuentaService(CuentaRepository cuentaRepository) {
      this.cuentaRepository = cuentaRepository;
   }

   @Transactional
   public CuentaResponseDTO createCuenta(CuentaCreateRequestDTO dto) {
      Cuenta cuenta = new Cuenta();
      cuenta.setNumeroCuenta(dto.getNumeroCuenta());
      cuenta.setTipo(dto.getTipo());
      cuenta.setSaldoInicial(dto.getSaldoInicial());
      cuenta.setClienteId(dto.getClienteId());
      cuenta.setEstado(dto.getEstado());

      Cuenta saved = cuentaRepository.save(cuenta);
      return mapToDTO(saved);
   }

   @Transactional(readOnly = true)
   public List<CuentaResponseDTO> getAllCuentas() {
      return cuentaRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
   }

   @Transactional(readOnly = true)
   public CuentaResponseDTO getCuentaByNumero(String numeroCuenta) {
      Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + numeroCuenta));
      return mapToDTO(cuenta);
   }

   @Transactional
   public CuentaResponseDTO updateCuenta(Long id, CuentaUpdateRequestDTO dto) {
      Cuenta cuenta = cuentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + id));

      cuenta.setTipo(dto.getTipo());
      cuenta.setEstado(dto.getEstado());

      return mapToDTO(cuentaRepository.save(cuenta));
   }

   private CuentaResponseDTO mapToDTO(Cuenta cuenta) {
      return new CuentaResponseDTO(
            cuenta.getId(),
            cuenta.getNumeroCuenta(),
            cuenta.getTipo(),
            cuenta.getSaldoInicial(),
            cuenta.getEstado(),
            cuenta.getClienteId());
   }
}