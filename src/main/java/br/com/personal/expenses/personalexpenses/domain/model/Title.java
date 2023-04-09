package br.com.personal.expenses.personalexpenses.domain.model;

import java.util.Date;
import java.util.List;

import br.com.personal.expenses.personalexpenses.domain.Enum.EnumTypeTitle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Title {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_title")
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserAdmin user;

    private EnumTypeTitle type;

    @ManyToMany
    @JoinTable(
        name = "title_cost_center",
        joinColumns = @JoinColumn(name = "id_title"),
        inverseJoinColumns = @JoinColumn(name = "id_cost_center")
    )
    private List<CostCenter> costCenter;
    
    @Column(nullable = false)
    private Double value;

    private Date dateRegister;

    private Date dateReference;

    private Date dateDue;

    private Date datePayment;

    @Column(columnDefinition = "TEXT")
    private String observation;

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

    public UserAdmin getUser() {
        return user;
    }

    public void setUser(UserAdmin user) {
        this.user = user;
    }

    public EnumTypeTitle getType() {
        return type;
    }

    public void setType(EnumTypeTitle type) {
        this.type = type;
    }

    public List<CostCenter> getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(List<CostCenter> costCenter) {
        this.costCenter = costCenter;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Date getDateReference() {
        return dateReference;
    }

    public void setDateReference(Date dateReference) {
        this.dateReference = dateReference;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }


}
