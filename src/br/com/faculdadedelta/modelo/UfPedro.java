package br.com.faculdadedelta.modelo;

public class UfPedro {

	private Long id;
	private String nome;
	private String sigla;
	private String codigo;
	private PaisPedro pais;

	public UfPedro() {
		super();
	}

	public UfPedro(Long id, String nome, String sigla, String codigo, PaisPedro pais) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.codigo = codigo;
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public PaisPedro getPais() {
		return pais;
	}

	public void setPais(PaisPedro pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UfPedro other = (UfPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
