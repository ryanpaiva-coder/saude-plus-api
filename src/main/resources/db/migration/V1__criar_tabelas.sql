CREATE TABLE Endereco (
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    bairro VARCHAR(50),
    cep VARCHAR(8),
    cidade VARCHAR(50),
    estado VARCHAR(2)
);

CREATE TABLE Clinica (
    cnpj_clinica VARCHAR(14) PRIMARY KEY,
    nome_fantasia VARCHAR(100),
    telefone VARCHAR(11),
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Role (
    id_role INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE Usuario (
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
    FOREIGN KEY (id_role) REFERENCES Role(id_role),
    FOREIGN KEY(id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Especialidade (
    id_especialidade INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE Medico (
    id_medico INT PRIMARY KEY,
    crm VARCHAR(12),
    cnpj_clinica VARCHAR(20),
    id_especialidade INT,
    FOREIGN KEY (cnpj_clinica) REFERENCES Clinica(cnpj_clinica),
    FOREIGN KEY (id_medico) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_especialidade) REFERENCES Especialidade(id_especialidade)
);

CREATE TABLE Paciente (
    id_paciente INT PRIMARY KEY,
    descricao TEXT,
    FOREIGN KEY (id_paciente) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Consulta (
    id_consulta INT PRIMARY KEY AUTO_INCREMENT,
    inicio TIMESTAMP,
    valor DECIMAL(10,2),
    diagnostico TEXT,
    observacao TEXT,
    id_medico INT,
    id_paciente INT,
    status VARCHAR(20) NOT NULL DEFAULT 'AGENDADA',
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico),
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente)
);
