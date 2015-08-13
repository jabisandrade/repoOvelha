package br.org.ovelha.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.MulherBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Mulher;

@ViewController
@PreviousView(PAGES.MULHER_LIST)
@RequiredPermission(resource="mulher" , operation="editar")
public class MulherEditMB extends AbstractEditPageBean<Mulher, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MulherBC bc;
	
	@Override
	@Transactional
	public String delete() {
		this.bc.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {			
		this.bc.insert(getBean());		
		return getPreviousView();		
	}

	@Override
	@Transactional
	public String update() {
		this.bc.update(getBean());
		return getPreviousView();
	}

	@Override
	protected Mulher handleLoad(Long id) {
		return this.bc.load(id);
	}




}
