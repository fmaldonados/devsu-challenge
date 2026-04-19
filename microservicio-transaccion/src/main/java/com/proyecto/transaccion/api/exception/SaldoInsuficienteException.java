package com.proyecto.transaccion.domain.exception;

public class SaldoInsuficienteException extends RuntimeException {
   public SaldoInsuficienteException(String message) {
      super(message);
   }
}