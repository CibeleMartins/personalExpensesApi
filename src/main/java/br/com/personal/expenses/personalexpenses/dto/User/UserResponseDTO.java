package br.com.personal.expenses.personalexpenses.dto.User;

import java.util.Date;

public class UserResponseDTO {

    private Long id;

    private String nameUser;

    private String email;

    private String photo;

    private Date dateRegister;

    private Date dateInativation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDateInativation() {
        return dateInativation;
    }

    public void setDateInativation(Date dateInativation) {
        this.dateInativation = dateInativation;
    }
    
    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

}
