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

import static com.example.MasterproofTool.MasterproefToolApplication.*;
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
//        http.authorizeRequests().antMatchers("/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers("/**").permitAll();
        //Subjects Contoller
        http.authorizeRequests().antMatchers("/Subjects/Review/Approved/{id}").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);
        http.authorizeRequests().antMatchers("/Subjects/Review/Denied/{id}").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);

        http.authorizeRequests().antMatchers("/Subjects/Denied").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);


        http.authorizeRequests().antMatchers("/Subjects/{id}").hasAnyAuthority(ROLE_ADMIN,ROLE_STUDENT,ROLE_PROMOTOR,ROLE_COÖRDINATOR,ROLE_COMPANY);
        http.authorizeRequests().antMatchers("/Subjects/Post").hasAnyAuthority(ROLE_ADMIN,ROLE_STUDENT,ROLE_PROMOTOR,ROLE_COÖRDINATOR,ROLE_COMPANY);

        http.authorizeRequests().antMatchers("/Subjects").hasAnyAuthority(ROLE_ADMIN,ROLE_STUDENT,ROLE_PROMOTOR,ROLE_COÖRDINATOR);


        //User Controller
        http.authorizeRequests().antMatchers("/User/users/save").hasAnyAuthority(ROLE_ADMIN,ROLE_PROMOTOR,ROLE_COÖRDINATOR,"ROLE_LOGIN");
        http.authorizeRequests().antMatchers("/User/users").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);
        http.authorizeRequests().antMatchers("/User/role/addtouser").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);
        http.authorizeRequests().antMatchers("/User/role/save").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);
//      http.authorizeRequests().antMatchers("/User/users").hasAnyAuthority(ROLE_ADMIN,ROLE_PROMOTOR,ROLE_COÖRDINATOR);
//      http.authorizeRequests().antMatchers("/User/token/refresh").hasAnyAuthority(ROLE_ADMIN,ROLE_STUDENT,ROLE_PROMOTOR,ROLE_COÖRDINATOR);
        http.authorizeRequests().antMatchers("/User/delete/{id}").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);
        http.authorizeRequests().antMatchers("/User/whoami").hasAnyAuthority(ROLE_ADMIN,ROLE_STUDENT,ROLE_PROMOTOR,ROLE_COÖRDINATOR,ROLE_COMPANY);

        //Campus controller
        http.authorizeRequests().antMatchers("/Campus").hasAnyAuthority("ROLE_ADMIN","ROLE_COÖRDINATOR", "ROLE_LOGIN");
        //CompanyController
        http.authorizeRequests().antMatchers("/Company/list").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);
//        http.authorizeRequests().antMatchers("/Company").hasAnyAuthority(ROLE_COMPANY);


        http.authorizeRequests().antMatchers("/Company/Save").hasAnyAuthority("ROLE_LOGIN","ROLE_ADMIN");
        //Discipline
        http.authorizeRequests().antMatchers("/Discipline").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR,"ROLE_LOGIN");

        //Promotor
        http.authorizeRequests().antMatchers("/Promotor/MySubjects/Students/{id}").hasAnyAuthority(ROLE_PROMOTOR);
        http.authorizeRequests().antMatchers("/Promotor/MySubjects/Boost/{subjectid}/{studentid}").hasAnyAuthority(ROLE_PROMOTOR);
        http.authorizeRequests().antMatchers("/Promotor/MySubjects").hasAnyAuthority(ROLE_PROMOTOR);
        http.authorizeRequests().antMatchers("/Promotor/Save").hasAnyAuthority(ROLE_PROMOTOR,"ROLE_LOGIN");

        //coordinator
        http.authorizeRequests().antMatchers("/Coordinator/Review").hasAnyAuthority(ROLE_ADMIN,ROLE_COÖRDINATOR);
        http.authorizeRequests().antMatchers("/Coordinator/Save").hasAnyAuthority("ROLE_ADMIN","ROLE_LOGIN");
        http.authorizeRequests().antMatchers("/Coordinator/AssignedSubjects").hasAnyAuthority("ROLE_ADMIN","ROLE_COÖRDINATOR");
        http.authorizeRequests().antMatchers("/Coordinator/NotAssignedSubjects").hasAnyAuthority("ROLE_ADMIN","ROLE_COÖRDINATOR");
        http.authorizeRequests().antMatchers("/Coordinator/AutoAssignBoosted").hasAnyAuthority("ROLE_ADMIN","ROLE_COÖRDINATOR");
        http.authorizeRequests().antMatchers("/Coordinator/AutoAssignFirstChoise").hasAnyAuthority("ROLE_ADMIN","ROLE_COÖRDINATOR");


        //Student
        http.authorizeRequests().antMatchers("/Student/Starred/firstChoise/{id}").hasAnyAuthority(ROLE_STUDENT);
        http.authorizeRequests().antMatchers("/Student/Starred/SecondChoise/{id}").hasAnyAuthority(ROLE_STUDENT);
        http.authorizeRequests().antMatchers("/Student/Starred/ThirdChoise/{id}").hasAnyAuthority(ROLE_STUDENT);

        http.authorizeRequests().antMatchers("/Student/GetFirstChoise").hasAnyAuthority(ROLE_STUDENT);
        http.authorizeRequests().antMatchers("/Student/GetSecondChoice").hasAnyAuthority(ROLE_STUDENT);
        http.authorizeRequests().antMatchers("/Student/GetThirdChoice").hasAnyAuthority(ROLE_STUDENT);

        http.authorizeRequests().antMatchers("/Student/Starred").hasAnyAuthority(ROLE_STUDENT);
        http.authorizeRequests().antMatchers("/Student/StarredSave/{id}").hasAnyAuthority(ROLE_STUDENT);
        http.authorizeRequests().antMatchers("/Student/StarredRemove/{id}").hasAnyAuthority(ROLE_STUDENT);



        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
        //http.authorizeRequests().antMatchers(GET,"/login").permitAll();
        //http.authorizeRequests().antMatchers(POST,"/login").permitAll();
//        http.authorizeRequests().antMatchers("/login/**","/User/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers(GET,"/Subjects").hasAnyAuthority("ROLE_STUDENT");
//        http.authorizeRequests().antMatchers("/User/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(GET,"/Subjects/Review").permitAll();
//        http.authorizeRequests().antMatchers(POST,"/Subjects/Post").hasAnyAuthority("ROLE_STUDENT");
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
