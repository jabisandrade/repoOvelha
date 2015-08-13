package br.org.ovelha.business;

import javax.faces.context.FacesContext;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.persistence.UsuarioDAO;
import br.org.ovelha.util.CDIFactory;

@BusinessController
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {

	private static final long serialVersionUID = 1L;

	public Long isUsuarioValido(Usuario usuario) {
		return CDIFactory.getUsuarioDAO().obterIdUsuario(usuario);		
	}

	public String alterarSenha(Usuario bean) {

		Usuario usuarioLogado = CDIFactory.getUsuarioLogado();

		if(bean.getLogin()==null || bean.getLogin().isEmpty() 
				|| bean.getSenha()==null || bean.getSenha().isEmpty() 
				|| bean.getSenhaNova() ==null || bean.getSenhaNova().isEmpty() 
				|| bean.getSenhaNovaRepetida()==null || bean.getSenhaNovaRepetida().isEmpty()){
			return "Todos os campos devem ser preenchidos corretamente. Por favor, revise os campos preenchidos e repita a operação.";	
		}
		
		if(!bean.getLogin().equals(usuarioLogado.getLogin())){
			return "Por favor informe seu Login de usuário corretamente!";
		}
		
		if(!bean.getSenha().equals(usuarioLogado.getSenha())){
			return "Sua senha não foi confirmada para este usuario! Por favor, revise os campos preenchidos e repita a operação.";
		}
		
		if(!bean.getSenhaNova().equals(bean.getSenhaNovaRepetida())){
			return "Os campos contendo a nova senha não conferem. Por favor, revise os campos preenchidos e repita a operação. ";
		}
		
		try{
			usuarioLogado.setSenha(bean.getSenhaNova());		
			this.update(usuarioLogado);
			return "Senha alterada com sucesso!";
			
			
		}catch(Exception e){
			return "Ocorreu um erro ao atualizar a senha. Tente mais tarde!";			
		}			
	}
	
	public Usuario obterUsuarioLogado() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String id = fc.getExternalContext().getSessionMap().get("usuarioid").toString();		
		return this.load(Long.parseLong(id));		
	}




}
