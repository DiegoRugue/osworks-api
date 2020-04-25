package com.algaworks.osworks.osworksapi.api.model;

import java.time.OffsetDateTime;

public class ComentarioModel {
    private String descricao;
    private OffsetDateTime dataEnvio;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OffsetDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(OffsetDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
