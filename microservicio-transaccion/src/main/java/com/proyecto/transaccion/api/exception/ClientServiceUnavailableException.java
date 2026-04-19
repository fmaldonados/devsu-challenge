package com.proyecto.transaccion.domain.exception;

public class ClientServiceUnavailableException extends RuntimeException {
   public ClientServiceUnavailableException(String message) {
      super(message);
   }
}