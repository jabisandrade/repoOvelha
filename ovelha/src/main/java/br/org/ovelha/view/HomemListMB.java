package br.org.ovelha.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.HomemBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Homem;

@ViewController
@NextView(PAGES.HOMEM_EDIT)
@PreviousView(PAGES.HOMEM_LIST)
public class HomemListMB extends AbstractListPageBean<Homem, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private HomemBC bc;

	@Override
	protected List<Homem> handleResultList() {
		return this.bc.findAll();
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				bc.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}
