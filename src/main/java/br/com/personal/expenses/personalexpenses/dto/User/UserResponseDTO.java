package br.com.personal.expenses.personalexpenses.dto.User;

import java.util.Date;

public class UserResponseDTO {

    private Long id;

    private String name;

    private String photo;

    private Date dataCadastro;
    
    private Date dataInativacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getFoto() {
        return photo;
    }

    public void setFoto(String foto) {
        this.photo = foto;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataInativacao() {
        return dataInativacao;
    }

    public void setDataInativacao(Date dataInativacao) {
        this.dataInativacao = dataInativacao;
    }

}
