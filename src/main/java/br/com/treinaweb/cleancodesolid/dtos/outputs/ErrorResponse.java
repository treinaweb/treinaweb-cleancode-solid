package br.com.treinaweb.cleancodesolid.dtos.outputs;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp;

    private String status;

    private String mensagem;

    public ErrorResponse() {}

    public ErrorResponse(LocalDateTime timestamp, String status, String mensagem) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensagem = mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
