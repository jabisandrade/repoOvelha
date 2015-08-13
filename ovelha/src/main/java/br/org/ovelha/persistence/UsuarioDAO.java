package br.org.ovelha.persistence;

import java.util.HashMap;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.domain.Usuario;

@PersistenceController
public class UsuarioDAO extends AbstractDAO<Usuario, Long> {
	
	private static final long serialVersionUID = 1L;

	public Long obterIdUsuario(Usuario usuario) {

		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select usuario from Usuario usuario");	
		jpql.append(" where usuario.login = :login");
		jpql.append(" and usuario.senha = :senha");

		parametros.put("login", usuario.getLogin());
		parametros.put("senha", usuario.getSenha());
		
		usuario = executeSingleResultQuery(jpql.toString(), parametros);
		if (usuario == null){
			return 0L;
		}else{
			return usuario.getIdUsuario();	
		}		
	}
		
}
