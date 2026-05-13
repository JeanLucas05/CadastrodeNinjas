package dev.java10x.CadastrodeNinjas.Execptions;

import dev.java10x.CadastrodeNinjas.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(NinjaNotFoundException.class)
    public ResponseEntity<ApiResponse> handleNinjaNotFoundException (NinjaNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse((ex.getMessage()), null));
    }

    @ExceptionHandler(MissaoNotFoundException.class)
    public ResponseEntity<ApiResponse> handleMissaoNotFoundException (MissaoNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(ex.getMessage(), null));
    }
}
