package br.org.ovelha.persistence;

import java.util.Collection;
import java.util.HashMap;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.domain.Casal;
import br.org.ovelha.domain.Perfil;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.util.Data;

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

	public Usuario obterSenhaUsuario(String login) {

		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select usuario from Usuario usuario");	
		jpql.append(" where usuario.login = :login");

		parametros.put("login", login);		
		return executeSingleResultQuery(jpql.toString(), parametros);

	}
	
    public Collection<Usuario> obterUsuariosCriadosPendentes(){
    	
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select u from Usuario u where u.idUsuario");		
		jpql.append(" not in (");
		jpql.append("   select u.idUsuario from Casal c inner join c.usuario u)");
		jpql.append(" and u.perfil= :perfil");
			
		parametros.put("perfil", Perfil.PUB.getId());
		

		return executeQuery(jpql.toString(), parametros);  
    }
    
    public Collection<Usuario> obterUsuariosADM(){
    	
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select u from Usuario u where u.perfil= :perfil");		
			
		parametros.put("perfil", Perfil.ADM.getId());
		
		return executeQuery(jpql.toString(), parametros);  
    }
		
}
