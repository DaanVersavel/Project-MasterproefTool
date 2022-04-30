package com.example.MasterproofTool.subject;

import com.example.MasterproofTool.user.Coördinator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubjectConfig {

    @Bean
    CommandLineRunner commandLineRunner(SubjectRepository repository){
        return  args -> {
            Subject onderwerp1= new Subject("Automated hacking", "Detecting vulnerabilities in IoT devices",  "opmerking",1);
            Subject onderwerp2= new Subject("Language Identification", " Word-level language identification for annotating street and place names",  "opmerking",1);
            Subject onderwerp3= new Subject("Cybersecurity", "a world in danger by cyberattacks",true,   "opmerking",1);
            repository.saveAll(List.of(onderwerp1,onderwerp2,onderwerp3));
        };
    }
}
