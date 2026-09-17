package reneiro.jean.proposta.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(nullable = false, length = 150)
	private String nomeFantasia;
	
	@Column(nullable = false, length = 150)
	private String nomeSocial;
	
	@Column(nullable = false,  unique = true, length = 15)
	private String cnpj;
	
	@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)	   
	private Endereco endereco;
	
	
}
