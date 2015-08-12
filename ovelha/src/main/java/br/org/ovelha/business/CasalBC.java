package br.org.ovelha.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Casal;
import br.org.ovelha.domain.Filho;
import br.org.ovelha.domain.Homem;
import br.org.ovelha.domain.Mulher;
import br.org.ovelha.persistence.CasalDAO;
import br.org.ovelha.util.CDIFactory;
import br.org.ovelha.util.Data;
import br.org.ovelha.util.StringU;

@BusinessController
public class CasalBC extends DelegateCrud<Casal, Long, CasalDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DiscipuloBC discipuloBC;
	
	public Casal obterCasal(Long id) {
		Homem marido = CDIFactory.getDiscipuloDAO().obterHomen(id);
		Mulher esposa = CDIFactory.getDiscipuloDAO().obterMulher(id);
		Casal casal = CDIFactory.getCasalDAO().load(id);
		casal.setMarido(marido);
		casal.setEsposa(esposa);
		
		return casal;
	}

	public void inserirCasal(Casal casal) {
		casal.setDataRegistro(Data.dataAtual());
		CDIFactory.getCasalDAO().insert(casal);
		CDIFactory.getCasalDAO().flushEntityManager();		
		discipuloBC.inserirDiscipulos(casal);						
	}
	
	public List<Casal> obterTodosCasais (){
		
		List<Casal> retornoCasais = new ArrayList<Casal>();
		List<Casal> casais = this.findAll();
		
		for (Casal casal:casais){
			retornoCasais.add(this.obterCasal(casal.getId()));
		}
		return retornoCasais;
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

	
}
