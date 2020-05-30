package com.dhl.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests()
				.antMatchers("/", "/css/**", "/fonts/**", "/img/**", "/js/**", "/less/**", "/favicon**", "/webjars/**",
						"/assets/**", "/fragments/**", "/fontawesome-5.13.0/**", "/bootstrap-4.1.3/**", "/login",
						"/register")
				.permitAll().
				// all other requests need to be authenticated
				anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/bookingQueue", true).failureUrl("/login?error").and().logout()
				.invalidateHttpSession(true).clearAuthentication(true).logoutSuccessUrl("/login?logout");
//                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
//				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//		SESSION RELATED
		httpSecurity.sessionManagement().maximumSessions(2).expiredUrl("/").and().invalidSessionUrl("/")
				.sessionFixation().migrateSession();

		// Add a filter to validate the tokens with every request
//		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(jwtUserDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

}
