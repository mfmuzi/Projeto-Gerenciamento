package com.dio.personmngmt.utils;

import com.dio.personmngmt.dto.request.PhoneDTO;
import com.dio.personmngmt.entity.Phone;
import com.dio.personmngmt.enums.PhoneType;

public class PhonesUtils {

//Variáveis do telefone do usuário.
    private static final String PHONE_NUMBER = "(16)999995432";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

//Variáveis do PhoneDTO.
    public static PhoneDTO createFakeDTO(){
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

//Variáveis Phone.
    public static Phone createFakeEntity(){
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
