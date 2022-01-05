package com.example.smple1231.controller;


import com.example.smple1231.dto.ResponseDto;
import com.example.smple1231.membervo.HooMember;
import com.example.smple1231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;



@RestController // 유저 컨트롤
public class MemberController {


    @Autowired
    private UserService userService;

//    @GetMapping("/auth/acount") //현재 로그인 ID값 반환 API
//    @ResponseBody
//    public String currentUserName(Principal principal) {
//        String accountcheck =null;
//        try { accountcheck = principal.getName();}
//       catch (NullPointerException e){accountcheck = "false";}
//        return accountcheck;
//    }

    @PostMapping("/auth/joinProc") // 회원가입 API
    public ResponseDto<Integer> save(@RequestBody HooMember user) {
        userService.memberjoin(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
