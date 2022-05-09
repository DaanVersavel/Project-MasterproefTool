package com.example.MasterproofTool.subject;

import com.example.MasterproofTool.user.company.Company;
import com.example.MasterproofTool.user.company.CompanyRepository;
import com.example.MasterproofTool.user.company.CompanyService;
import com.example.MasterproofTool.user.coördinator.Coördinator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubjectConfig {

    @Bean
    CommandLineRunner commandLineRunner(SubjectRepository repository, CompanyRepository companyRepository, CompanyService companyService){
        return  args -> {
            companyService.saveNewCompany(new Company("Filip", "Desmet", "0456454", "filip@gmail.com", "alfons", 545154.545,-4523.5151, "djsfhldsh ","filip123"));

            Company company= companyRepository.findExistingCompanyByEmail("filip@gmail.com");
            Subject onderwerp1= new Subject("Automated hacking", "Detecting vulnerabilities in IoT devices",  "opmerking",1);
            Subject onderwerp2= new Subject("hacking", "Detecting vulnerabilities in IoT devices",  "opmerking",1,company);
            Subject onderwerp3= new Subject("Language Identification", " Word-level language identification for annotating street and place names",  "opmerking",1);
            Subject onderwerp4= new Subject("Cybersecurity", "a world in danger by cyberattacks",true,   "opmerking",1);
            Subject onderwerp5= new Subject("Acro Planning", "Planning gymnastic competiton",true,   "opmerking",1);
            Subject onderwerp6= new Subject("Hacking tool ", "Tool for hacking",true,   "opmerking",1);
            Subject onderwerp7= new Subject("nvidia GPU", "light calculation in nvidia GPU",true,   "opmerking",1);

//            Coördinator coordinator1=new Coördinator("Annemie","Vorstermans");
            repository.saveAll(List.of(onderwerp1,onderwerp2,onderwerp3,onderwerp4,onderwerp5, onderwerp6,onderwerp7));
        };
    }
}
