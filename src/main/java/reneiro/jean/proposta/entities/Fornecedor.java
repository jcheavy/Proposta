package reneiro.jean.proposta.entities;

import java.io.Serializable;
import java.util.List;

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
	
	
}
