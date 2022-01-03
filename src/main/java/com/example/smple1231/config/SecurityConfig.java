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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

//빈등록: 스프링 컨테이너에서 객체를 관리할 수 있게 한다
@Configuration //빈등록
@EnableWebSecurity // 시큐리티 필터 등록 ,설정을 여기서
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다 어노테이션 3개 셋트
public class SecurityConfig extends WebSecurityConfigurerAdapter{




	@Autowired
	private PrincipalDetailService principalDetailService; //username을 userDetail만들고 가져오는곳
	
	@Bean //ioc시킴
	public BCryptPasswordEncoder encodePWD() { //비밀번호 해쉬로 인코딩
		return new BCryptPasswordEncoder(); 
	}

	//시큐리티가 대신 로그인해주기 떄문에 password를 가로채기를 하는데
	//해당password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	//같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교할 수 있음 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD()); //이걸 안넣으면 페스워드 비교못함
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
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
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/");




	}


}
