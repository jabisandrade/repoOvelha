package br.org.ovelha.view;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.CasalBC;
import br.org.ovelha.business.FilhoBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Casal;
import br.org.ovelha.domain.Filho;
import br.org.ovelha.message.InfoMessages;
import br.org.ovelha.util.StringU;

@ViewController
@PreviousView(PAGES.CASAL_LIST)
public class CasalEditMB extends AbstractEditPageBean<Casal, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CasalBC casalBC;

	@Inject
	private FilhoBC filhoBC;

	@Inject
	private MessageContext messageContext;

	private int numeroFilhos;
	private boolean exibeFilhos = false;
	

	@PostConstruct
	public void init(){
		if (getBean()!=null && getBean().getFilhos().size()>0){
			numeroFilhos = getBean().getFilhos().size();
			exibeFilhos = true;
		}
	}


	@Override
	@Transactional
	public String delete() {
		this.casalBC.apagarCasal(getBean());
		messageContext.add(InfoMessages.DELETE_OK.getText());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {					
		this.casalBC.inserirCasal(getBean());
		messageContext.add(InfoMessages.INSERT_OK.getText());
		return getPreviousView();		
	}

	@Override
	@Transactional
	public String update() {
		this.casalBC.atualizarCasal(getBean());
		messageContext.add(InfoMessages.UPDATE_OK.getText());
		return getPreviousView();
	}

	@Override
	protected Casal handleLoad(Long id) {
		return this.casalBC.obterCasal(id);
	}

	public void mostrarFilhos(){
		List<Filho> filhos = new ArrayList<Filho>();
		if(numeroFilhos>0){

			if ((getBean().getFilhos().size()>0) && (numeroFilhos > getBean().getFilhos().size())){
				numeroFilhos = numeroFilhos - getBean().getFilhos().size();
				for (int i = 0; i < numeroFilhos; i++) {
					Filho filho = new Filho();
					filhos.add(filho);
				}
				getBean().getFilhos().addAll(filhos);
				numeroFilhos = getBean().getFilhos().size();

			}else if (getBean().getFilhos().size()>0 && numeroFilhos < getBean().getFilhos().size() ){
				numeroFilhos = getBean().getFilhos().size()-numeroFilhos;
				for (int j = 0; j < numeroFilhos; j++) {
					List<Filho> collFilhos = (List<Filho>) getBean().getFilhos();
					Filho remFilho = collFilhos.get(collFilhos.size()-1);					
					getBean().getFilhos().remove(remFilho);					
				}
				numeroFilhos = getBean().getFilhos().size();

			}else if(getBean().getFilhos().size()==0){
				for (int i = 0; i < numeroFilhos; i++) {
					Filho filho = new Filho();
					filhos.add(filho);
				}
				getBean().setFilhos(filhos);
			}

			exibeFilhos = Boolean.TRUE;					
		}else{
			exibeFilhos = Boolean.FALSE;
		}
	}


	/**
	 * @return the numeroFilhos
	 */
	public int getNumeroFilhos() {
		return numeroFilhos;
	}

	/**
	 * @param numeroFilhos the numeroFilhos to set
	 */
	public void setNumeroFilhos(int numeroFilhos) {
		this.numeroFilhos = numeroFilhos;
	}

	/**
	 * @return the exibeFilhos
	 */
	public boolean isExibeFilhos() {
		return exibeFilhos;
	}

	/**
	 * @param exibeFilhos the exibeFilhos to set
	 */
	public void setExibeFilhos(boolean exibeFilhos) {
		this.exibeFilhos = exibeFilhos;
	}

	@Transactional
	public String deleteSelectionFilho() {
		long id = Long.parseLong(StringU.getParamPage("idFilho"));
		this.filhoBC.delete(id);
		messageContext.add(InfoMessages.DELETE_OK.getText());
		return getPreviousView();

	}


}
