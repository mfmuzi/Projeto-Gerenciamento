package com.dio.personmngmt.enums;

//Especificação do tipo de telefone.

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

    HOME ("Home"),
    MOBILE ("Mobile"),
    COMMERCIAL ("Commercial");

    private final String description;
}
