package com.invillia.poc01.enums;


import com.invillia.poc01.validation.groupValidation.CnpjGroup;
import com.invillia.poc01.validation.groupValidation.CpfGroup;

public enum DocumentType {

    CPF ("CPF", "000.000.000-00", CpfGroup.class),
    CNPJ("CNPJ", "00.000.000/0000-00", CnpjGroup.class);

    private final String document;
    private final String mask;
    private final Class<?> group;

    DocumentType(String document, String mask, Class<?> group) {
        this.document = document;
        this.mask = mask;
        this.group = group;
    }

    public String getDocument() {
        return document;
    }

    public String getMask() {
        return mask;
    }

    public Class<?> getGroup() {
        return group;
    }
}
