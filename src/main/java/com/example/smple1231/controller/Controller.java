package com.example.smple1231.controller;

import com.example.smple1231.membervo.ANIMAL_INS;
import com.example.smple1231.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private MemberService memberService;



    @GetMapping("/ani")// 동물 전체 리스트 API 주소
    public List<ANIMAL_INS> aniAll(){
        return memberService.aniAllService();
    }

    @GetMapping("/ani/{id}")// 상세 리스트 API 주소
    public ANIMAL_INS getani(@PathVariable("id")String id){
        return memberService.getaniService(id);
    }

    @PostMapping("/aniadd") // 동물 쓰기 API 주소
    public Long aniadd(@RequestBody ANIMAL_INS aNIMAL_INS){return memberService.aniaddService(aNIMAL_INS);}

    @PutMapping("/aniput/{id}") // 동물 리스트 수정 API 주소
    public Long aniput(@PathVariable("id") String id, @RequestBody ANIMAL_INS aNIMAL_INS){return memberService.aniputService(id, aNIMAL_INS);}

    @DeleteMapping("/anidel/{id}") // 동물 리스트 삭제 API 주소
    public Long anidel(@PathVariable("id") String id){
        return memberService.anidelService(id);
    }

    @GetMapping("/")
    public String why(){return "";}
}
