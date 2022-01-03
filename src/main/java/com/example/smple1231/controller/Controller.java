package com.example.smple1231.controller;

import com.example.smple1231.membervo.ANIMAL_INS;
import com.example.smple1231.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    private MemberService memberService;
    @GetMapping("/ani")
    public List<ANIMAL_INS> aniAll(){
        return memberService.aniAllService();
    }

    @GetMapping("/ani/{id}")
    public ANIMAL_INS getani(@PathVariable("id")String id){
        return memberService.getaniService(id);
    }
    @PostMapping("/aniadd")
    public Long aniadd(@RequestBody ANIMAL_INS aNIMAL_INS){
        return memberService.aniaddService(aNIMAL_INS);
    }
    @PutMapping("/aniput/{id}")
    public Long aniput(@PathVariable("id") String id, @RequestBody ANIMAL_INS aNIMAL_INS){
        return memberService.aniputService(id, aNIMAL_INS);
    }
    @DeleteMapping("/anidel/{id}")
    public Long anidel(@PathVariable("id") String id){
        return memberService.anidelService(id);
    }
}
