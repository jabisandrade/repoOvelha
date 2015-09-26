package br.org.ovelha.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@SequenceGenerator(name = "seqCasal", sequenceName = "idCasal_seq", allocationSize = 1)
@Entity
public class Casal implements EntidadeIf {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCasal")
	@Column(name = "idCasal")
	private Long idCasal;	
			
	@NotNull
	@Column
	@Temporal(TemporalType.DATE)
	private Date dataAniversarioCasamento;
	
	@NotNull
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;
		
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacaoRegistro;
	
	@Column(columnDefinition="boolean default false")
	private boolean casadosParaSempre;
	
	@Column(columnDefinition="boolean default false")
	private boolean encontroCasais;
	
    @OneToOne 
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    

	@Transient
	private Homem marido = new Homem();
	
	@Transient
	private Mulher esposa = new Mulher();
		
	@OneToMany(mappedBy = "idCasal", targetEntity = Filho.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Filho> filhos = new ArrayList<Filho>();	
		
	public Casal() {
		super();		
	}

	public Date getDataAniversarioCasamento() {
		return dataAniversarioCasamento;
	}

	public void setDataAniversarioCasamento(Date dataAniversarioCasamento) {
		this.dataAniversarioCasamento = dataAniversarioCasamento;
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

	public boolean isCasadosParaSempre() {
		return casadosParaSempre;
	}

	public void setCasadosParaSempre(boolean casadosParaSempre) {
		this.casadosParaSempre = casadosParaSempre;
	}

	public boolean isEncontroCasais() {
		return encontroCasais;
	}

	public void setEncontroCasais(boolean encontroCasais) {
		this.encontroCasais = encontroCasais;
	}

	public Homem getMarido() {
		return marido;
	}

	public void setMarido(Homem marido) {
		this.marido = marido;
	}

	public Mulher getEsposa() {
		return esposa;
	}

	public void setEsposa(Mulher esposa) {
		this.esposa = esposa;
	}

	
	public Collection<Filho> getFilhos() {
		return filhos;
	}

	public void setFilhos(Collection<Filho> filhos) {
		this.filhos = filhos;		
	}

	public Long getIdCasal() {
		return idCasal;
	}

	public void setIdCasal(Long idCasal) {
		this.idCasal = idCasal;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return idCasal;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.idCasal = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	
}
