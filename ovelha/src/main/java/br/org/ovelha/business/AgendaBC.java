package br.org.ovelha.business;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Agenda;
import br.org.ovelha.persistence.AgendaDAO;
import br.org.ovelha.util.CDIFactory;

@BusinessController
public class AgendaBC extends DelegateCrud<Agenda, Long, AgendaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Agenda> obterAgendaMes(){
		return (List<Agenda>) CDIFactory.getAgendaDAO().obterAgendaMes();
	}

	

	
}
