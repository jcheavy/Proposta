package reneiro.jean.proposta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import reneiro.jean.proposta.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);

}
