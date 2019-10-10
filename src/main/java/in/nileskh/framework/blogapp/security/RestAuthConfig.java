package in.nileskh.framework.blogapp.security;

import in.nileskh.framework.blogapp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class RestAuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DataSource dataSource;

    private CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    public void initUserDetailsService() {
        customUserDetailsService = webApplicationContext.getBean(CustomUserDetailsService.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}user123").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}admin123").roles("ADMIN");

        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(getPasswordEncoder())
                .and()
                .authenticationProvider(getAuthenticationProvider())
                .jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/blog/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }
}
