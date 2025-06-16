INSERT INTO endereco (logradouro, numero, bairro, cep, cidade, estado)
VALUES ('Rua Exemplo', '100', 'Centro', '12345678', 'Cidade', 'SP');

INSERT INTO usuario (nome, cpf, email, senha, sexo, telefone, data_nascimento, ativo, id_role, id_endereco, data_criacao)
VALUES (
  'Alexandre Delas',
  '12345678901',
  'gerente@exemplo.com',
  '$2a$12$P3W4y91hnOeOeAfTJT8IRe6tR9yar7xTnjXjlyo95fCYAdT4muKYi',
  'M',
  '11999999999',
  '1980-01-01',
  true,
  1,
  2,
  NOW()
);

INSERT INTO gerente (id_usuario, codigo_autorizacao, data_inicio_gestao)
VALUES (1, 'AUTORIZACAO123', '2024-06-15');