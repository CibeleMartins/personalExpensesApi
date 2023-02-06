package br.com.personal.expenses.personalexpenses.domain.model;

public class ErrorResponse {
    
    private String dateHour;

    private Integer status;

    private String title;

    private String mensagem;

    public ErrorResponse(String dateHour, Integer status, String title, String mensagem) {
        this.dateHour = dateHour;
        this.status = status;
        this.title = title;
        this.mensagem = mensagem;
    }
}
