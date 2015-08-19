package br.org.ovelha.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.MensagemEletronica;
import br.org.ovelha.persistence.EmailDAO;
import br.org.ovelha.util.SendMail;

@BusinessController
public class EmailBC extends DelegateCrud<MensagemEletronica, Long, EmailDAO> {

	private static final long serialVersionUID = 1L;
	
	public void enviarEmail(MensagemEletronica mail){
		try{			
			SendMail sendMail = new SendMail(mail);
			sendMail.send();
		}catch(Exception e){
			System.out.println("Erro ao enviar email: "+e.getMessage());

		}
	}
	
	public MensagemEletronica newMensagemEletronica(){
		MensagemEletronica mensagemEletronica = this.load(1L); 
		return mensagemEletronica;
	}


}
