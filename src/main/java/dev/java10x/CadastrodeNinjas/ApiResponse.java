package dev.java10x.CadastrodeNinjas;

public class ApiResponse {
    private String message;
    private Object dados;

    public ApiResponse(String message, Object dados) {
        this.message = message;
        this.dados = dados;
    }

    public String getMessage() {
        return message;
    }

    public Object getDados() {
        return dados;
    }
}
