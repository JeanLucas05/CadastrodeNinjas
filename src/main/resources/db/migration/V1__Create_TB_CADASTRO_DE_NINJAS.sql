-- V1 : Criar tabela de missoes

CREATE TABLE tb_missoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nomedamissao VARCHAR(255),
    dificuldade VARCHAR(255)
);

-- V1 : Criar tabela de ninjas

CREATE TABLE tb_cadastro_de_ninjas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    idade INT,
    missoes_id BIGINT,

    CONSTRAINT fk_ninja_missao
        FOREIGN KEY (missoes_id)
        REFERENCES tb_missoes(id)
);