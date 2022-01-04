package com.example.smple1231.service;



import com.example.smple1231.membervo.HooMember;
import com.example.smple1231.membervo.RoleType;
import com.example.smple1231.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	
	@Autowired //DI
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; //암호화 인코더 가져오기
	
	@Transactional
	public void memberjoin(HooMember user) { // 회원 가입 서비스
		String rawPassword =user.getPassword();//사용자로부터 패스워드 받기(해쉬전)
		String encPassword = encoder.encode(rawPassword); //해쉬시킴
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

}
