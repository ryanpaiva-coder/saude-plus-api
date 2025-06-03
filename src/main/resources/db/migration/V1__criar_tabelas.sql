CREATE TABLE endereco (
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    bairro VARCHAR(50),
    cep VARCHAR(8),
    cidade VARCHAR(50),
    estado VARCHAR(2)
);

CREATE TABLE clinica (
    id_clinica INT PRIMARY KEY AUTO_INCREMENT,
    cnpj_clinica VARCHAR(14),
    nome_fantasia VARCHAR(100),
    telefone VARCHAR(11),
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES endereco(id_endereco)
);

CREATE TABLE role (
    id_role INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(64) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    sexo CHAR(1) CHECK (sexo IN ('M', 'F')) NOT NULL,
    telefone VARCHAR(11),
    data_nascimento DATE NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    id_role INT,
    id_endereco INT,
    FOREIGN KEY (id_role) REFERENCES role(id_role),
    FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco)
);

CREATE TABLE especialidade (
    id_especialidade INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE medico (
    id_usuario INT PRIMARY KEY,
    crm VARCHAR(12),
    id_clinica INT,
    id_especialidade INT,
    FOREIGN KEY (id_clinica) REFERENCES clinica(id_clinica),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_especialidade) REFERENCES especialidade(id_especialidade)
);

CREATE TABLE paciente (
    id_usuario INT PRIMARY KEY,
    descricao TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE consulta (
    id_consulta INT PRIMARY KEY AUTO_INCREMENT,
    inicio TIMESTAMP,
    valor DECIMAL(10,2),
    diagnostico TEXT,
    observacao TEXT,
    id_medico INT,
    id_paciente INT,
    status VARCHAR(20) NOT NULL DEFAULT 'AGENDADA',
    FOREIGN KEY (id_medico) REFERENCES medico(id_usuario),
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_usuario)
);
