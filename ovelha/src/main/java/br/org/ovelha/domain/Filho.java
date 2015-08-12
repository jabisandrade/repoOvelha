package br.org.ovelha.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import br.org.ovelha.util.CDIFactory;

@SequenceGenerator(name = "seqFilho", sequenceName = "idFilho_seq", allocationSize = 1)
@Entity
public class Filho extends Discipulo  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqFilho")
	@Column(name = "idFilho")
	private Long idFilho;
	
	@Transient
	private String nomePais;
	
	
	private Long idCasal;

	
	public Filho() {
		super();
	}

	public Long getIdFilho() {
		return idFilho;
	}

	public void setIdFilho(Long idFilho) {
		this.idFilho = idFilho;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return idFilho;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.idFilho = id;
	}

	/**
	 * @return the idCasal
	 */
	public Long getIdCasal() {
		return idCasal;
	}

	/**
	 * @param idCasal the idCasal to set
	 */
	public void setIdCasal(Long idCasal) {
		this.idCasal = idCasal;
	}

	public String getNomePais() {
		nomePais = CDIFactory.getCasalBC().getNomePais(this.idCasal);
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
	
	



		
	
}
