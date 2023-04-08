package br.com.personal.expenses.personalexpenses.domain.enum;

public enum EnumTypeTitle {
    RECEIVABLE("A receber"),
    PAYABLE("A pagar");

    private String value;

    private EnumTypeTitle(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
