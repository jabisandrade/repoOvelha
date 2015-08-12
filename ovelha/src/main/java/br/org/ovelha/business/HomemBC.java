package br.org.ovelha.business;

import java.util.Date;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Casal;
import br.org.ovelha.domain.Homem;
import br.org.ovelha.persistence.HomemDAO;
import br.org.ovelha.util.CDIFactory;

@BusinessController
public class HomemBC extends DelegateCrud<Homem, Long, HomemDAO> {

	private static final long serialVersionUID = 1L;

	public void update(Casal bean) {
		Homem homem = CDIFactory.getHomemDAO().getHomem(bean.getId());
		Homem homemBean = bean.getMarido();
		homemBean.setIdHomem(homem.getIdHomem());
		homemBean.setIdCasal(homem.getIdCasal());
		homemBean.setDataAtualizacaoRegistro(new Date());
		update(homemBean);
	}

	public void delete(Casal bean) {
		Homem homem = CDIFactory.getHomemDAO().getHomem(bean.getId());
		delete(homem.getIdHomem());		
	}

}
