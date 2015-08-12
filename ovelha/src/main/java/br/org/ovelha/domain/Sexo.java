package br.org.ovelha.domain;

public enum Sexo {
	
	M(1, "Masculino"),
	F(2, "Feminino");

	private final Integer id;
	private final String descricao;

	private Sexo(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}

	public static Sexo get(Integer id) {

		for (Sexo item : values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}

		return null;
	}

}
