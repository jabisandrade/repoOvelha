package br.org.ovelha.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.MensagemEletronica;
import br.org.ovelha.persistence.EmailDAO;
import br.org.ovelha.util.SendMail;

@BusinessController
public class EmailBC extends DelegateCrud<MensagemEletronica, Long, EmailDAO> {

	private static final long serialVersionUID = 1L;
	
	public void enviarEmail(String destinatarios, String assunto, String conteudo){
		try{			
			SendMail sendMail = new SendMail(destinatarios,assunto,conteudo, this.newMensagemEletronica());
			sendMail.send();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public MensagemEletronica newMensagemEletronica(){
		MensagemEletronica mensagemEletronica = this.load(1L); 
		return mensagemEletronica;
	}


}
