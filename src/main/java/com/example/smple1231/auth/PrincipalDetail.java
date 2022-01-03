package com.example.smple1231.auth;

import com.example.smple1231.membervo.HooMember;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에 저장을 해준다(저장되는게 PrincipalDetail이게 저장됨)
@Getter //user 정보를 꺼내기 위해
public class PrincipalDetail implements UserDetails{ //UserDetails타입으로 변경 시큐리티 세션을 위해

	private HooMember user; //콤포지션

	
	public PrincipalDetail(HooMember user) {

		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
	//계정이 만료되지 않았는지 리턴한다 (true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 잠겨있지 않았는지 리턴한다 (true:만료안됨)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	//비밀번호가 만료되지 않았는지 리턴한다 (true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//계정활성화가 되어있는지 리턴한다 (true:활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	//계정의 갖고 있는 권한 목록을 리턴한다(권한이 여러개있을 수 있어서 루프를 돌아햐하는데 우리는 한개만 for문을 돌려야함)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { return
		 * "ROLE_"+user.getRole();//"ROLE_" 반드시넣어줘야함 -> ROLE_USER 리턴됨 } });
		 */
		
		collectors.add(()->{return "ROLE_"+user.getRole();}); //위에껄 람다식으로 
		
		
		return collectors;
	}
	
	
}
