package reneiro.jean.proposta.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioSenhaDto {

	@NotBlank
	@Size(min = 6, max = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String senhaAutal;
	
	@NotBlank
	@Size(min = 6, max = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String novaSenha;
	
	@NotBlank
	@Size(min = 6, max = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String confimaNovaSenha;
	
	public UsuarioSenhaDto() {
	}

	public String getSenhaAutal() {
		return senhaAutal;
	}

	public void setSenhaAutal(String senhaAutal) {
		this.senhaAutal = senhaAutal;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfimaNovaSenha() {
		return confimaNovaSenha;
	}

	public void setConfimaNovaSenha(String confimaNovaSenha) {
		this.confimaNovaSenha = confimaNovaSenha;
	}

	@Override
	public String toString() {
		return "UsuarioSenhaDto [senhaAutal=" + senhaAutal + ", novaSenha=" + novaSenha + ", confimaNovaSenha="
				+ confimaNovaSenha + "]";
	}
	
	
}
