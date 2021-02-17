package br.multiplayer.accountapi.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.multiplayer.accountapi.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private LoginService loginService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
    
	public void WebSecurity( LoginService loginService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.loginService = loginService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	private static final String[] SWAGGER_WHITELIST = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**" };
	
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().ignoringAntMatchers("/h2/**").disable()
		.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers(SWAGGER_WHITELIST).permitAll()
		.antMatchers(HttpMethod.GET, "/api/usuario").permitAll()
		.antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
		.antMatchers(HttpMethod.POST, "/api/login").permitAll()
		.antMatchers("/h2/**").permitAll()
		.anyRequest().authenticated()
       	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().headers().frameOptions().sameOrigin();
//		.antMatchers("/").permitAll()
//		.and().csrf().ignoringAntMatchers("/h2/**")
//		.and().headers().frameOptions().sameOrigin();
    }
	
//	bean para configuração do CORS em nível de Spring Security.
	@Bean
  CorsConfigurationSource corsConfigurationSource() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
      return source;
  }
	
//	@Autowired
//  public void configAuthentication(AuthenticationManagerBuilder auth)
//      throws Exception {
//
//      auth.jdbcAuthentication().dataSource(dataSource)
//          .passwordEncoder(passwordEncoder())
//          .usersByUsernameQuery("{SQL}") //SQL query
//          .authoritiesByUsernameQuery("{SQL}"); //SQL query
//  }
	
}