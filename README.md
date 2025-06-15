<h1 align="center" style="font-weight: bold;">Saúde Plus API 💻</h1>

<p align="center">
 <a href="#descricao">Descrição</a> • 
 <a href="#funcionalidades">Funcionalidades</a> • 
 <a href="#instalacao">Instalação</a> • 
  <a href="#colab">Colaboradores</a>
</p>

<p align="center">
    <b>API em Java para um sistema de agendamento de consultas médicas, com o objetivo de automatizar e facilitar a marcação de atendimentos para pacientes e médicos através de uma plataforma centralizada e intuitiva.</b>
</p>

<p align="center">
     <a href="https://github.com/ryanpaiva-coder/saude-plus-api">📱 Visite o Projeto</a>
</p>

<h2 id="descricao">📄 Descrição</h2>

O Saúde+ é um sistema que moderniza o agendamento de consultas, eliminando a dependência de filas, telefonemas e anotações manuais. A plataforma permite que pacientes marquem e cancelem atendimentos de forma rápida, com confirmação imediata por e-mail.

Para os médicos, o sistema centraliza todo o histórico de agendamentos em um painel único, simplificando a gestão da agenda e a comunicação com os pacientes.

Gerentes possuem uma visão completa das operações da clínica, podendo gerenciar usuários e acompanhar o desempenho.

Este projeto foi desenvolvido como parte do trabalho da A3, construído com Java e Spring Boot no backend para garantir robustez e segurança, o sistema utiliza MySQL para o armazenamento de dados. Otimiza a experiência de agendamento, reduz erros e conflitos de horários.

<h2 id="funcionalidades">💻 Funcionalidades</h2>

- Gestão de Pacientes: Permite o cadastro. 
- Agendamento de Consultas: Pacientes e administradores podem agendar consultas com médico. 
- Cancelamento de Consultas: Permite o cancelamento de atendimentos com notificação por e-mail para o paciente e o médico. 
- Autenticação e Perfis: Usuários (pacientes, médicos e gerentes) podem realizar login  e visualizar seus perfis. 
- Gestão da Clínica: Gerentes podem gerenciar médicos e visualizar relatórios de desempenho da clínica. 
- Segurança: Senhas e dados sensíveis são criptografados, e o sistema possui proteção contra ataques comuns. 

<h2 id=instalacao>🚀 Instalação</h2>

Para clonar o repositório e instalar as dependências:

<h3>Pré-requisitos</h3>

- Java 21+ 
- Maven
- Spring Boot
- MySQL
- Visual Studio Code (Opcional)
- DBeaver (Opcional)
- Postman (Opcional)
- Bash, ZSH ou um shell de terminal de sua escolha.

<h3>Passo a Passo</h3>

1. Clone o repositório:
   ```bash
   git clone https://github.com/ryanpaiva-coder/saude-plus-api
   ```
2. Acesse o projeto:
   ```bash
   cd saude-plus-api
   ```
3. Instale as dependências do maven:
   ```bash
   mvn clean install
   ```   
4. Rode o projeto:
   ```bash
   mvn spring-boot:run
   ```

<h2 id="colab">🤝 Colaboradores</h2>

Os alunos envolvidos em todo o projeto.

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/176524197?v=4" width="100px;" alt="Ryan Pedro Profile Picture"/><br>
        <sub>
          <b>Ryan Pedro</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/157769029?v=4" width="100px;" alt="João Victor Profile Picture"/><br>
        <sub>
          <b>João Victor</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/178051914?v=4" width="100px;" alt="Rafael Souza Profile Picture"/><br>
        <sub>
          <b>Rafael Souza</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/128873783?v=4" width="100px;" alt="Vitorio Profile Picture"/><br>
        <sub>
          <b>Vitorio</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/190994625?v=4" width="100px;" alt="Alexandre Fernandes Profile Picture"/><br>
        <sub>
          <b>Alexandre Fernandes</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

<h2>Licença</h2>
Este projeto está licenciado sob a Licença MIT.
