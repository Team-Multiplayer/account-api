package br.multiplayer.accountapi.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	
	@Autowired
    DataSource dataSource;
    
	public void WebSecurity( LoginService loginService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.loginService = loginService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	
	@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth)
        throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery("{SQL}") //SQL query
            .authoritiesByUsernameQuery("{SQL}"); //SQL query
    }
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
       http.cors().and().csrf().disable().authorizeRequests()
       .antMatchers("/").permitAll()
        .antMatchers("/h2/**").permitAll()
        .antMatchers(HttpMethod.POST,"/api/*").permitAll()
        .antMatchers(HttpMethod.POST,"/api/usuario/cadastrar").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.GET,"/api/usuario").permitAll()
        .antMatchers(HttpMethod.GET,"/master/*").permitAll()
        .anyRequest().authenticated()
        
        
        .and().csrf().ignoringAntMatchers("/h2/**")
        .and().headers().frameOptions().sameOrigin();
       
    }
	
//	bean para configuração do CORS em nível de Spring Security.
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
