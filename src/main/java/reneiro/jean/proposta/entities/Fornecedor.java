package reneiro.jean.proposta.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(nullable = false, length = 150)
	@Length(min = 10, max = 150, message = "O campo nome-fantasia deve ter entre 10 e 150 caracteres")
	private String nomeFantasia;
	
	@Column(nullable = false, length = 150)
	@Length(min = 10, max = 150, message = "O campo nome-social deve ter entre 10 e 150 caracteres")
	private String nomeSocial;
	
	@Column(nullable = false,  unique = true, length = 15)
	@Length(min = 15, max = 15, message = "O campo cnpj deve ter 15 caracteres")
	private String cnpj;
	
	@Length(min = 15, max = 15, message = "O campo Email deve ter 15 caracteres")
	private String telefone;
	
	@Column( length = 150)	
	private String email;	
	
	@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
	private List<Endereco> enderecos;
	
	public Fornecedor() {
	}

	//Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nomeFantasia=" + nomeFantasia + ", nomeSocial=" + nomeSocial + ", cnpj="
				+ cnpj + ", telefone=" + telefone + ", email=" + email + ", enderecos=" + enderecos + "]";
	}
	
	
	
	
	
	
}
