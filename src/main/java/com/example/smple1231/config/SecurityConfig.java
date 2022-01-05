package com.example.smple1231.config;


import com.example.smple1231.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity // 시큐리티 필터 등록
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{ // 시큐리티 설정 Config

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder encodePWD() { //비밀번호 해쉬로 인코딩
		return new BCryptPasswordEncoder(); 
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // 패스워드 비교
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception { // 시큐리티 설정
		http
		.csrf().disable()// csrf 토큰 비활성화
		.authorizeRequests() //요청이 들어오면
			.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //"/auth/loginForm","/auth/joinForm" /auth/아래
			.permitAll()  //"/auth/loginForm","/auth/joinForm" /auth/아래로 들어오는건 누구나 조인가능
			.anyRequest() //이게 아닌 모든 요청은
			.authenticated() //인증이 되어야한다
		.and()
			.formLogin()
				.loginPage("/auth/loginForm") //인증이 없으면 자동으로 로그인폼으로
				.loginProcessingUrl("/auth/loginProc") //스프링시큐리티가 해당주소로 오는 로그인을 가로챔(대신로그인)
			.defaultSuccessUrl("/") //시큐리티가 로그인후 해당주소로 이동
		.and()
				.logout() // 로그아웃
				.logoutUrl("/logout")//로그아웃 주소
				.logoutSuccessUrl("/"); // 로그아웃 성공시 이동 주소
		http.sessionManagement()
				.maximumSessions(1) //세션 최대 허용 수
				.maxSessionsPreventsLogin(false); // false이면 중복 로그인하면 이전 로그인이 풀린다.




	}


}
