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
import javax.validation.constraints.NotNull;


@SequenceGenerator(name = "seqAgenda", sequenceName = "idAgenda_seq", allocationSize = 1)
@Entity
public class Agenda implements EntidadeIf  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAgenda")
	@Column(name = "idAgenda")
	private Long idAgenda;

	@NotNull
	@Column
	private String nome;
	
	@NotNull
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;
	
	@NotNull
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date termino;
	
	@NotNull
	@Column
	private String local;
	
	@NotNull
	@Column
	private String descricao;
	
	@Column
	private String emailNotificao;
	
	@Column
	private boolean notificarCasaisCadastrados;
	
	@NotNull
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacaoRegistro;
		
	public Agenda() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmailNotificao() {
		return emailNotificao;
	}

	public void setEmailNotificao(String emailNotificao) {
		this.emailNotificao = emailNotificao;
	}

	public boolean isNotificarCasaisCadastrados() {
		return notificarCasaisCadastrados;
	}

	public void setNotificarCasaisCadastrados(boolean notificarCasaisCadastrados) {
		this.notificarCasaisCadastrados = notificarCasaisCadastrados;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getDataAtualizacaoRegistro() {
		return dataAtualizacaoRegistro;
	}

	public void setDataAtualizacaoRegistro(Date dataAtualizacaoRegistro) {
		this.dataAtualizacaoRegistro = dataAtualizacaoRegistro;
	}

	public Long getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}
		



	
}
