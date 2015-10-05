package br.org.ovelha.util;

import java.util.Collection;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.mail.Mail;
import br.org.ovelha.domain.MensagemEletronica;


public class GerenciadorEmail {
	
	@Inject
	private Mail carteiro;
	
	private GerenciadorEmail() {
		
	}
	
	/**
	 * Método que envia uma notificação de e-mail de acordo com os parâmetros
	 * nela configurados
	 * 
	 * @param email
	 * @throws AmbienteException
	 */
	public void enviar(String destino, String assunto, String conteudo)  {
		
		String remetente = "sistema.ovelha@ovelha.org.br";
		
		// Chama o carteiro passando os parametros e pedindo para enviar
		this.carteiro
		.to(destino)
		.from(remetente)
		.body().text(conteudo)
		.subject(assunto)
		.send();		
		
	}
	

	
}
