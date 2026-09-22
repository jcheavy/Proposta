package reneiro.jean.proposta.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import reneiro.jean.proposta.exceptions.EntitiesNotFoundException;
import reneiro.jean.proposta.exceptions.ErrorMessage;
import reneiro.jean.proposta.exceptions.UsernameUniqueViolationException;
import reneiro.jean.proposta.utilitarios.Renlogs;

@RestControllerAdvice
public class ApiExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> methoArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request, BindingResult bindingResult) {

		Renlogs.error(ApiExceptionHandler.class, "Api Erro :", ex);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_CONTENT, "Campos inválidos", bindingResult));
	}
	
	
	@ExceptionHandler(UsernameUniqueViolationException.class)
	public ResponseEntity<ErrorMessage> methoArgumentNotValidException(RuntimeException ex,
			HttpServletRequest request) {

		Renlogs.error(ApiExceptionHandler.class, "Api Erro :", ex);
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
	}
	
	
	@ExceptionHandler(EntitiesNotFoundException.class)
	public ResponseEntity<ErrorMessage> entitiesNotFoundException(RuntimeException ex,
			HttpServletRequest request) {

		Renlogs.error(ApiExceptionHandler.class, "Api Erro :", ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
	}
}
