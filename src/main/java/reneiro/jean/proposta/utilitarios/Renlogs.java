package reneiro.jean.proposta.utilitarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.slf4j.LoggerFactory;

public final class Renlogs {

	private Renlogs() {

	}

	public static void info(Class<?> classe, String mensagem) {
		LoggerFactory.getLogger(classe).info(mensagem);
	}

	public static void warn(Class<?> classe, String mensagem) {
		LoggerFactory.getLogger(classe).warn(mensagem);
	}

	public static void error(Class<?> classe, String mensagem) {
		LoggerFactory.getLogger(classe).error(mensagem);
	}

	public static void error(Class<?> classe, String mensagem, Throwable exception) {
		LoggerFactory.getLogger(classe).error(mensagem, exception);
	}

	public static void debug(Class<?> classe, String mensagem) {
		LoggerFactory.getLogger(classe).debug(mensagem);
	}

	public static void save(String mensagem) {
		Path caminho = Path.of("logs", "application.log");
		try {
			Files.createDirectories(caminho.getParent());
			Files.writeString(caminho, mensagem + System.lineSeparator(), StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException exception) {
			LoggerFactory.getLogger(Renlogs.class).error("Não foi possível salvar o log no arquivo.", exception);
		}
	}

}
