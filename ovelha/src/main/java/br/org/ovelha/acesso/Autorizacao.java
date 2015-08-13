package br.org.ovelha.acesso;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authorizer;
import br.org.ovelha.domain.Perfil;

public class Autorizacao implements Authorizer {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Credenciais credenciais;


	@Override
	public boolean hasRole(String role) throws Exception {
		String perfil = credenciais.getPerfil().toString();
		if (perfil.equals(role)){
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPermission(String resource, String operation)
			throws Exception {
		String perfil = credenciais.getPerfil().toString();
		if (perfil.equals(Perfil.ADM.getId().toString())){
			return true;
		}
		return false;
	}

}
