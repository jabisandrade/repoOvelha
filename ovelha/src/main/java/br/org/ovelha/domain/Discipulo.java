package br.org.ovelha.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@MappedSuperclass
public class Discipulo implements EntidadeIf  {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column
	private String nome;
	
	@Column
	private String nomeLider;
	
	@Column
	private String nomeLiderMacro;
	
	@Column
	private Sexo sexo;
	
	@Column
	private String endereco;
				
	@Column
	private String telefoneCelular;
	
	@Column
	private String telefoneResidencial;
	
	@Column
	private String email;
		
	@Column(columnDefinition="boolean default false")
	private boolean batizado;
	
	@Column(columnDefinition="boolean default false")
	private boolean encontro;
	
	@Column(columnDefinition="boolean default false")
	private boolean posEncontro;
	
	@Column(columnDefinition="boolean default false")
	private boolean capacitacaoDestino;

	@NotNull
	@Column
	@Temporal(TemporalType.DATE)
	private Date dataAniversario;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacaoRegistro;
	
	public Discipulo() {
		super();
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBatizado() {
		return batizado;
	}

	public void setBatizado(boolean batizado) {
		this.batizado = batizado;
	}

	public boolean isEncontro() {
		return encontro;
	}

	public void setEncontro(boolean encontro) {
		this.encontro = encontro;
	}

	public boolean isPosEncontro() {
		return posEncontro;
	}

	public void setPosEncontro(boolean posEncontro) {
		this.posEncontro = posEncontro;
	}

	public boolean isCapacitacaoDestino() {
		return capacitacaoDestino;
	}

	public void setCapacitacaoDestino(boolean capacitacaoDestino) {
		this.capacitacaoDestino = capacitacaoDestino;
	}

	public Date getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
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

	public String getNomeLider() {
		return nomeLider;
	}

	public void setNomeLider(String nomeLider) {
		this.nomeLider = nomeLider;
	}

	public String getNomeLiderMacro() {
		return nomeLiderMacro;
	}

	public void setNomeLiderMacro(String nomeLiderMacro) {
		this.nomeLiderMacro = nomeLiderMacro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}



	
}
