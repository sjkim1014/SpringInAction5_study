package tacos.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	DataSource dataSource;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/design", "/orders")
				.access("hasRole('ROLE_USER')")
	//			.antMatchers("/", "/**").permitAll();
				.antMatchers("/", "/**").access("permitAll")
			.and()
				.formLogin()
				.loginPage("/login")
//			.defaultSuccessUrl("/design", true)
//			.loginProcessingUrl("/authenticate")
//			.usernameParameter("user")
//			.passwordParameter("pwd")
			.and()
				.logout()
				.logoutSuccessUrl("/")
			.and()
				.csrf()
//			.disable() 
			;
		
		
		// 화요일의 타코생성은 ROLE_USER 권한을 갖는 사용자에게만 허용
//		http.authorizeRequests()
//			.antMatchers("/design", "/orders")
//				.access("hasRole('ROLE_USER') && " +
//				"T(java.util.Calendar).getInstance().get("+
//				"T(java.util.Calendar).DAY_OF_WEEK) == " + 
//				"T(java.util.Calendar).TUESDAY")
//			.antMatchers("/", "/**").access("permitAll");
		
		
		
		/*
		http.authorizeRequests()
				.antMatchers("/design", "/orders")
				.access("hasRole('ROLE_USER')")
				.antMatchers("/", "/**").access("permitAll")
				.and()
				.httpBasic()
				;
		 */
	}
	
//	@Autowired
//	DataSource dataSource;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
		
		/*
		auth.ldapAuthentication()
			.userSearchBase("ou=people")
			.userSearchFilter("(uid={0})")
			.groupSearchBase("ou=groups")
			.groupSearchFilter("member={0}")
			.contextSource()
			.root("dc=tacocloud,dc=com")
			.ldif("classpath:users.ldif")
			.and()
			.passwordCompare()
			.passwordEncoder(new BCryptPasswordEncoder())
			.passwordAttribute("userPasscode");		
		*/	
			
		/*
		auth.ldapAuthentication()
			.userSearchBase("ou=people")
			.userSearchFilter("(uid={0})")
			.groupSearchBase("ou=groups")
			.groupSearchFilter("member={0}")
			.passwordCompare()
			.passwordEncoder(new BCryptPasswordEncoder())
			.passwordAttribute("userPasscode")
			;
			
		*/
			
			
		/*
		auth.inMemoryAuthentication()
				.withUser("user1")
				.password("{noop}password1")
				.authorities("ROLE_USER")
				.and()
				.withUser("user2")
				.password("{noop}password2")
				.authorities("ROLE_USER");
		*/
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(
					"select username, password, enabled from users " +
					"where username=?")
			.authoritiesByUsernameQuery(
					"select username, authority from authorities " +
					"where username=?")
			.passwordEncoder(new NoEncodingPasswordEncoder());
	}

}
