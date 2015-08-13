package br.org.ovelha.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.org.ovelha.acesso.Credenciais;
import br.org.ovelha.util.Cripto;

@SequenceGenerator(name = "seqUsuario", sequenceName = "idUsuario_seq", allocationSize = 1)
@Entity
public class Usuario implements EntidadeIf {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
	@Column(name = "idUsuario")
	private Long idUsuario;

	@NotNull
	@Column
	private String login;
	
	@NotNull
	@Column
	private String senha;
	
	@NotNull
	@Column
	private Integer perfil;
		
	@Transient
	private String senhaNova;
	
	@Transient
	private String senhaNovaRepetida;
	
	@Transient
	private Integer perfilId;
	
	public Usuario(){
		
	}
	
	public Usuario(Credenciais credenciais){
		this.setLogin(credenciais.getUsername());
		this.setSenha(credenciais.getPassword());		
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Cripto.gerar(senha);
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaNovaRepetida() {
		return senhaNovaRepetida;
	}

	public void setSenhaNovaRepetida(String senhaNovaRepetida) {
		this.senhaNovaRepetida = senhaNovaRepetida;
	}

	public Perfil getPerfil() {		
		return Perfil.get(perfil);
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil.getId();
	}
	
	
	
	public Integer getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Integer perfilId) {
		this.perfilId = perfilId;
		this.perfil = perfilId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return idUsuario;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.idUsuario = id;
	}

	
}
