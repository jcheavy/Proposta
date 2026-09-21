package reneiro.jean.proposta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reneiro.jean.proposta.entities.Endereco;
import reneiro.jean.proposta.repositories.EnderecoRepository;


@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	@Transactional
	public Endereco adicionar(Endereco endereco) {
		return repository.save(endereco);
	}	
	
	@Transactional(readOnly = true)
	public Endereco findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public List<Endereco> listar() {
		return repository.findAll();
	}
	
	
	public Endereco atualizar(Long id, Endereco categoria) {
		
		Endereco categoriaExistente = findById(id);
		updateData(categoriaExistente, categoria);
		return repository.save(categoriaExistente);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	private void updateData(Endereco enderecoExistente, Endereco enderecoNovo) {
               
        enderecoExistente.setRua(enderecoNovo.getRua());
        enderecoExistente.setNumero(enderecoNovo.getNumero());
        enderecoExistente.setBairro(enderecoNovo.getBairro());
        enderecoExistente.setCidade(enderecoNovo.getCidade());
        enderecoExistente.setCep(enderecoNovo.getCep());
        enderecoExistente.setComplemento(enderecoNovo.getComplemento());
        enderecoExistente.setCidade(enderecoNovo.getCidade());
    }
	
}
