package br.com.personal.expenses.personalexpenses.domain.model;

import java.util.Collection;
import java.util.Date;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "TEXT")
    private String photo;


    @Column(nullable = false)
    private Date dataCadastro;
    
    private Date dataInativacao;

    // um (one) usuário pode ter muitos (to many) títulos
    // no parametro, é definido a entidade que é "dona" do relacionamento
    @OneToMany(mappedBy = "user")
    private List<Title> titles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }


    // métodos do UserDetails -> spring security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //   autorizações específicas
        return null;
    }

    @Override
    public String getPassword() {
        // quando o framework tentar pegar o password do usuário
        return password;
    }

    @Override
    public String getUsername() {
        // o que será usado como nome de usuário
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // a conta vai ter expiração?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // se a credencial não expira
        return true;
    }

    @Override
    public boolean isEnabled() {
        //    se a conta está ativa
        return true;
    }

   
}
