package reneiro.jean.proposta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import reneiro.jean.proposta.entities.Usuario;
import reneiro.jean.proposta.enums.Role;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	  Optional<Usuario> findByUsername(String username);

	  @Query("select u.role from Usuario u where u.username like:username")
	  Role findRoleByUsername(String username);
}
