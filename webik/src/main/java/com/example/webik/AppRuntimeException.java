package com.example.webik;

public class AppRuntimeException extends RuntimeException {
    @Override
    public String toString() {
        return "file not readable";
    }
}
