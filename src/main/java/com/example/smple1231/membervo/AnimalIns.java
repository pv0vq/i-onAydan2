package com.example.smple1231.membervo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity //db랑 1대1 매칭
@Table(name = "AnimalIns")
public class AnimalIns {

    @Id
    @Column(name = "animalId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long animalId; // 동물 id

    @Column(name = "animalType", length = 100)
    private String animalType; // 동물 타입

    @Column(name = "intakeCondition", length = 100)
    private String intakeCondition; // 동물 컨디션

    @Column(name = "name", length = 100)
    private String name; // 동물 이름

    @Column(name = "sexUponIntake", length = 100)
    private String sexUponIntake; // 동물 중성화 유무

    @Column(name = "dateTime", length = 100)
    private Date dateTime; // 동물보호날짜

    @Column(name = "context", length = 100)
    private String context; // 동물보호날짜

}
