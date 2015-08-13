package br.org.ovelha.acesso;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class Credenciais implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;
	
	private Long idUsuario;
	
	private Integer perfil;
	
	private boolean loggedIn = false;

	public void clear() {
		username = null;
		password = null;
		idUsuario = 0L;
		loggedIn =false;
		perfil = 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
}
