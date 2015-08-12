package br.org.ovelha.persistence;

import java.util.Collection;
import java.util.HashMap;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.domain.Agenda;
import br.org.ovelha.util.Data;

@PersistenceController
public class AgendaDAO extends AbstractDAO<Agenda, Long> {
	
	private static final long serialVersionUID = 1L;
	
    public Collection<Agenda> obterAgendaMes(){
    	
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select agenda from Agenda agenda");		
		jpql.append(" where month(agenda.inicio) in (:mes,:proximoMes)");
		jpql.append(" 	and year(agenda.inicio) = :ano");
		
		parametros.put("mes", Data.mesAtual());
		parametros.put("proximoMes", Data.mesAtual()+1);
		parametros.put("ano", Data.anoAtual());

		return executeQuery(jpql.toString(), parametros);                    
    }
    

	


	
	
}
