package br.com.personal.expenses.personalexpenses.domain.Enum;

public enum EnumTypeTitle {
    RECEIVABLE("ARECEBER"),
    PAYABLE("APAGAR");

    private String value;

    private EnumTypeTitle(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
