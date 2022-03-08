package com.example.MasterproofTool.subject;


import com.example.MasterproofTool.user.Company;
import com.example.MasterproofTool.user.Coördinator;
import com.example.MasterproofTool.user.Promotor;
import com.example.MasterproofTool.user.Student;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubjectConfig {

    @Bean
    CommandLineRunner commandLineRunner(SubjectRepository repository){
        return  args -> {
            Subject onderwerp1= new Subject("titelken", " Beschrijving", "een discipline", "opmerking",
                    /*Company company, Coördinator coordinator, Promotor promotor, Student boostedStudent,*/ 1, new String[]{"campus"}, new String[]{"Opleiding"});

            repository.saveAll(List.of(onderwerp1));
        };
    }

}
