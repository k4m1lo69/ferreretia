package com.Ferreteria.ms_auth.dto;

public class ApiResponse {

    private String mensaje;

    public ApiResponse() {
    }

    public ApiResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}