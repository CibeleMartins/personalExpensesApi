package br.com.personal.expenses.personalexpenses.domain.model;

public class ErrorResponse {
    
    private String dateHour;

    private Integer status;

    private String title;

    private String message;

    public ErrorResponse(String dateHour, Integer status, String title, String message) {
        this.dateHour = dateHour;
        this.status = status;
        this.title = title;
        this.message = message;
    }
}
