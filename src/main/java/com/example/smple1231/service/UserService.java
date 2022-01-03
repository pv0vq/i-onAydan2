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
	public void memberjoin(HooMember user) {
		String rawPassword =user.getPassword();//사용자로부터 패스워드 받기(해쉬전)
		String encPassword = encoder.encode(rawPassword); //해쉬시킴
		user.setPassword(encPassword);// 해쉬값은 db에 저장
		user.setRole(RoleType.USER); //user 권한 을 db에 저장
		userRepository.save(user); //DB에 INSERT해준다
		
	}
	@Transactional
	public void memberre(HooMember user) {
		//수정시에는 jpa 영속성 컨텍스트에 BlogMember 오브젝트를 영속화 시키고 영속환된 BlogMember오브젝트 수정
		//SELET해서 BlogMember오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기위해
		//영속화된 오브젝트를 변경하면 @Transactional이 더티체킹UPDATE문 날려줌
		HooMember persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		String rawPassword =user.getPassword(); //사용자로부터 패스워드 받기
		String encPassword = encoder.encode(rawPassword);//해쉬시킴
		persistance.setPassword(encPassword);// 해쉬값은 db에 저장
		persistance.setEmail(user.getEmail());

	}

	

}
