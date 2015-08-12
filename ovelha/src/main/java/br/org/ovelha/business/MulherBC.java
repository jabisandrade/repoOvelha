package br.org.ovelha.business;

import java.util.Date;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Casal;
import br.org.ovelha.domain.Mulher;
import br.org.ovelha.persistence.MulherDAO;
import br.org.ovelha.util.CDIFactory;

@BusinessController
public class MulherBC extends DelegateCrud<Mulher, Long, MulherDAO> {
	
	private static final long serialVersionUID = 1L;

	public void update(Casal bean) {
		Mulher mulher = CDIFactory.getMulherDAO().getMulher(bean.getId());
		Mulher mulherBean = bean.getEsposa();
		mulherBean.setIdMulher(mulher.getIdMulher());
		mulherBean.setIdCasal(mulher.getIdCasal());
		mulherBean.setTelefoneResidencial(bean.getMarido().getTelefoneResidencial());
		mulherBean.setEndereco(bean.getMarido().getEndereco());
		mulherBean.setDataAtualizacaoRegistro(new Date());
		update(mulherBean);
	}

	public void delete(Casal bean) {
		Mulher mulher = CDIFactory.getMulherDAO().getMulher(bean.getId());
		delete(mulher.getIdMulher());
		
	}


	

	
}
