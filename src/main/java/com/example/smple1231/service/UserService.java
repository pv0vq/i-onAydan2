package com.example.smple1231.service;




import com.example.smple1231.dto.UserDto;
import com.example.smple1231.entity.HooAuthority;
import com.example.smple1231.entity.HooUser;
import com.example.smple1231.exception.DuplicateMemberException;
import com.example.smple1231.jwt.SecurityUtil;
import com.example.smple1231.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public UserDto signup(UserDto userDto) { //회원가입 서비스
		if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) { //유저정보 확인
			throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
		}

		HooAuthority authority = HooAuthority.builder() // 없으면 유저권한을 만듬
				.authorityName("ROLE_USER")
				.build();

		HooUser user = HooUser.builder() // 유정정보를 만들어서
				.username(userDto.getUsername())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.nickname(userDto.getNickname())
				.phone(userDto.getPhone())
				.adress(userDto.getAdress())
				.adressDetail(userDto.getAdressDetail())
				.authorities(Collections.singleton(authority))
				.activated(true)
				.build();

		return UserDto.from(userRepository.save(user)); // dto에 실어서 userRepository를 이용해서db에 저장
	}

	@Transactional(readOnly = true)
	public UserDto getMyUserWithAuthorities() { // SecurityContext에 저장된 username의 정보만 가져옴
		return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null));
	}


}
