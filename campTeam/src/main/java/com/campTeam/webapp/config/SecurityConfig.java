package com.campTeam.webapp.config;


import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.campTeam.webapp.service.CustomProvider;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

	@Autowired
	private CustomProvider customProvider;	
	
	private UserDetailsService userDetailsService;

	private DataSource dataSource;

	public SecurityConfig(UserDetailsService userDetailsService, DataSource dataSource) {

		log.info("생성자 주입");
		this.dataSource = dataSource;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    // security 적용 예외 URL 등록
	// bootstrap-icons/** 추가
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	
    	// 게시판 : summernote 추가
    	// swagger 항목 예외(열외) 추가 :
    	// 참고) /v3/api-docs : swagger의 전체적인 환경설정 정보를 JSON 형식으로 보여주는 페이지
    	// 환경 설정 정보 : /v3/api-docs/swagger-config
    	// /v3/api-docs, /swagger-resources/**, /swagger/**, /swagger-ui/INDEX.html
    	// axios 항목 예외 추가
    	return (web) -> web.ignoring().requestMatchers("/img/**", "/css/**", "/webjars/**",
    				"/images/**", "/js/**", "/axios/**",
    				"/swagger-resources/**", "/swagger/**", "/swagger-ui/**", "/v3/api-docs/**",
    				"/axios/**", "/bootstrap-icons/**", "/bootstrap/**", "/webjars/jquery/**",
    				"/summernote/**");   	
    }

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf((csrf)->csrf.disable()); // csrf 토큰 미사용
    	
        http.userDetailsService(userDetailsService);
            
        http.authenticationProvider(customProvider);
            
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions
									   .sameOrigin()
									)
								);
					        
        http.authorizeHttpRequests((authorizeHttpRequests) ->  
										authorizeHttpRequests
										// axios 추가
						                // security 적용 예외 URL 등록와의 중복 부분 제외 => "/"만 적용
						                // .requestMatchers("/", "/css/**", "/webjars/**", "/images/**", "/js/**", "/axios/**", "/bootstrap-icons/**").permitAll()
						                .requestMatchers("/","/webjars/jquery/**","/map","/img/**","/mapSearch").permitAll() 
						                // .requestMatchers("/swagger-resources/**", "/swagger/**", "/swagger-ui/index.html").permitAll()
						                .requestMatchers("/mapRest", "/mapRestOne", "/member/hasFld/**").permitAll()
						                .requestMatchers("/member/view.do", "/member/hasFldForUpdate/**").authenticated()
						                .requestMatchers("/member/update.do", "/member/updateProc.do").authenticated()
						                .requestMatchers("/member/updateSess.do", "/member/updateSessProc.do").authenticated()
						                .requestMatchers("/member/join.do", "/member/joinProc.do", "/member/joinProcRest.do").permitAll()
						                .requestMatchers("/member/updateRoles/**", "/member/changeMemberState/**", 
						                			     "/member/updateMemberByAdmin/**", "/member/deleteMemberByAdmin/**").authenticated()
						                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
						                .requestMatchers("/content1", "/content2", "/about.do").permitAll()
						                
						                // 공지사항 관련 링크 추가
						                 .requestMatchers("/searchlist.do",
						                		 		  "/notice/image", "/notice/image/**", "/notice/list.do", 
						                		 		  "/notice/view.do/**", "/notice/searchList.do").permitAll()
						                
						                .requestMatchers("/notice/write.do","/notice/writeProc.do",
						                				 "/notice/update.do", "/notice/updateProc.do",
							                			 "/notice/replyWrite.do", "/notice/replyUpdate.do", 
							                			 "/notice/getRepliesAll.do", "/notice/replyDelete.do",
							                			 "/notice/deleteProc.do").authenticated()
						                
						                //
						                // 게시판 관련 링크 추가
						                .requestMatchers("/board/write.do","/board/writeProc.do",
						                				 "/board/image", "/board/image/**", "/board/list.do", 
							                			 "/board/view.do", "/board/searchList.do",
							                			 "/board/update.do", "/board/updateProc.do",
							                			 "/board/replyWrite.do", "/board/replyUpdate.do", 
							                			 "/board/getRepliesAll.do", "/board/replyDelete.do",
							                			 "/board/deleteProc.do", "/board/mypage.do").authenticated()
						                .anyRequest().authenticated()); 
                
           http.formLogin(formLogin -> formLogin
				                .loginPage("/login")
				                .usernameParameter("username")
				    			.passwordParameter("password")
				    			.defaultSuccessUrl("/welcome")                
				                .failureUrl("/loginError")
				                //.successHandler(new CustomAuthenticationSuccess()) // 로그인 성공 핸들러 
				                //.failureHandler(new CustomAuthenticationFailure()) // 로그인 실패 핸들러 
		                		.permitAll());
           		
          http.logout((logout) -> logout.permitAll());
                
          http.exceptionHandling(handler -> handler.accessDeniedPage("/403")); 	 
//            .and()
//                .logout()
//                    .logoutSuccessUrl("/")
                    
//            .and()
//                .oauth2Login()
//                    .userInfoEndpoint()
//                        .userService(customOAuth2UserService);
          
          
          http.rememberMe((remember) -> remember
					.key("campTeam")
					.userDetailsService(userDetailsService)
					.tokenRepository(getJDBCRepository())
					.tokenValiditySeconds(60 * 60 * 24)); // 24시간(1일)
    	
    	return http.build();
    } //
    

	// 추가된 remember-me 관련 메서드
	private PersistentTokenRepository getJDBCRepository() {

		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);

		return repo;
	} //
	
}