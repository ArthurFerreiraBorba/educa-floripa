INSERT INTO papeis (id, nome) VALUES (1, 'ADM') ON CONFLICT (id) DO NOTHING;
INSERT INTO papeis (id, nome) VALUES (2, 'PEDAGOGICO') ON CONFLICT (id) DO NOTHING;
INSERT INTO papeis (id, nome) VALUES (3, 'RECRUITER') ON CONFLICT (id) DO NOTHING;
INSERT INTO papeis (id, nome) VALUES (4, 'PROFESSOR') ON CONFLICT (id) DO NOTHING;
INSERT INTO papeis (id, nome) VALUES (5, 'ALUNO') ON CONFLICT (id) DO NOTHING;


INSERT INTO usuarios (id, login, senha, papel_id) VALUES (1, 'admin', '$2a$12$1lfFh4oMZCxZhGrWpQ7Kyelr7nEGYC/EoZmNrtMbQJYWJgTsWfCJ.', 1) ON CONFLICT (id) DO NOTHING;