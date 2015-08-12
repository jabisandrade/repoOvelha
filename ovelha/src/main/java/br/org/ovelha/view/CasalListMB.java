package br.org.ovelha.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.CasalBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Casal;

@ViewController
@NextView(PAGES.CASAL_EDIT)
@PreviousView(PAGES.CASAL_LIST)
public class CasalListMB extends AbstractListPageBean<Casal, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CasalBC bc;

	@Override
	protected List<Casal> handleResultList() {
		return this.bc.obterTodosCasais();			
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				this.bc.apagarCasal(this.bc.load(id));
				iter.remove();
			}
		}
		return getPreviousView();
	}	

}
