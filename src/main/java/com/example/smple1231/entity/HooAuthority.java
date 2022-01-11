package com.example.smple1231.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hoo_authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HooAuthority { // 권한 Entity

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

}
