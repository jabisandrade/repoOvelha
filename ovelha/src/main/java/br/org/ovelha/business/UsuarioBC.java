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
import br.org.ovelha.util.StringU;

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

		if(!bean.getLogin().equals(usuarioLogado.getLogin()) && usuarioLogado.getPerfil().isPUB()){
			return "Por favor informe seu Login de usuário corretamente!";
		}

		if(!bean.getSenha().equals(usuarioLogado.getSenha()) && usuarioLogado.getPerfil().isPUB()){
			return "Sua senha não foi confirmada para este usuario! Por favor, revise os campos preenchidos e repita a operação.";
		}

		if(!bean.getSenhaNova().equals(bean.getSenhaNovaRepetida())){
			return "Os campos contendo a nova senha não conferem. Por favor, revise os campos preenchidos e repita a operação. ";
		}

		try{
			if(usuarioLogado.getPerfil().isPUB()){
				usuarioLogado.setSenha(bean.getSenhaNova());		
				this.update(usuarioLogado);
				return "Senha alterada com sucesso!";				
			}else{
				Usuario usuario = CDIFactory.getUsuarioDAO().obterSenhaUsuario(bean.getLogin());
				String retorno = "Usuário informado não pode ser localizado.";
				if(usuario!=null){
					usuario.setSenha(bean.getSenhaNova());
					this.update(usuario);
					retorno = "Senha alterada com sucesso para o usuario: "+bean.getLogin();									
				}
				return retorno;
			}


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
			String senhaGerada = StringU.getSenhaAleatoria(8);
			Usuario usuario = CDIFactory.getUsuarioDAO().obterSenhaUsuario(bean.getLogin());
			usuario.setSenha(senhaGerada);
			MensagemEletronica email = emailBC.newMensagemEletronica();
			email.setDestinatario(usuario.getLogin());
			email.setAssunto("Recuperação de senha de usuário em ("+Data.dataExtenso()+")");

			StringBuilder conteudo = new StringBuilder();
			conteudo.append("Prezado(a),\n");
			conteudo.append("\n");
			conteudo.append("Por medidas de segurança, uma nova senha foi gerada automaticamente pelo sistema.\n");
			conteudo.append("Segue as informações referente ao seu usuario no sistema:\n");
			conteudo.append("\n");
			conteudo.append("	--------------------------------------------\n");
			conteudo.append("	Login: "+usuario.getLogin()+"\n");
			conteudo.append("	Senha: "+senhaGerada+"\n");
			conteudo.append("	--------------------------------------------\n");
			conteudo.append("\n");
			conteudo.append("Sugerimos que a senha informada seja alterada por uma outra se sua preferência com até oito caracteres.\n");
			conteudo.append("\n");
			conteudo.append("Que o Senhor te abençoe.\n");
			conteudo.append("\n");
			conteudo.append("\n");
			conteudo.append("Atenciosamente,\n");
			conteudo.append("Sistema Ovelha \n");
			conteudo.append("http://sistema-ovelha.rhcloud.com");
			
			email.setConteudo(conteudo.toString());					
			this.update(usuario);
			emailBC.enviarEmail(email);
			return "Uma nova senha foi gerada automaticamente e será enviada dentro de instantes ao email informado.";

		}catch(Exception e){
			return "Ocorreu um erro ao recuperar senha de usuario. Favor contatar o administrador do sistema.";

		}
	}

	public String inserir(Usuario usuario) {
		try {
			Usuario usuarioPesquisado = CDIFactory.getUsuarioDAO().obterSenhaUsuario(usuario.getLogin());
			
			if (usuarioPesquisado == null){
				MensagemEletronica email = emailBC.newMensagemEletronica();
				email.setDestinatario(usuario.getLogin());
				email.setAssunto("Criação de usuário no sistema em ("+Data.dataExtenso()+")");

				StringBuilder conteudo = new StringBuilder();
				conteudo.append("Prezado(a),\n");
				conteudo.append("\n");
				conteudo.append("Identificamos a criação para o usuário informado abaixo em nosso sistema:\n");
				conteudo.append("\n");
				conteudo.append("	--------------------------------------------\n");
				conteudo.append("	Login: "+usuario.getLogin()+"\n");
				conteudo.append("	--------------------------------------------\n");
				conteudo.append("\n");
				conteudo.append("Seja bem vindo, obrigado por se cadastrar.\n");
				conteudo.append("\n");
				conteudo.append("Que o Senhor te abencoe.\n");
				conteudo.append("\n");
				conteudo.append("\n");
				conteudo.append("Atenciosamente,\n");
				conteudo.append("Sistema Ovelha \n");
				conteudo.append("http://sistema-ovelha.rhcloud.com");
				
				email.setConteudo(conteudo.toString());					
				
				this.insert(usuario);
				emailBC.enviarEmail(email);
				return "Usuário ["+usuario.getLogin()+"] criado com sucesso.";
			}else{
				return "Usuário ["+usuario.getLogin()+"] existente na base. Favor usar a funcionalidade [Recuperar Senha].";
			}						
		} catch (Exception e) {
			return "Usuário ["+usuario.getLogin()+"] não pôde ser criado. Certifique-se que é um email fornecido é valido ou entre em contato com o administrador do sistema.";
		}		
	}




}
