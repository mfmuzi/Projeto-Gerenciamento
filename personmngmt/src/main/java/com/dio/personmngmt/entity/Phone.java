package com.dio.personmngmt.entity;

//Variáveis do telefone de cada usuário.

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dio.personmngmt.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data // insere getter e setter.
@Builder // constroi os objetos.
@AllArgsConstructor //insere os contrutores.
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
