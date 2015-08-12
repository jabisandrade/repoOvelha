package br.org.ovelha.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.EmailBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.MensagemEletronica;
import br.org.ovelha.message.InfoMessages;

@ViewController
@PreviousView(PAGES.INICIAL)
public class EmailEditMB extends AbstractEditPageBean<MensagemEletronica, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmailBC bc;
	
	@Inject
	private MessageContext messageContext;
	
	private boolean updateMode;	
	
	
	@PostConstruct
	public void init(){
		MensagemEletronica bean = bc.load(1L);
		if(bean!=null){
			this.updateMode = Boolean.TRUE;
			setBean(bean);	
			
		}
	}
	
	@Override
	@Transactional
	public String delete() {
		this.bc.delete(getId());
		messageContext.add(InfoMessages.DELETE_OK.getText());
		return null;
	}

	@Override
	@Transactional
	public String insert() {			
		this.bc.insert(getBean());	
		messageContext.add(InfoMessages.INSERT_OK.getText());
		return getPreviousView();		
	}

	@Override
	@Transactional
	public String update() {
		this.bc.update(getBean());
		messageContext.add(InfoMessages.UPDATE_OK.getText());
		return null;
	}

	@Override
	protected MensagemEletronica handleLoad(Long id) {
		return this.bc.load(id);
	}
	
	public String enviarEmail(){
		this.bc.enviarEmail(getBean());	
		messageContext.add("Solicitação para encaminhamento de email realizado. Favor confirmar na caixa de entrada do endereço do destinatário");
		return null;

		
	}

public boolean isUpdateMode(){
	return this.updateMode;
	
}

/**
 * @param updateMode the updateMode to set
 */
public void setUpdateMode(boolean updateMode) {
	this.updateMode = updateMode;
}


}
