package br.org.ovelha.persistence;

import java.util.HashMap;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.domain.Discipulo;
import br.org.ovelha.domain.Homem;
import br.org.ovelha.domain.Mulher;

@PersistenceController
public class DiscipuloDAO extends AbstractDAO<Discipulo, Long> {
	
	private static final long serialVersionUID = 1L;
	
	public Homem obterHomen(Long idCasal) {

		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select homem from Homem homem");		
		jpql.append(" where homem.idCasal = :idCasal");

		parametros.put("idCasal", idCasal);

		return (Homem) executeSingleResultQuery(jpql.toString(), parametros);
	}
	
	public Mulher obterMulher(Long idCasal) {

		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select mulher from Mulher mulher");		
		jpql.append(" where mulher.idCasal = :idCasal");

		parametros.put("idCasal", idCasal);

		return (Mulher) executeSingleResultQuery(jpql.toString(), parametros);
	}
	
}
