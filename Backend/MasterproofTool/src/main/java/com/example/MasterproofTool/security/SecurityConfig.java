package com.example.MasterproofTool.security;

import com.example.MasterproofTool.security.filter.CustomAuthenticationFilter;
import com.example.MasterproofTool.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder BCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();

        //CustomAuthenticationFilter custumAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        //custumAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
        //http.authorizeRequests().antMatchers(GET,"/login").permitAll();
        //http.authorizeRequests().antMatchers(POST,"/login").permitAll();
        http.authorizeRequests().antMatchers("/login/**","/User/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/Subjects").hasAnyAuthority("ROLE_STUDENT");
        http.authorizeRequests().antMatchers("/User/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET,"/Subjects/Review").permitAll();
        http.authorizeRequests().antMatchers(POST,"/Subjects/Post").hasAnyAuthority("ROLE_STUDENT");
        http.authorizeRequests().anyRequest().authenticated();

        http.authorizeRequests().and().formLogin().loginPage("/login")/*.defaultSuccessUrl("/Subject/",true)*/;

        //http.addFilter(custumAuthenticationFilter);
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
