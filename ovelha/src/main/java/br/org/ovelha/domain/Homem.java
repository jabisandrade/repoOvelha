package br.org.ovelha.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

@SequenceGenerator(name = "seqHomem", sequenceName = "idHomem_seq", allocationSize = 1)
@Entity
public class Homem extends Discipulo  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqHomem")
	@Column(name = "idHomem")
	private Long idHomem;
		
	@JoinColumn(name = "idCasal")
	private Long idCasal;
	
	
	public Homem() {
		super();
	}


	public Long getIdHomem() {
		return idHomem;
	}


	public void setIdHomem(Long idHomem) {
		this.idHomem = idHomem;
	}



	public Long getIdCasal() {
		return idCasal;
	}


	public void setIdCasal(Long idCasal) {
		this.idCasal = idCasal;
	}
	





		
	
}
