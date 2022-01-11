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
public class HooUser {
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

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @JsonIgnore
    @ManyToMany
    @JoinTable( //다대다 관계 정의
            name = "hoo_user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})

    private Set<HooAuthority> authorities;


}
