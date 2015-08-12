package br.org.ovelha.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.HomemBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Homem;

@ViewController
@PreviousView(PAGES.HOMEM_LIST)
public class HomemEditMB extends AbstractEditPageBean<Homem, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private HomemBC homemBC;
	
	@Override
	@Transactional
	public String delete() {
		this.homemBC.delete(getId());		
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {			
		this.homemBC.insert(getBean());		
		return getPreviousView();		
	}

	@Override
	@Transactional
	public String update() {
		this.homemBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected Homem handleLoad(Long id) {
		return this.homemBC.load(id);
	}




}
