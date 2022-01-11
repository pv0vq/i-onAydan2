package com.example.smple1231.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity //db랑 1대1 매칭
@Table(name = "hoo_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HooUser { // 유저 Entity
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "adress", length = 50)
    private String adress;

    @Column(name = "adressDetail", length = 50)
    private String adressDetail;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @JsonIgnore
    @ManyToMany
    @JoinTable( //다대다 관계 정의 권한과 유저를 묶어주는 Entity
            name = "hoo_user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})

    private Set<HooAuthority> authorities;


}
