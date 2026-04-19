package com.proyecto.cliente.api.exception;

import com.proyecto.cliente.api.dto.ErrorResponseDTO;
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

   // 1. Manejo de 404 - Entidad no encontrada
   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(
         EntityNotFoundException ex, WebRequest request) {
      ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage());
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
   }

   // 2. Manejo de 400 - Errores de validación (@Valid)
   @ExceptionHandler(BindException.class)
   public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(
         BindException ex) {
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

   // 3. Manejo de 400 - Otros errores de argumento
   // (MethodArgumentNotValidException, etc)
   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(
         IllegalArgumentException ex, WebRequest request) {
      ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            ex.getMessage());
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
   }

   // 4. Manejo de 500 - Errores genéricos del servidor
   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponseDTO> handleGlobalException(
         Exception ex, WebRequest request) {
      ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "An unexpected error occurred");

      return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
   }
}