package com.example.smple1231.membervo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="hoomember")
@Getter
@Setter
@ToString
public class HooMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY:프로젝트에서 연결된 Db의 넘버링전략을 따락간다 , SEQUENCE: 시퀀스 새성
    private int id; //시퀀스

    @Column(nullable= false, length =30, unique = true) // 널값은 받지않고 길이는 30
    private String username;
    @Column(nullable= false, length =100) // 비밀번호를 hash로 변경후 암호화 할꺼니까
    private String password;
    @Column(nullable= false, length =30)
    private String email;
//    @Column(nullable = false, length = 64)
//    private String token;


    //@ColumnDefault("'user'")//따음표 주의 기본으로 user로 준다
    @Enumerated(EnumType.STRING) //db는 Roletype이라는게 없기에
    private RoleType role; //Enum을 쓰는게 좋다 //도메인을 만들 수 있다. (ex role은 admin이다)//도메인은 범위를 정해졌다
    //db는

}
