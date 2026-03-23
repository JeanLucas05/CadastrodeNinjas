-- V3 : Migration para adicionar a coluna de imgUrl na tabela de cadastros

ALTER TABLE tb_cadastro_de_ninjas
ADD COLUMN imgurl VARCHAR(255);