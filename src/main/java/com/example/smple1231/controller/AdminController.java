package com.example.smple1231.controller;

import com.example.smple1231.dto.UserDto;
import com.example.smple1231.entity.HooUser;
import com.example.smple1231.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user/{username}") //admin권한만 허용한 유저정보 조회
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(adminService.getUserWithAuthorities(username));
    }


    @GetMapping("/Hoouser")   // 관리자 권한 전체 멤버 조회
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN만 허용
    public ResponseEntity<List<HooUser>> usergetAll( ) {
        return ResponseEntity.ok(adminService.usergetAll());
    }


    @PutMapping("/Hoouserput/{id}") // 관리자 권한으로 맴버 수정
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN만 허용
    public ResponseEntity<String> userput(@PathVariable("id") Long id, @RequestBody UserDto userDto)
    {
        return  ResponseEntity.ok(adminService.userput(id, userDto));
    }

    @PutMapping("/HoouserIns") // 관리자 권한으로 맴버 수정
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN만 허용
    public ResponseEntity<String> userIn(@RequestBody UserDto userDto)
    {
        return  ResponseEntity.ok(adminService.userIn(userDto));
    }

    @DeleteMapping("/deleteuser/{id}") // 관리자 권한으로 맴버 삭제
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN만 허용
    public ResponseEntity<String> userdel(@PathVariable("id") Long id)
    {
        return  ResponseEntity.ok(adminService.userdel(id));
    }




}
