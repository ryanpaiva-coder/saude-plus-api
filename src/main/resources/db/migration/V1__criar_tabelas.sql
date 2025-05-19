CREATE TABLE Endereco (
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    bairro VARCHAR(50),
    cep VARCHAR(10),
    cidade VARCHAR(50),
    estado VARCHAR(2)
);

CREATE TABLE Clinica (
    cnpj_clinica VARCHAR(20) PRIMARY KEY,
    nome_comercial VARCHAR(100),
    telefone VARCHAR(15),
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Role (
    id_role INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE Usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    sexo CHAR(1) CHECK (sexo IN ('M', 'F')) NOT NULL,
    telefone VARCHAR(15),
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
    id_medico INT PRIMARY KEY AUTO_INCREMENT,
    cnpj_clinica VARCHAR(20),
    id_usuario INT,
    id_especialidade INT,
    FOREIGN KEY (cnpj_clinica) REFERENCES Clinica(cnpj_clinica),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
    FOREIGN KEY (id_especialidade) REFERENCES Especialidade(id_especialidade)
);

CREATE TABLE CRM_Medico (
    id_crm INT PRIMARY KEY AUTO_INCREMENT,
    id_medico INT,
    crm VARCHAR(20),
    uf VARCHAR(2),
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

CREATE TABLE Paciente (
    id_usuario INT PRIMARY KEY,
    descricao TEXT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Consulta (
    id_consulta INT PRIMARY KEY AUTO_INCREMENT,
    inicio TIMESTAMP,
    valor DECIMAL(10,2),
    diagnostico TEXT,
    observacao TEXT,
    id_medico INT,
    id_paciente INT,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico),
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_usuario)
);

CREATE TABLE Receita (
    id_receita INT PRIMARY KEY,
    data_emissao DATE,
    validacao BOOLEAN,
    id_consulta INT,
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta)
);

CREATE TABLE Medicamento (
    id_medicamento INT PRIMARY KEY,
    nome VARCHAR(100),
    tipo VARCHAR(50),
    descricao TEXT,
    dose INT
);

CREATE TABLE Receita_Medicamento (
    id_receita INT,
    id_medicamento INT,
    posologia TEXT,
    observacao TEXT,
    PRIMARY KEY (id_receita, id_medicamento),
    FOREIGN KEY (id_receita) REFERENCES Receita(id_receita),
    FOREIGN KEY (id_medicamento) REFERENCES Medicamento(id_medicamento)
);
