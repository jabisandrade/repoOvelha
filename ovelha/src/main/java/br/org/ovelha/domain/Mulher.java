package br.org.ovelha.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

@SequenceGenerator(name = "seqMulher", sequenceName = "idMulher_seq", allocationSize = 1)
@Entity
public class Mulher extends Discipulo  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMulher")
	@Column(name = "idMulher")
	private Long idMulher;
		
	@JoinColumn(name = "idCasal")
	private Long idCasal;
	
	public Mulher() {
		super();
	}

	public Long getIdMulher() {
		return idMulher;
	}

	public void setIdMulher(Long idMulher) {
		this.idMulher = idMulher;
	}

	public Long getIdCasal() {
		return idCasal;
	}

	public void setIdCasal(Long idCasal) {
		this.idCasal = idCasal;
	}
	




	
}
