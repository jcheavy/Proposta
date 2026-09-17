package reneiro.jean.proposta.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import reneiro.jean.proposta.enums.UnidadesDeMedida;


@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String nome;

	private String descricao;

	@Column(length = 10)
	private BigDecimal precoDeVenda;
	
	@Column(length = 100)
	private String modelo;
	
	@Column(length = 10)
	private BigDecimal valorCusto;
	
	@Enumerated(EnumType.STRING)
	private UnidadesDeMedida undidadeDeMedida;
	
	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	public Produto() {
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecoDeVenda() {
		return precoDeVenda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", precoDeVenda=" + precoDeVenda
				+ ", modelo=" + modelo + ", valorCusto=" + valorCusto + ", undidadeDeMedida=" + undidadeDeMedida
				+ ", marca=" + marca + "]";
	}

	
}
