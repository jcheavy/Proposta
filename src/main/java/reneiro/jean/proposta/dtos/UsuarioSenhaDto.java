package reneiro.jean.proposta.dtos;

public class UsuarioSenhaDto {

	
	private String senhaAutal;
	private String novaSenha;
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
