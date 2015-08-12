package br.org.ovelha.persistence;

import java.util.Collection;
import java.util.HashMap;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.domain.Casal;
import br.org.ovelha.util.Data;

@PersistenceController
public class CasalDAO extends AbstractDAO<Casal, Long> {
	
	private static final long serialVersionUID = 1L;

	
    public Collection<Casal> obterAniversariosCasamentoMes(){
    	
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select c from Casal c");		
		jpql.append(" where month(c.dataAniversarioCasamento) in (:mes,:proximoMes)");

		parametros.put("mes", Data.mesAtual());
		parametros.put("proximoMes", Data.mesAtual()+1);

		return executeQuery(jpql.toString(), parametros);  
    }
	
}
