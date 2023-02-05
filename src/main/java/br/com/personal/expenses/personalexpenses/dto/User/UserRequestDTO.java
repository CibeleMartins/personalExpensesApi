package br.com.personal.expenses.personalexpenses.dto.User;

import java.util.Date;

public class UserRequestDTO {
 
    private String name;

  
    private String email;

    
    private String password;


    private String photo;


    public String getNome() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordUser() {
        return password;
    }
    
    public void setPasswordUser(String password) {
        this.password = password;
    }

    public String getFoto() {
        return photo;
    }

    public void setFoto(String foto) {
        this.photo = foto;
    }


}
