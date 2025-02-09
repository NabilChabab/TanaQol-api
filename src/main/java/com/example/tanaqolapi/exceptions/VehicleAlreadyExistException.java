package com.example.tanaqolapi.exceptions;

public class VehicleAlreadyExistException extends RuntimeException {
    public VehicleAlreadyExistException(String message) {
        super(message);
    }
}
