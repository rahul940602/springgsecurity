package com.basic.security.secuityCode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import static org.springframework.security.config.Customizer.withDefaults;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity  //for Role based authentication
public class SecurityConfig {
	

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated());
//		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		// h2 databse
		http.headers(headers ->
		             headers.frameOptions(frameOptions
		            		   -> frameOptions.sameOrigin()));
		http.csrf(csrf -> csrf.disable());
		//the above for h2 databse
		
		return http.build();
	}

    @Bean
    UserDetailsService userDetailsService() {
		
    	UserDetails user1 = User.withUsername("user1")
    			            .password("{noop}user1")
    			            .roles("USER")
    			            .build();
    	
    	UserDetails admin = User.withUsername("admin")
	            .password("{noop}admin")
	            .roles("ADMIN")
	            .build();
		return new InMemoryUserDetailsManager(user1, admin);
		
	}

}
