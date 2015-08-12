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
	public void enviar(MensagemEletronica email)  {
		
		String remetente = "sistema.ovelha@ovelha.org.br";
		String destino = email.getDestinatario();
		String assunto = email.getAssunto();
		String conteudo  = email.getConteudo();
		
		// Chama o carteiro passando os parametros e pedindo para enviar
		this.carteiro
		.to(destino)
		.from(remetente)
		.body().text(conteudo)
		.subject(assunto)
		.send();		
		
	}
	
	/**
	 * Método que receber uma coleção de notificações e envia cada uma
	 * @param notificacoes
	 */
	public void enviarMultiplos(Collection<MensagemEletronica> notificacoes){
		for (MensagemEletronica email : notificacoes) {
			enviar(email);			
		}
	}
	
}
