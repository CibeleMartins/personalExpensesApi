package br.com.personal.expenses.personalexpenses.domain.exception;

public class ResourceBadRequestException extends RuntimeException {
    
    public ResourceBadRequestException(String mensagem) {
        super(mensagem);
    }
}
