package br.com.escola.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class MailConfig {

  @Autowired
  private JavaMailSender mailSender;

  public MailConfig(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void enviarEmail(String destinatario, String assunto, String mensagemHtml) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

      helper.setTo(destinatario);
      helper.setSubject(assunto);
      helper.setText(mensagemHtml, true);

      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      throw new RuntimeException("Erro ao enviar e-mail", e);
    }
  }
}
