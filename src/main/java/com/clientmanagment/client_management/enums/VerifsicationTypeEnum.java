package com.clientmanagment.client_management.enums;

public enum VerifsicationTypeEnum {
    ACCOUNT("ACCOUNT"),
    PASSWORD("PASSWORD");

    private final String type;

    VerifsicationTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return this.type.toLowerCase();
    }
}
