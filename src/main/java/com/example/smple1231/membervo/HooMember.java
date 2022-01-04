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
    @GeneratedValue(strategy = GenerationType.AUTO) // 시퀸스 생성
    private int id;

    @Column(nullable= false, length =30, unique = true) //유저네임
    private String username;

    @Column(nullable= false, length =100)  // 패스워드
    private String password;

    @Column(nullable= false, length =30)
    private String email;


    @Enumerated(EnumType.STRING)  // 권한
    private RoleType role;


}
