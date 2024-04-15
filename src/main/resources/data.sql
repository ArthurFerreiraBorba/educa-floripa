INSERT INTO papeis (id, nome)
VALUES
(1, 'ADM'),
(2, 'PEDAGOGICO'),
(3, 'RECRUITER'),
(4, 'PROFESSOR'),
(5, 'ALUNO')
ON CONFLICT (id) DO NOTHING;


INSERT INTO usuarios (login, senha, papel_id)
SELECT 'admin', '$2a$12$1lfFh4oMZCxZhGrWpQ7Kyelr7nEGYC/EoZmNrtMbQJYWJgTsWfCJ.', 1
WHERE NOT EXISTS (
    SELECT 1 FROM usuarios WHERE papel_id = 1
);

INSERT INTO docentes (nome, usuario_id)
SELECT 'admin', id
FROM usuarios
WHERE login = 'admin'
AND NOT EXISTS (
    SELECT 1 FROM docentes WHERE usuario_id = 1
);