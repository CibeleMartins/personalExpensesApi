package br.com.personal.expenses.personalexpenses.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cost_center")
public class CostCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cost_center")
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "TEXT")
    private String observation;

    // muitos (many) centor de custos p/ um (to one) usuário
    // join column define quem é o dono desse relacionamento/ do centro de custo/
    // por isso o id do usuário
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserAdmin user;

    // muitos centros de custo podem estar em muitos titulos
    // a tabela que cria/ "é dona" do relacionamento é a center_cost
    // a segunda anotação define que, ao buscar o centros de custos, irá retornar apenas os centros de custo e não os títulos
    // ex: busco o centro de custo e os titulos, entro no titulo, pego ocentro de custo -> loop infinito
    // @ManyToMany(mappedBy = "cost_center")
    // @JsonBackReference
    // private List<Title> titles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public UserAdmin getUser() {
        return user;
    }

    public void setUser(UserAdmin userAdmin) {
        this.user = userAdmin;
    }

    // public List<Title> getTitles() {
    //     return titles;
    // }

    // public void setTitles(List<Title> titles) {
    //     this.titles = titles;
    // } 

}
