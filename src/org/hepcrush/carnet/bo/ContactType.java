package org.hepcrush.carnet.bo;

public enum ContactType {
    PERSO("Perso"), PRO("Pro");

    private String label;

    ContactType(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }
}
