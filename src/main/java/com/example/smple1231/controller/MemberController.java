package com.example.smple1231.controller;

import com.example.smple1231.auth.PrincipalDetail;
import com.example.smple1231.dto.ResponseDto;
import com.example.smple1231.membervo.HooMember;
import com.example.smple1231.repository.UserRepository;
import com.example.smple1231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;




@CrossOrigin
@RestController
public class MemberController {
    //test

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/auth/acount")
    @ResponseBody
    public String currentUserName(Principal principal) {
        String accountcheck =null;
        try {
            accountcheck = principal.getName();
            System.out.println(principal.getName());
        }
       catch (NullPointerException e){
           accountcheck = "false";
       }
        return accountcheck;
    }


    @GetMapping("/auth/loginForm")
    public String loginForm() {

        return "user/loginProc";

    }
    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {//Authentication 객체를 들고옴

        return "user/updateForm";

    }


    @PostMapping("/auth/joinProc") // user.js에서 ajax통신으로 넘오온 json데이터를 받음
    public ResponseDto<Integer> save(@RequestBody HooMember user) {
        System.out.println("UserApiController:save 호출완료");
        // UserService db에서 Insert하고 아래에서 리턴해주면 끝

        userService.memberjoin(user); // 서비스클래스에 회원가입실행
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // user.js =>.done(function(resp) resp에 값이 들어감
        // HttpStatus.OK는 200이 뜸 200은 통신성공이라는 뜻
        // 자바오브젝트를 json으로 변환해서 리턴(jackson)

    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody HooMember user,
                                       @AuthenticationPrincipal PrincipalDetail principal, HttpSession session ) {// json타입 데이터를 받기위해

        userService.memberre(user);
        // 트랜잭션이 종료되기 때문에 db값은 변경이 됐음
        // 하지만 세션값은 변경이 안됨 그래서 우리가 직접 세션값을 변경해줄꺼임

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()); //authentication객체만들기
        SecurityContext securityContext = SecurityContextHolder.getContext();//SecurityContextHolder안에있는 SecurityContext까지 접근
        securityContext.setAuthentication(authentication); //SecurityContext에authentication객체넣기
        session.setAttribute("SPRING_SECURITY_CONTEXT",securityContext); //세션이 SecurityContext담기


        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

}
