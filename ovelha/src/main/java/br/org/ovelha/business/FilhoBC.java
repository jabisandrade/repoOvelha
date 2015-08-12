package br.org.ovelha.business;

import java.util.Collection;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Filho;
import br.org.ovelha.persistence.FilhoDAO;
import br.org.ovelha.util.Data;

@BusinessController
public class FilhoBC extends DelegateCrud<Filho, Long, FilhoDAO> {

	private static final long serialVersionUID = 1L;

	public void inserirNovosFilhos(Collection<Filho> filhos, Long idCasal) {
		for(Filho filho:filhos){			
			if (filho.getId()==null){
				filho.setIdCasal(idCasal);
				filho.setDataRegistro(Data.dataAtual());
				this.insert(filho);
			}
		}
		
	}

	public void update(Collection<Filho> filhos) {
		for(Filho filho:filhos){
			this.update(filho);
		}		
	}



}
