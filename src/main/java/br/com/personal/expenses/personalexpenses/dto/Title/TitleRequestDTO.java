package br.com.personal.expenses.personalexpenses.dto.Title;

import java.util.Date;
import java.util.List;

import br.com.personal.expenses.personalexpenses.domain.Enum.EnumTypeTitle;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterRequestDTO;

public class TitleRequestDTO {

    private Long id;

    private String description;

    private EnumTypeTitle type;
   
    private List<CostCenterRequestDTO> costCenter;
 
    private Double value;

    private Date dateRegister;

    private Date dateReference;

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

    public EnumTypeTitle getType() {
        return type;
    }

    public void setType(EnumTypeTitle type) {
        this.type = type;
    }

    public List<CostCenterRequestDTO> getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(List<CostCenterRequestDTO> costCenter) {
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

    private Date dateDue;

    private Date datePayment;

    private String observation;

}
