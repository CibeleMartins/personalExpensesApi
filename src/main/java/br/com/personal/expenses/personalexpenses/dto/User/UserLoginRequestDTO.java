package br.com.personal.expenses.personalexpenses.dto.User;

public class UserLoginRequestDTO {
    
    private String email;

    private String passwordUser;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    
}
