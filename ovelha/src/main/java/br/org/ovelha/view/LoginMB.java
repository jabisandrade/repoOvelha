package br.org.ovelha.view;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.InvalidCredentialsException;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;
import br.org.ovelha.acesso.Credenciais;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Perfil;

@ViewController
@NextView(PAGES.INICIAL)
public class LoginMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;
    
	@Inject
    private SecurityContext context;
	
	@Inject
	private Credenciais credenciais;
	
	private boolean ADM;
	
	public void login(){
		try{			
			context.login();
			messageContext.add("Usuário autenticado com sucesso!");
		}catch(InvalidCredentialsException exception){
			messageContext.add(exception.getMessage());				
		}				
	}
	
	public void logout(){
		System.out.println("Logout solicitado...");
		context.logout();			
	}

	public Credenciais getCredenciais() {
		return credenciais;
	}

	public void setCredenciais(Credenciais credenciais) {
		this.credenciais = credenciais;
	}

	public boolean isADM() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String perfil = fc.getExternalContext().getSessionMap().get("perfil").toString();
		if (perfil.equals(Perfil.ADM.getId().toString())){
			this.ADM = true;
		}else{
			this.ADM = false;
		}
		return ADM;
	}





}
