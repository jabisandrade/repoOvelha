package br.org.ovelha.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.AgendaBC;
import br.org.ovelha.business.DiscipuloBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Agenda;
import br.org.ovelha.domain.dto.DatasComemorativas;
import br.org.ovelha.util.Data;
import br.org.ovelha.util.StringU;

@ViewController
@NextView(PAGES.AGENDA_EDIT)
@PreviousView(PAGES.AGENDA_LIST)
public class AgendaListMB extends AbstractListPageBean<Agenda, Long> {

	private static final long serialVersionUID = 1L;

	private List<Agenda> lembretesAgenda;

	private List<DatasComemorativas> lembretesDatas; 

	private String labelLembretes;

	@Inject
	private AgendaBC bc;

	@Inject
	private DiscipuloBC discBC;

	@PostConstruct  
	protected void init() {  
		lembretesDatas = new ArrayList<DatasComemorativas>();
		lembretesAgenda = new ArrayList<Agenda>();
	}  

	@Override
	protected List<Agenda> handleResultList() {
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

	public List<Agenda> getLembretesAgenda() {
		lembretesAgenda = bc.obterAgendaMes();
		return lembretesAgenda;
	}

	public void setLembretesAgenda(List<Agenda> lembretesAgenda) {
		this.lembretesAgenda = lembretesAgenda;
	}

	/**
	 * @return the lembretesDatas
	 */
	public List<DatasComemorativas> getLembretesDatas() {
		lembretesDatas = discBC.obterDatasComemorativasMes();
		return lembretesDatas;
	}

	/**
	 * @param lembretesDatas the lembretesDatas to set
	 */
	public void setLembretesDatas(List<DatasComemorativas> lembretesDatas) {
		this.lembretesDatas = lembretesDatas;
	}

	/**
	 * @return the labelLembretes
	 */
	public String getLabelLembretes() {
		this.labelLembretes = "Lembretes ("+StringU.getMes(Data.mesAtual())+" e "+StringU.getMes(Data.mesAtual()+1)+")" ;
		return this.labelLembretes ;
	}

	/**
	 * @param labelLembretes the labelLembretes to set
	 */
	public void setLabelLembretes(String labelLembretes) {
		this.labelLembretes = labelLembretes;
	}

}
