package com.example.smple1231.controller;

import com.example.smple1231.membervo.AnimalIns;
import com.example.smple1231.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log
public class Controller {

    @Autowired
    private MemberService memberService;


    @GetMapping("/ani")// 동물 전체 리스트 API 주소
    public List<AnimalIns> aniAll(){
        return memberService.aniAllService();
    }

    @GetMapping("/ani/{id}")// 상세 리스트 API 주소
    public Optional<AnimalIns> getani(@PathVariable("id")Long id){
        return memberService.getaniService(id);
    }

    @PostMapping("/aniadd") // 동물 쓰기 API 주소
    public AnimalIns aniadd(@RequestBody AnimalIns animalIns){
        return memberService.aniaddService(animalIns);
    }

    @PutMapping("/aniput/{id}") // 동물 리스트 수정 API 주소
    public ResponseEntity<String> aniput(@PathVariable("id") Long id, @RequestBody AnimalIns animalIns){
        return ResponseEntity.ok(memberService.aniputService(id, animalIns));
    }
    @PutMapping("/aniputgird") // 동물 리스트 수정 API 주소
    public ResponseEntity<String> aniput(@RequestBody AnimalIns animalIns){
        return ResponseEntity.ok(memberService.aniPutGridService(animalIns));
    }

    @DeleteMapping("/anidel/{id}") // 동물 리스트 삭제 API 주소
    public ResponseEntity<String> anidel(@PathVariable("id") Long id){
        return ResponseEntity.ok(memberService.anidelService(id));
    }

    @GetMapping("/")
    public String why(){return "";}
}
