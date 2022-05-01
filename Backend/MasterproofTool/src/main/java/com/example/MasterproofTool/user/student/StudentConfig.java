package com.example.MasterproofTool.user.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner studentRunner(StudentRepository studentRepository){
        return args -> {
            Student s= new Student("Gert","de man","04165","gert@gmail.com","r0489455");
            studentRepository.saveAll(List.of(s));
        };

    }

}
