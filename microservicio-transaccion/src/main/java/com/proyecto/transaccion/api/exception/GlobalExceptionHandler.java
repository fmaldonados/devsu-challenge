package com.proyecto.transcan.api.exception;

import com.proyecto.transaccion.api.dto.ErrorResponseDTO;
import com.proyecto.transaccion.domain.exception.ClientServiceUnavailableException;
import com.proyecto.transaccion.domain.exception.SaldoInsuficienteException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

   // 1. 404 - No encontrado (Ej: Cuenta no existe)
   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(
         EntityNotFoundException ex, WebRequest request) {
      return createErrorResponse(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage());
   }

   // 2. 400 - Error de negocio (Ej: Saldo insuficiente)
   @ExceptionHandler(SaldoInsuficienteException.class)
   public ResponseEntity<ErrorResponseDTO> handleSaldoInsuficienteException(
         SaldoInsuficienteException ex) {
      return createErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage());
   }

   // 3. 503 - Error de dependencia (Ej: Microservicio Cliente caído)
   @ExceptionHandler(ClientServiceUnavailableException.class)
   public ResponseEntity<ErrorResponseDTO> handleClientServiceUnavailable(
         ClientServiceUnavailableException ex) {
      return createErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, "Service Unavailable", ex.getMessage());
   }

   // 4. 400 - Errores de validación (@Valid)
   @ExceptionHandler(BindException.class)
   public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(BindException ex) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
         String fieldName = ((FieldError) error).getField();
         String errorMessage = error.getDefaultMessage();
         errors.put(fieldName, errorMessage);
      });

      ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            "Validation failed",
            errors);
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
   }

   // 5. 500 - Error genérico
   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex) {
      return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
            "An unexpected error occurred");
   }

   private ResponseEntity<ErrorResponseDTO> createErrorResponse(HttpStatus status, String error, String message) {
      ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            LocalDateTime.now(),
            status.value(),
            error,
            message);
      return new ResponseEntity<>(errorResponse, status);
   }
}