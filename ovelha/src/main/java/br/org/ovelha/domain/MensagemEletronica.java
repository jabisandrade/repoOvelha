package br.org.ovelha.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SequenceGenerator(name = "seqEmail", sequenceName = "id_seq", allocationSize = 1)
@Entity
public class MensagemEletronica implements EntidadeIf {
	
	private static final long serialVersionUID = 1L;

	@Column
	private String destinatario;	
	
	@Transient
	private String assunto;
	
	@Transient
	private String conteudo;
	
	@Transient
	private boolean debug = false;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEmail")
	@Column(name = "id")
	private Long id;
	
	@Column
	private String servidor;
	
	@Column
	private String porta;
	
	@Column
	private String usuario;
	
	@Column
	private String senha;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacaoRegistro;
	
	public MensagemEletronica() {
		super();
				
	}
	
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataAtualizacaoRegistro() {
		this.dataAtualizacaoRegistro = new Date();
		return dataAtualizacaoRegistro;
	}

	public void setDataAtualizacaoRegistro(Date dataAtualizacaoRegistro) {
		this.dataAtualizacaoRegistro = dataAtualizacaoRegistro;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
