package br.org.ovelha.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.org.ovelha.domain.MensagemEletronica;

public class SendMail {
	
	private String mailSMTPServer;
	private String mailSMTPServerPort;
	private String mailSMTPUserServer;
	private String mailSMTPPasswordrServer;
	private String mailTo;
	private String mailSubject;
	private String mailMessage;
	private boolean debug;
		
	/**
	 * @param destinatarios
	 * @param assunto
	 * @param conteudo
	 * @param config
	 */
	public SendMail(String destinatarios, String assunto, String conteudo, MensagemEletronica config){
		
		this.mailTo = destinatarios;
		this.mailSubject = assunto;
		this.mailMessage = conteudo;  		

		//Config
		this.mailSMTPServer = config.getServidor();
		this.mailSMTPServerPort = config.getPorta();
		this.mailSMTPUserServer = config.getUsuario();
		this.mailSMTPPasswordrServer = config.getSenha();		
		this.debug  = false;
		
	}
	
	public void send() {
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.host", mailSMTPServer); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.user", mailSMTPUserServer); 
		props.put("mail.debug", debug);
		props.put("mail.smtp.port", mailSMTPServerPort); 
		props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); 
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		//Cria um autenticador que sera usado a seguir
		SimpleAuth auth = null;
		auth = new SimpleAuth (mailSMTPUserServer,mailSMTPPasswordrServer);		
		//Session session = Session.getDefaultInstance(props, auth);
		Session session = Session.getInstance(props, auth);
		session.setDebug(debug); 

		//Objeto que contém a mensagem
		Message msg = new MimeMessage(session);

		try {
			//Setando o destinatário
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
			//Setando a origem do email
			msg.setFrom(new InternetAddress(mailSMTPUserServer));
			//Setando o assunto
			msg.setSubject(mailSubject);
			//Setando o conteúdo/corpo do email
			msg.setContent(mailMessage,"text/plain");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Objeto encarregado de enviar os dados para o email
		Transport tr;
		try {
			tr = session.getTransport("smtp"); //define smtp para transporte
			/*
			 *  1 - define o servidor smtp
			 *  2 - seu nome de usuario do gmail
			 *  3 - sua senha do gmail
			 */
			tr.connect(mailSMTPServer, mailSMTPUserServer, mailSMTPPasswordrServer);
			msg.saveChanges(); // don't forget this
			//envio da mensagem
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
class SimpleAuth extends Authenticator {
	public String username = null;
	public String password = null;


	public SimpleAuth(String user, String pwd) {
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication (username,password);
	}
} 
