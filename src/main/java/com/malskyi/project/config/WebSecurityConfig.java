package com.malskyi.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.malskyi.project.config.jwt.JWTTokenFilterConfigurer;
import com.malskyi.project.config.jwt.JWTTokenProvider;
import com.malskyi.project.entity.enums.Roles;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;	
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/**")
 		.hasRole("ADMIN").antMatchers("/**").anonymous();

		http.authorizeRequests().antMatchers("/auth/**").permitAll();
//		http.authorizeRequests().antMatchers("/auth/**").authenticated();
//		http.antMatcher("/auth/**").anonymous();//permitAll();
		
//		http.antMatcher("/commodity/**").anonymous();
//		
//		http.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_GLOBAL_ADMIN") ;
//		
		http.authorizeRequests().antMatchers("/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_GLOBAL_ADMIN", "ROLE_MODERATOR", "ROLE_SELLER") ;
//		http.authorizeRequests().antMatchers("/category/**").authenticated();
//			
//		http.authorizeRequests().antMatchers("/users/**").authenticated();

		http.antMatcher("/bla/**").anonymous();
		
		http.apply(new JWTTokenFilterConfigurer(jwtTokenProvider));
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		
//		return source;		
//	}
	
	
}



