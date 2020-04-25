CREATE TABLE ordem_servico (
	id BIGINT NOT NULL AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    descricao TEXT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_abertura DATETIME NOT NULL,
    data_finalizacao DATETIME,

    PRIMARY KEY(id)
);

ALTER TABLE ordem_servico ADD CONSTRAINT FK_ORDEM_SERVICO_CLIENTE
FOREIGN KEY (cliente_id) REFERENCES cliente (id);
