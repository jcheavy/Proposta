package reneiro.jean.proposta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reneiro.jean.proposta.entities.Categoria;
import reneiro.jean.proposta.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional
	public Categoria adicionar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}	
	
	@Transactional(readOnly = true)
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	
	public Categoria atualizar(Long id, Categoria categoria) {
		
		Categoria categoriaExistente = findById(id);
		updateData(categoriaExistente, categoria);
		return categoriaRepository.save(categoriaExistente);
	}
	
	public void deletar(Long id) {
		categoriaRepository.deleteById(id);
	}
	
	private void updateData(Categoria categoriaExistente, Categoria categoria) {
        categoriaExistente.setNome(categoria.getNome());
    }
	
}
