package br.org.ovelha.util;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.domain.Perfil;
import br.org.ovelha.domain.Usuario;
 
@Transactional
public class Loader {
 
    @Startup
    public void load() {
        Usuario usuario;
 
        usuario = new Usuario();
        usuario.setLogin("admin@ovelha.com.br");
        usuario.setSenha("admin@");
        usuario.setPerfil(Perfil.ADM);
        
        Usuario usuarioExiste = CDIFactory.getUsuarioDAO().obterSenhaUsuario(usuario.getLogin());
        if(usuarioExiste == null){
        	CDIFactory.getUsuarioDAO().insert(usuario);	
        }
        
    }
}
