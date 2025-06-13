package br.com.projeto.saude_plus.domain.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.projeto.saude_plus.domain.model.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private static final String ASSUNTO_BOAS_VINDAS = "Bem-vindo ao Saúde Plus!";
    private static final String TEMPLATE_BOAS_VINDAS = "src/main/resources/templates/email/cadastro.html";
    private static final String IMAGEM_ASSINATURA = "src/main/resources/static/imagens/saudeplus_cursive.png";
    private static final String CID_ASSINATURA = "assinaturaSaudePlus";
    private static final String ASSUNTO_DESATIVAMENTO = "Conta desativada - Saúde Plus";
    private static final String TEMPLATE_DESATIVAMENTO = "src/main/resources/templates/email/desativamento.html";
    private static final String ASSUNTO_CONSULTA_AGENDADA_PACIENTE = "Consulta agendada com sucesso!";
    private static final String TEMPLATE_CONSULTA_AGENDADA_PACIENTE = "src/main/resources/templates/email/consulta_agendada_paciente.html";
    private static final String ASSUNTO_CONSULTA_AGENDADA_MEDICO = "Nova consulta agendada";
    private static final String TEMPLATE_CONSULTA_AGENDADA_MEDICO = "src/main/resources/templates/email/consulta_agendada_medico.html";
    private static final String ASSUNTO_CONSULTA_CANCELADA_PACIENTE = "Consulta cancelada";
    private static final String TEMPLATE_CONSULTA_CANCELADA_PACIENTE = "src/main/resources/templates/email/consulta_cancelada_paciente.html";
    private static final String ASSUNTO_CONSULTA_CANCELADA_MEDICO = "Consulta cancelada pelo paciente";
    private static final String TEMPLATE_CONSULTA_CANCELADA_MEDICO = "src/main/resources/templates/email/consulta_cancelada_medico.html";

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailHtmlComImagem(String destinatario, String assunto, String conteudoHtml, File imagem) throws MessagingException {
        MimeMessage mensagem = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");
        helper.setTo(destinatario);
        helper.setSubject(assunto);
        helper.setText(conteudoHtml, true);
        helper.addInline(CID_ASSINATURA, new FileSystemResource(imagem));
        mailSender.send(mensagem);
    }

    public void enviarEmailBoasVindas(Usuario usuario) {
        try {
            String conteudoHtml = carregarTemplate(TEMPLATE_BOAS_VINDAS, usuario.getNome());
            File imagem = new File(IMAGEM_ASSINATURA);
            enviarEmailHtmlComImagem(usuario.getEmail(), ASSUNTO_BOAS_VINDAS, conteudoHtml, imagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail de boas-vindas: " + e.getMessage(), e);
        }
    }

    public void enviarEmailDesativamento(Usuario usuario) {
        try {
            String conteudoHtml = carregarTemplate(TEMPLATE_DESATIVAMENTO, usuario.getNome());
            File imagem = new File(IMAGEM_ASSINATURA);
            enviarEmailHtmlComImagem(usuario.getEmail(), ASSUNTO_DESATIVAMENTO, conteudoHtml, imagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail de desativação: " + e.getMessage(), e);
        }
    }

    public void enviarEmailConsultaAgendadaPaciente(
            String emailPaciente,
            String nomePaciente,
            String data,
            String hora,
            String nomeMedico,
            String especialidade
    ) {
        try {
            String conteudoHtml = new String(Files.readAllBytes(Paths.get(TEMPLATE_CONSULTA_AGENDADA_PACIENTE)), StandardCharsets.UTF_8)
                    .replace("@nomepaciente", nomePaciente)
                    .replace("@data", data)
                    .replace("@hora", hora)
                    .replace("@nomemedico", nomeMedico)
                    .replace("@especialidade", especialidade);
            File imagem = new File(IMAGEM_ASSINATURA);
            enviarEmailHtmlComImagem(emailPaciente, ASSUNTO_CONSULTA_AGENDADA_PACIENTE, conteudoHtml, imagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail de consulta agendada para paciente: " + e.getMessage(), e);
        }
    }

    public void enviarEmailConsultaAgendadaMedico(
            String emailMedico,
            String nomeMedico,
            String data,
            String hora,
            String nomePaciente,
            String telefonePaciente
    ) {
        try {
            String conteudoHtml = new String(Files.readAllBytes(Paths.get(TEMPLATE_CONSULTA_AGENDADA_MEDICO)), StandardCharsets.UTF_8)
                    .replace("@nomemedico", nomeMedico)
                    .replace("@data", data)
                    .replace("@hora", hora)
                    .replace("@nomepaciente", nomePaciente)
                    .replace("@telefonepaciente", telefonePaciente);
            File imagem = new File(IMAGEM_ASSINATURA);
            enviarEmailHtmlComImagem(emailMedico, ASSUNTO_CONSULTA_AGENDADA_MEDICO, conteudoHtml, imagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail de consulta agendada para médico: " + e.getMessage(), e);
        }
    }

    public void enviarEmailConsultaCanceladaPaciente(
            String emailPaciente,
            String nomePaciente,
            String data,
            String hora,
            String nomeMedico,
            String especialidade
    ) {
        try {
            String conteudoHtml = new String(Files.readAllBytes(Paths.get(TEMPLATE_CONSULTA_CANCELADA_PACIENTE)), StandardCharsets.UTF_8)
                    .replace("@nomepaciente", nomePaciente)
                    .replace("@data", data)
                    .replace("@hora", hora)
                    .replace("@nomemedico", nomeMedico)
                    .replace("@especialidade", especialidade);
            File imagem = new File(IMAGEM_ASSINATURA);
            enviarEmailHtmlComImagem(emailPaciente, ASSUNTO_CONSULTA_CANCELADA_PACIENTE, conteudoHtml, imagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail de consulta cancelada para paciente: " + e.getMessage(), e);
        }
    }

    public void enviarEmailConsultaCanceladaMedico(
            String emailMedico,
            String nomeMedico,
            String data,
            String hora,
            String nomePaciente,
            String telefonePaciente
    ) {
        try {
            String conteudoHtml = new String(Files.readAllBytes(Paths.get(TEMPLATE_CONSULTA_CANCELADA_MEDICO)), StandardCharsets.UTF_8)
                    .replace("@nomemedico", nomeMedico)
                    .replace("@data", data)
                    .replace("@hora", hora)
                    .replace("@nomepaciente", nomePaciente)
                    .replace("@telefonepaciente", telefonePaciente);
            File imagem = new File(IMAGEM_ASSINATURA);
            enviarEmailHtmlComImagem(emailMedico, ASSUNTO_CONSULTA_CANCELADA_MEDICO, conteudoHtml, imagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail de consulta cancelada para médico: " + e.getMessage(), e);
        }
    }

    private String carregarTemplate(String caminhoTemplate, String nomePaciente) throws Exception {
        String conteudo = new String(Files.readAllBytes(Paths.get(caminhoTemplate)), StandardCharsets.UTF_8);
        return conteudo.replace("@nomedapessoa", nomePaciente);
    }
}
