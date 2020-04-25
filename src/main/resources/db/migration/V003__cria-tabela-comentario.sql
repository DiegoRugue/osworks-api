CREATE TABLE comentario (
	id BIGINT NOT NULL AUTO_INCREMENT,
    ordem_servico_id BIGINT NOT NULL,
    descricao TEXT NOT NULL,
    data_envio DATETIME NOT NULL,

    PRIMARY KEY(id)
);

ALTER TABLE comentario ADD CONSTRAINT FK_COMENTARIO_ORDEM_SERVICO
FOREIGN KEY (ordem_servico_id) REFERENCES ordem_servico (id);
