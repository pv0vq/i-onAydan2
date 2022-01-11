package com.example.smple1231.dto;


import com.example.smple1231.entity.HooUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto { //userDto

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    @NotNull
    @Size(min = 3, max = 50)
    private String phone;

    @NotNull
    @Size(min = 3, max = 50)
    private String adress;

    @NotNull
    @Size(min = 3, max = 50)
    private String adressDetail;

    private Set<AuthorityDto> authorityDtoSet;

    public static UserDto from(HooUser user) {
        if(user == null) return null;

        return UserDto.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .adress(user.getAdress())
                .adressDetail(user.getAdressDetail())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
