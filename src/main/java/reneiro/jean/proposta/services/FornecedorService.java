package reneiro.jean.proposta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reneiro.jean.proposta.entities.Endereco;
import reneiro.jean.proposta.entities.Fornecedor;
import reneiro.jean.proposta.repositories.EnderecoRepository;
import reneiro.jean.proposta.repositories.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository categoriaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public Fornecedor adicionar(Fornecedor fornecedor) {
	
		List<Endereco> enderecos = fornecedor.getEnderecos();
		enderecos.forEach(endereco -> endereco.setFornecedor(fornecedor));
		Fornecedor fornecedorSalvo = categoriaRepository.save(fornecedor);
		return categoriaRepository.save(fornecedorSalvo);
	}

	@Transactional(readOnly = true)
	public Fornecedor findById(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Fornecedor> listar() {
		return categoriaRepository.findAll();
	}

	public Fornecedor atualizar(Long id, Fornecedor fornecedor) {

		Fornecedor categoriaExistente = findById(id);
		updateData(categoriaExistente, fornecedor);
		return categoriaRepository.save(categoriaExistente);
	}

	public void deletar(Long id) {
		categoriaRepository.deleteById(id);
	}

	private void updateData(Fornecedor fornecedorExistente, Fornecedor fornecedor) {

		List<Endereco> enderecoExistentes = fornecedorExistente.getEnderecos();

		if (fornecedor.getEnderecos().size() < 0) {
			atualizarEnderecos(enderecoExistentes, fornecedor.getEnderecos());
		}

		fornecedorExistente.setEmail(fornecedor.getEmail());
		fornecedorExistente.setTelefone(fornecedor.getTelefone());
		fornecedorExistente.setCnpj(fornecedor.getCnpj());
		fornecedorExistente.setNomeFantasia(fornecedor.getNomeFantasia());
		fornecedorExistente.setNomeSocial(fornecedor.getNomeSocial());
		fornecedorExistente.setEnderecos(enderecoExistentes);

	}

	private void atualizarEnderecos(List<Endereco> enderecosExistentes, List<Endereco> enderecosNovos) {

		for (Endereco enderecoNovo : enderecosNovos) {

			if (enderecoNovo.getId() == null) {
				enderecosExistentes.add(enderecoNovo);
			} else {

				Optional<Endereco> enderecoExistente = enderecosExistentes.stream()
						.filter(e -> e.getId().equals(enderecoNovo.getId())).findFirst();

				if (enderecoExistente.isPresent()) {

					Endereco endereco = enderecoExistente.get();

					endereco.setRua(enderecoNovo.getRua());
					endereco.setNumero(enderecoNovo.getNumero());
					endereco.setBairro(enderecoNovo.getBairro());
					endereco.setCidade(enderecoNovo.getCidade());
					endereco.setCep(enderecoNovo.getCep());

				} else {
					// ID informado, mas não existe na lista atual
					enderecosExistentes.add(enderecoNovo);
				}
			}
		}
	}

}
