package com.example.smple1231.service;

import com.example.smple1231.dto.UserDto;
import com.example.smple1231.entity.HooAuthority;
import com.example.smple1231.entity.HooUser;
import com.example.smple1231.exception.DuplicateMemberException;
import com.example.smple1231.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true) // 관리자 전체 유저 정보 서비스
    public List<HooUser> usergetAll(){return userRepository.findAll();}

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username) { //관리자 정보 가져옴
        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional // 회원 수정 서비스
    public String userput(Long id, UserDto userDto){
        Optional<HooUser> updateUser = userRepository.findById(id);
        updateUser.ifPresent(selectUser -> { //! = null코딩줄임
            selectUser.setNickname(userDto.getNickname());
            selectUser.setPhone(userDto.getPhone());
            selectUser.setAdress(userDto.getAdress());
            selectUser.setAdressDetail(userDto.getAdressDetail());
            userRepository.save(selectUser);
        });

        return "회원수정완료" ;
    }
    @Transactional // 회원 수정 서비스
    public String userIn(UserDto userDto){
        Optional<HooUser> updateUser = userRepository.findByUsername(userDto.getUsername());
        updateUser.ifPresent(selectUser -> { //! = null코딩줄임
            selectUser.setNickname(userDto.getNickname());
            selectUser.setPhone(userDto.getPhone());
            selectUser.setAdress(userDto.getAdress());
            selectUser.setAdressDetail(userDto.getAdressDetail());
            userRepository.save(selectUser);
        });

        return "회원수정완료" ;
    }
    @Transactional // 회원 수정 서비스
    public String userdel(Long id){
        userRepository.deleteById(id);
        return "회원삭제완료" ;
    }





}
