package reneiro.jean.proposta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import reneiro.jean.proposta.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
