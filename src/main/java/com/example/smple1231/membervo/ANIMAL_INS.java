package com.example.smple1231.membervo;

import lombok.Data;

import java.util.Date;

@Data
public class ANIMAL_INS {

    private String ANIMAL_ID;
    private String ANIMAL_TYPE;
    private String INTAKE_CONDITION;
    private String NAME;
    private String SEX_UPON_INTAKE;
    private Date DATETIME;

}
