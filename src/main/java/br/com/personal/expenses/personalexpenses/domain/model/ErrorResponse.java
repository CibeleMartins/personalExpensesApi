package br.com.personal.expenses.personalexpenses.domain.model;

public class ErrorResponse {

    private String title;

    private String message;
    
    private String dateHour;

    private Integer status;

    public ErrorResponse(String dateHour, Integer status, String title, String message) {
        this.dateHour = dateHour;
        this.status = status;
        this.title = title;
        this.message = message;
    }

    public String getDateHour() {
        return dateHour;
    }

    public void setDateHour(String dateHour) {
        this.dateHour = dateHour;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

 
}
