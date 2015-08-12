package br.org.ovelha.domain.dto;

import java.util.Date;

import br.org.ovelha.util.Data;

public class DatasComemorativas {

	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private  final String CASAMENTO = "CASAMENTO";
	private  final String NASCIMENTO = "NASCIMENTO";
	
	private String nome;
	private Date data;
	private String dataDiaMes;
	private String evento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {		
		this.dataDiaMes = Data.dataDiaMes(data);
		this.data = data;		
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public  String getCasamento() {
		return this.CASAMENTO;
	}
	public  String getNascimento() {
		return this.NASCIMENTO;
	}
	/**
	 * @return the dataDiaMes
	 */
	public String getDataDiaMes() {
		return dataDiaMes;
	}
	/**
	 * @param dataDiaMes the dataDiaMes to set
	 */
	public void setDataDiaMes(String dataDiaMes) {
		this.dataDiaMes = dataDiaMes;
	}
}
