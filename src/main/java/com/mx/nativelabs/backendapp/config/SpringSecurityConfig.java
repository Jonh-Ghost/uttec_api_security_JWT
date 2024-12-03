package com.mx.nativelabs.backendapp.config;


import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mx.nativelabs.backendapp.config.utils.JwtAuthenticationEntryPoint;
import com.mx.nativelabs.backendapp.config.utils.JwtRequestFilter;
import com.mx.nativelabs.backendapp.service.AdministradorService;
import com.mx.nativelabs.backendapp.service.OwnerService;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final static Logger logger = Logger.getLogger(SpringSecurityConfig.class); 
	
	@Autowired 
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private AdministradorService administradorService;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/**
	 * Crea el bean authenticationManager
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		logger.info("Authenticando: ");
		return super.authenticationManager();
	}
	
	/**
	 * Crea el bean bCryptPasswordEncoder 
	 * @return
	 */
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		logger.info("bean: ");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PasswordEncoder pbkdf2PasswordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}
	@Bean
	public PasswordEncoder sCryptPasswordEncoder() {
		
		return new StandardPasswordEncoder();
	}
	/**
	 * Referencias de UserDetailsService en los servicios para realizar la consulta de Autenticacion
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(ownerService).passwordEncoder(bCryptPasswordEncoder());
		//auth.userDetailsService(proveedorService).passwordEncoder(pbkdf2PasswordEncoder());
		
		auth.userDetailsService(administradorService).passwordEncoder(sCryptPasswordEncoder());
		logger.info("creando config global : "+ auth.toString());
	}
	/**
	 * Configuracion y mapeo de URL con los filtros de JWT y Spring Security
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors();
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers("/api/v1/sesion/admin").permitAll()
		.antMatchers("/api/v1/sesion/owner").permitAll()
		.antMatchers("/api/v1/owners").permitAll()
		.antMatchers(HttpHeaders.ALLOW).permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		logger.info("informacion: "+ jwtAuthenticationEntryPoint.toString()+" _  "+ jwtRequestFilter.toString()+ "  : ");
		
		
		//httpSecurity.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		//the below three lines will add the relevant CORS response headers
		configuration.addAllowedOriginPattern("*");
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers","Access-Control-Allow-Origin",
				"Access-Control-Request-Method", 
				"Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"));
		configuration.addAllowedMethod("*");
		configuration.setExposedHeaders(Arrays.asList("Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
			"/configuration/ui",
			"/swager-resources/**",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**"
		);
    }
	
}
