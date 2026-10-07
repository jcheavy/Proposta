package reneiro.jean.proposta.controllers.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDto {

	@Email(message = "O email deve ser válido")
	@NotBlank
	private String username;
	
	@NotBlank
	@Size(min = 6, max = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String password;
	
	public UsuarioLoginDto() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsuarioLoginDto [username=" + username + ", password=" + password + "]";
	}
	
	
	
}
