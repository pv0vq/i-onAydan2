package com.example.smple1231.membervo;

import lombok.Data;

import java.util.Date;

@Data
public class ANIMAL_INS {

    private String ANIMAL_ID; // 동물 id

    private String ANIMAL_TYPE; // 동물 타입

    private String INTAKE_CONDITION; // 동물 컨디션

    private String NAME; // 동물 이름

    private String SEX_UPON_INTAKE; // 동물 중성화 유무

    private Date DATETIME; // 동물보호날짜

}
