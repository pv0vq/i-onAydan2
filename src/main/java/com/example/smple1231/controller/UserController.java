package com.example.smple1231.controller;


import com.example.smple1231.dto.LoginDto;
import com.example.smple1231.dto.TokenDto;
import com.example.smple1231.dto.UserDto;
import com.example.smple1231.entity.HooUser;
import com.example.smple1231.jwt.JwtFilter;
import com.example.smple1231.jwt.TokenProvider;
import com.example.smple1231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public UserController(UserService userService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/login") // 로그인 api 토큰발급
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken = // LoginDto를 이용해 username과 password를 받고 토큰생성
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);//SecurityContextHolder에 Authentication객체 저장

        String jwt = tokenProvider.createToken(authentication); // 토큰을 발급

        HttpHeaders httpHeaders = new HttpHeaders(); //응답해더에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);// //응답해더에 넣어줌
    }


    @PostMapping("/signup")// 회원가입 api
    public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user") //두가지권한 모두 허용한 유저정보 조회
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }


}