CREATE TABLE IF NOT EXISTS papeis (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    login VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(60) NOT NULL UNIQUE,
    papel_id BIGINT NOT NULL REFERENCES papeis(id)
);

CREATE TABLE IF NOT EXISTS docentes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_entrada DATE NOT NULL DEFAULT current_date,
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS cursos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS materias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL UNIQUE,
    curso_id BIGINT NOT NULL REFERENCES cursos(id)
);

CREATE TABLE IF NOT EXISTS turmas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    professor_id BIGINT NOT NULL REFERENCES docentes(id),
    curso_id BIGINT NOT NULL REFERENCES cursos(id)
);

CREATE TABLE IF NOT EXISTS alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_nascimento DATE NOT NULL,
    turma_id BIGINT NOT NULL REFERENCES turmas(id),
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS notas (
    id SERIAL PRIMARY KEY,
    valor NUMERIC(5, 2) NOT NULL DEFAULT 0.00,
    data_criacao DATE NOT NULL DEFAULT current_date,
    aluno_id BIGINT NOT NULL REFERENCES alunos(id),
    professor_id BIGINT NOT NULL REFERENCES docentes(id),
    materia_id BIGINT NOT NULL REFERENCES materias(id)
);