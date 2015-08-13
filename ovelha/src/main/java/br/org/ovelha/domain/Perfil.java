package br.org.ovelha.domain;

public enum Perfil {
	
	ADM(1, "Administrador"),
	PUB(2, "Público");

	private final Integer id;
	private final String descricao;

	private Perfil(Integer id, String descricao) {
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

	public static Perfil get(Integer id) {

		for (Perfil item : values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}

		return null;
	}
	
	public boolean isADM() {
		return this.equals(Perfil.ADM);
	}
	
	public boolean isPUB() {
		return this.equals(Perfil.PUB);
	}

}
