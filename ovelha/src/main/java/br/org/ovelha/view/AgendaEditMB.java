package br.org.ovelha.view;

import java.util.Date;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.AgendaBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Agenda;
import br.org.ovelha.message.InfoMessages;

@ViewController
@PreviousView(PAGES.AGENDA_LIST)
@RequiredPermission(resource="agenda" , operation="editar")
public class AgendaEditMB extends AbstractEditPageBean<Agenda, Long> { 
	
	private static final long serialVersionUID = 1L;

	@Inject
	private AgendaBC bc;
	
	@Inject
	private MessageContext messageContext;
	
	@Override
	@Transactional
	public String delete() {
		this.bc.delete(getId());
		messageContext.add(InfoMessages.DELETE_OK.getText());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		Agenda agenda = getBean();
		agenda.setDataRegistro(new Date());
		this.bc.insert(agenda);
		messageContext.add(InfoMessages.INSERT_OK.getText());
		return getPreviousView();		
	}

	@Override
	@Transactional
	public String update() {
		Agenda agenda = getBean();
		agenda.setDataAtualizacaoRegistro(new Date()); 
		this.bc.update(agenda);
		messageContext.add(InfoMessages.UPDATE_OK.getText());
		return getPreviousView();
	}

	@Override
	protected Agenda handleLoad(Long id) {
		return this.bc.load(id);
	}

}

