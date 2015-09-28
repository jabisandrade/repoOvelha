package br.org.ovelha.util;

import br.gov.frameworkdemoiselle.util.Beans;
import br.org.ovelha.acesso.Credenciais;
import br.org.ovelha.business.CasalBC;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.persistence.AgendaDAO;
import br.org.ovelha.persistence.CasalDAO;
import br.org.ovelha.persistence.DiscipuloDAO;
import br.org.ovelha.persistence.FilhoDAO;
import br.org.ovelha.persistence.HomemDAO;
import br.org.ovelha.persistence.MulherDAO;
import br.org.ovelha.persistence.UsuarioDAO;

public class CDIFactory {

	private CDIFactory() {
	}

	public static CasalDAO getCasalDAO() {
		return Beans.getReference(CasalDAO.class);
	}
	
	public static DiscipuloDAO getDiscipuloDAO() {
		return Beans.getReference(DiscipuloDAO.class);
	}
	
	public static HomemDAO getHomemDAO() {
		return Beans.getReference(HomemDAO.class);
	}
	
	public static MulherDAO getMulherDAO() {
		return Beans.getReference(MulherDAO.class);
	}
	
	public static FilhoDAO getFilhoDAO() {
		return Beans.getReference(FilhoDAO.class);
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return Beans.getReference(UsuarioDAO.class);
	}
	
	public static AgendaDAO getAgendaDAO() {
		return Beans.getReference(AgendaDAO.class);
	}
	
	public static Usuario getUsuarioLogado() {
		Credenciais credenciais = Beans.getReference(Credenciais.class);
		if(credenciais.getIdUsuario()!=null && credenciais.getIdUsuario()>0){
			return getUsuarioDAO().load(credenciais.getIdUsuario());	
		}else{
			return null;
		}
		
	}
	
	public static CasalBC getCasalBC() {
		return Beans.getReference(CasalBC.class);
	}

}
