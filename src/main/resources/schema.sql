DROP SEQUENCE IF EXISTS seq_disciplina;
CREATE SEQUENCE seq_disciplina START WITH 1 INCREMENT BY 1;

-- Garantir que a tabela disciplina use a sequência correta
DROP TABLE IF EXISTS disciplina CASCADE;
CREATE TABLE disciplina (
    id BIGINT DEFAULT nextval('seq_disciplina') PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    ano VARCHAR(255),
    periodo VARCHAR(255),
    descricao TEXT,
    carga_horaria INTEGER
);
