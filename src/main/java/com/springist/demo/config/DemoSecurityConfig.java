package com.springist.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springist.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
    @Autowired
    private UserService userService;
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
//		.csrf().disable()
//	    .exceptionHandling()
//	    .authenticationEntryPoint(restAuthenticationEntryPoint)
//	    .and()
	    .authorizeRequests()
			.antMatchers("/").permitAll()
//			.antMatchers("/api").permitAll()
			.antMatchers("/links").permitAll()
			.antMatchers("/", "/error", "/webjars/**","/js/**").permitAll()
//			.antMatchers("/api/greetings").permitAll()
//			.antMatchers("/api/greetings/**").permitAll()
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.antMatchers("/register/**").permitAll()
			.antMatchers("/**").hasRole("EMPLOYEE")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
//			.oauth2Login()
//			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied")
			.and()
					.httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint); //rest api erisimi icin ekledim
	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	  
}






