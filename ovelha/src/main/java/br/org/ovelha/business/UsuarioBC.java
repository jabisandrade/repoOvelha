package br.org.ovelha.business;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.MensagemEletronica;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.persistence.UsuarioDAO;
import br.org.ovelha.util.CDIFactory;
import br.org.ovelha.util.Data;

@BusinessController
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmailBC emailBC;

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

	public String recuperarSenha(Usuario bean) {

		try{
			Usuario usuario = CDIFactory.getUsuarioDAO().obterSenhaUsuario(bean.getLogin());
			MensagemEletronica email = emailBC.newMensagemEletronica();
			email.setDestinatario(bean.getLogin());
			email.setAssunto("Recuperação de senha de usuário em ("+Data.dataExtenso()+")");

			StringBuilder conteudo = new StringBuilder();
			conteudo.append("Prezado(a),\n");
			conteudo.append("\n");
			conteudo.append("Segue as informações referente ao seu usuario no sistema:\n");
			conteudo.append("\n");
			conteudo.append("	--------------------------------------------\n");
			conteudo.append("	Login: "+usuario.getLogin()+"\n");
			conteudo.append("	Senha: "+usuario.getSenha()+"\n");
			conteudo.append("	--------------------------------------------\n");
			conteudo.append("\n");
			conteudo.append("Sugerimos que a senha seja alterada por outra e memorizada.\n");
			conteudo.append("\n");
			conteudo.append("\n");
			conteudo.append("Atenciosamente,\n");
			conteudo.append("Sistema Ovelha");

			email.setConteudo(conteudo.toString());					

			emailBC.enviarEmail(email);
			return "Sua senha foi recuperada e será enviada dentro de instantes ao email informado.";

		}catch(Exception e){
			return "Ocorreu um erro ao recuperar senha de usuario. Favor contatar o administrador do sistema.";

		}
	}




}
