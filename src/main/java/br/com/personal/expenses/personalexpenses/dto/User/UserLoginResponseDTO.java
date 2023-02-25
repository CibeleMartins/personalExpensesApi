package br.com.personal.expenses.personalexpenses.dto.User;

public class UserLoginResponseDTO {
    
    private String tokenAuthorization;

    private UserResponseDTO user;

    public String getTokenAuthorization() {
        return tokenAuthorization;
    }

    public void setTokenAuthorization(String tokenAuthorization) {
        this.tokenAuthorization = tokenAuthorization;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    
}
