package br.org.ovelha.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Casal;
import br.org.ovelha.domain.Filho;
import br.org.ovelha.domain.Homem;
import br.org.ovelha.domain.Mulher;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.persistence.CasalDAO;
import br.org.ovelha.util.CDIFactory;
import br.org.ovelha.util.Data;
import br.org.ovelha.util.StringU;

@BusinessController
public class CasalBC extends DelegateCrud<Casal, Long, CasalDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DiscipuloBC discipuloBC;
	
	@Inject
	private UsuarioBC usuarioBC;
	
	@Inject
	private EmailBC emailBC;
	
	public Casal obterCasal(Long id) {				
		Homem marido = CDIFactory.getDiscipuloDAO().obterHomen(id);
		Mulher esposa = CDIFactory.getDiscipuloDAO().obterMulher(id);
		Casal casal = CDIFactory.getCasalDAO().load(id);
		casal.setMarido(marido);
		casal.setEsposa(esposa);
		
		return casal;
	}

	public void inserirCasal(Casal casal) {
		Usuario usuario = usuarioBC.obterUsuarioLogado();
		
		if(usuario.getPerfil().isPUB()){
			casal.setUsuario(usuario);
		}
		casal.setDataRegistro(Data.dataAtual());
		CDIFactory.getCasalDAO().insert(casal);
		CDIFactory.getCasalDAO().flushEntityManager();		
		discipuloBC.inserirDiscipulos(casal);
		enviarEmailCasalCadastrado(casal);
	}
	
	private void enviarEmailCasalCadastrado(Casal casal) {
		String destinatario = casal.getUsuario().getLogin();
		String nomeCasal = this.getNomePais(casal.getId());
		String assunto = "Cadastro do casal no sistema em ("+Data.dataExtenso()+")";

		StringBuilder conteudo = new StringBuilder();
		conteudo.append("Prezado(a),\n");
		conteudo.append("\n");
		conteudo.append("Identificamos o cadastro do casal conforme informado abaixo em nosso sistema:\n");
		conteudo.append("\n");
		conteudo.append("	--------------------------------------------\n");
		conteudo.append("	Login: "+destinatario+"\n");
		conteudo.append("	Casal: "+nomeCasal+"\n");
		conteudo.append("	--------------------------------------------\n");
		conteudo.append("\n");
		conteudo.append("Obrigado por realizar o cadastro.\n");
		conteudo.append("\n");
		conteudo.append("Que o Senhor te abencoe.\n");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("Atenciosamente,\n");
		conteudo.append("Sistema Ovelha \n");
		conteudo.append("http://sistema-ovelha.rhcloud.com");
		
		emailBC.enviarEmail(destinatario, assunto, conteudo.toString());
		
	}

	public List<Casal> obterTodosCasais (){
		
		List<Casal> retornoCasais = new ArrayList<Casal>();
		List<Casal> casais = new ArrayList<Casal>();
		Usuario usuario = usuarioBC.obterUsuarioLogado();
		
		if(usuario.getPerfil().isPUB()){
			Long idCasalUsuarioLogado = this.obterCasalPorUsuario();
			retornoCasais.add(this.obterCasal(idCasalUsuarioLogado));
			return retornoCasais;
		}else{
			casais = this.findAll();
			for (Casal casal:casais){
				retornoCasais.add(this.obterCasal(casal.getId()));
			}
			return retornoCasais;			
		}
		
	}

	public void atualizarCasal(Casal bean) {
		Collection<Filho> filhos = bean.getFilhos(); 
		
		bean.setDataAtualizacaoRegistro(Data.dataAtual());
		bean.setFilhos(null);
		CDIFactory.getCasalDAO().update(bean);
		
		bean.setFilhos(filhos);
		discipuloBC.atualizarDiscipulos(bean); 
		
	} 
	
	public void apagarCasal(Casal bean) {
		CDIFactory.getCasalDAO().delete(bean.getId());;
		discipuloBC.apagarDiscipulos(bean);
	} 
	
	public String getNomePais(Long idCasal){
		Casal casal = this.obterCasal(idCasal);
		return StringU.getNomeCasal(casal);
	}
	
	private Collection<Filho> getFilhosUpdate(Casal bean) {
		Collection<Filho> filhosUpdate = new ArrayList<Filho>();
		for(Filho filho:bean.getFilhos()){
			if((filho.getId()!=null) && (filho.getId()>0)){
				filho.setDataAtualizacaoRegistro(Data.dataAtual());
				
			}else{
				filho.setDataRegistro(Data.dataAtual());
				filho.setIdCasal(bean.getIdCasal());
			}
			filhosUpdate.add(filho);
		}
		return filhosUpdate;
	}
	
	public Long obterCasalPorUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String id = fc.getExternalContext().getSessionMap().get("usuarioid").toString();		
		return CDIFactory.getCasalDAO().obterCasalPorUsuario(Long.parseLong(id));		
	}

	
}
