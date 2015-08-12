package br.org.ovelha.acesso;

import javax.enterprise.inject.Alternative;

import br.gov.frameworkdemoiselle.security.Authorizer;

@Alternative
public class Autorizacao implements Authorizer {

	private static final long serialVersionUID = 1L;


	@Override
	public boolean hasRole(String role) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(String resource, String operation)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	@Override
	public boolean hasRole(String role) {
		String usr = context.getUser().getId();
		boolean authorized = false;

		if (usr.equals("admin") && role.equals("administrators")) {
			authorized = true;
		}

		return authorized;
	}

	@Override
	public boolean hasPermission(Object res, String op) {
		String usr = context.getUser().getId();
		boolean authorized = false;

		if (usr.equals("zyc") && res.equals("hello") && op.equals("say")) {
			authorized = true;
		} else if (context.hasRole("administrators")) {
			authorized = true;
		}

		return authorized;
	}*/
}
