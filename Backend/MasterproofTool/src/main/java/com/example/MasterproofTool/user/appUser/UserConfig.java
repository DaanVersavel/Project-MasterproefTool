package com.example.MasterproofTool.user.appUser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner1(UserRepository repository){

        return args ->{
            Appuser user1 = new Appuser("Bob","De Bouwer","018852","bob.debouwer@gmail.com");

            repository.saveAll(List.of(user1));
        };
    }
}
