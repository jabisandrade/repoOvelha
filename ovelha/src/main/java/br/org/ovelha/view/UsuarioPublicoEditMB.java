package br.org.ovelha.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.Message;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.UsuarioBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Perfil;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.message.InfoMessages;

@ViewController
@PreviousView(PAGES.LOGIN)
public class UsuarioPublicoEditMB extends AbstractEditPageBean<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC bc;
	
	@Inject
	private MessageContext messageContext;
	


	@Override
	@Transactional
	public String insert() {
		Usuario usuario = getBean();
		usuario.setPerfil(Perfil.PUB);
		this.bc.insert(getBean());
		messageContext.add(InfoMessages.INSERT_OK.getText());
		return getPreviousView();		
	}


	
	
	@Transactional
	public String alterarSenha() {		
		String resultado = this.bc.alterarSenha(getBean());
		Message msg = new DefaultMessage(resultado);
		messageContext.add(msg.getText());
		return null;		

	}

	@Override
	protected Usuario handleLoad(Long id) {
		return this.bc.load(id);
	}




	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}





}
