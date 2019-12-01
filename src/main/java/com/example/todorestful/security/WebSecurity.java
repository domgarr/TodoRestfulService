package com.example.todorestful.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.todorestful.dao.MyUserDetailsService;
//Example followed
//https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/#Securing-RESTful-APIs-with-JWTs

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private MyUserDetailsService userDetailService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(MyUserDetailsService userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailService = userDetailService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(authenticationManager());
		
		//Add cors, since the angular app is on a different port and disable CSRF because we aren't using sessions.
		http.cors().and().csrf().disable().authorizeRequests()
		//The signup url will be public.
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.GET, "/users/user").permitAll()		
		.antMatchers(HttpMethod.GET, "/").permitAll() 		//Permit the index file?		
		.antMatchers(HttpMethod.GET,"/*.*.js").permitAll()
		.antMatchers(HttpMethod.GET,"/*.*.css").permitAll()
		.antMatchers(HttpMethod.GET,"/*.ico").permitAll()
		.antMatchers(HttpMethod.GET,"/*.html").permitAll()
		//Everythign else will be secured.
		.anyRequest().authenticated()
		.and()
		.addFilter(authenticationFilter)
		.addFilter(new JwtAuthorizationFilter(authenticationManager()))
		//Disable session creation.
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().accessDeniedPage("/index.html"); //If access is denied forward the user to the root directory which will either navigate the user to home or to login. 
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Load user specific information into the security framework.
		auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Value("${user-defined.allowed-origin}")
	private String allowedOrgin;
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(allowedOrgin));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTION", "DELETE", "PUT"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("Authorization"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}	
}
