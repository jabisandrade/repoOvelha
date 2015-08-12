package br.org.ovelha.persistence;

import java.util.Collection;
import java.util.HashMap;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.domain.Mulher;
import br.org.ovelha.util.Data;

@PersistenceController
public class MulherDAO extends AbstractDAO<Mulher, Long> {
	
	private static final long serialVersionUID = 1L;

	public Mulher getMulher (Long idCasal){
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select m from Mulher m");		
		jpql.append(" where m.idCasal = :idCasal");

		parametros.put("idCasal", idCasal);

		return executeSingleResultQuery(jpql.toString(), parametros);
	}
	
    public Collection<Mulher> obterAniversariantesMes(){
    	
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select m from Mulher m");		
		jpql.append(" where month(m.dataAniversario) in (:mes,:proximoMes)");

		parametros.put("mes", Data.mesAtual());
		parametros.put("proximoMes", Data.mesAtual()+1);

		return executeQuery(jpql.toString(), parametros);  
    }
	
	
}
