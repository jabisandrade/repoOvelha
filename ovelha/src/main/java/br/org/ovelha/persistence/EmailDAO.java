package br.org.ovelha.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.domain.MensagemEletronica;

@PersistenceController
public class EmailDAO extends AbstractDAO<MensagemEletronica, Long> {
	
	private static final long serialVersionUID = 1L;

	
}
