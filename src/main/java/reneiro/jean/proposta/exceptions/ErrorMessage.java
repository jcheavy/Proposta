package reneiro.jean.proposta.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorMessage {

	
	private String path;
	private String method;
	private int status;
	private String statusMessage;
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)  //só inclui o campo se não for nulo
	private Map<String, String> errors;
	
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage( HttpServletRequest request, HttpStatus status, String message) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusMessage = status.getReasonPhrase();
		this.message = message;
		
	}
	
	public ErrorMessage( HttpServletRequest request, HttpStatus status, String message, BindingResult bindingResult) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusMessage = status.getReasonPhrase();
		this.message = message;
		addErros(bindingResult);
	}
	
	private void addErros(BindingResult bindingResult) {
		this.errors = new HashMap<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorMessage [path=" + path + ", method=" + method + ", status=" + status + ", statusMessage="
				+ statusMessage + ", message=" + message + ", errors=" + errors + "]";
	}


	
	
}
