package com.example.MasterproofTool.subject;


import com.example.MasterproofTool.user.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;

@Configuration
public class SubjectConfig {

    @Bean
    CommandLineRunner commandLineRunner(SubjectRepository repository){
        return  args -> {
            Subject onderwerp1= new Subject("titelken", " Beschrijving",  "opmerking",1);
            repository.saveAll(List.of(onderwerp1));
        };
    }
}
