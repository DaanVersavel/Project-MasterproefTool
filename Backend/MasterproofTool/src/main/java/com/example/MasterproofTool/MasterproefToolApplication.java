package com.example.MasterproofTool;

import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectRepository;
import com.example.MasterproofTool.user.Appuser;
import com.example.MasterproofTool.user.Campus;
import com.example.MasterproofTool.user.Discipline;
import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.appUser.UserService;
import com.example.MasterproofTool.user.campus.CampusRepository;
import com.example.MasterproofTool.user.disciplines.DisciplineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootApplication
@Configuration
@ComponentScan({"com.example.MasterproofTool.security"
		,"com.example.MasterproofTool.user"
		,"com.example.MasterproofTool.subject"
		,"com.example.MasterproofTool.user.appUser"})

public class MasterproefToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterproefToolApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/Subjects").allowedOrigins("*");
				registry.addMapping("/Subjects/Post").allowedOrigins("*");
				registry.addMapping("/Subjects/Review").allowedOrigins("*");
				registry.addMapping("/Subjects/MySubjects").allowedOrigins("*");
			}
		};
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run1(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_COÖRDINATOR"));
			userService.saveRole(new Role(null,"ROLE_PROMOTOR"));
			userService.saveRole(new Role(null,"ROLE_STUDENT"));
			userService.saveRole(new Role(null,"ROLE_COMPANY"));

			userService.saveAppuser(new Appuser("Bob", "Smith", "0489005054","bob.smith@gmail.com","bob123"));
			userService.saveAppuser(new Appuser("Bart", "Smith", "0489005054","bart.smith@gmail.com","bart123"));

			userService.addRoleToAppuser("bob.smith@gmail.com","ROLE_ADMIN");
			userService.addRoleToAppuser("bart.smith@gmail.com","ROLE_STUDENT");


		};
	}
	@Bean
	CommandLineRunner run2(DisciplineRepository disciplineRepository, CampusRepository campusRepository, SubjectRepository subjectRepository){
		return args -> {
			//Disciplines
			//gent
			Discipline dis1 = new Discipline("Master in de industriële wetenschappen: biochemie");
			Discipline dis2 = new Discipline("Master in de industriële wetenschappen: bouwkunde");
			Discipline dis3 = new Discipline("Master in de industriële wetenschappen: chemie");
			Discipline dis4 = new Discipline("Master in de industriële wetenschappen: elektromechanica");
			Discipline dis5 = new Discipline("Master in de industriële wetenschappen: elektronica-ICT");
			Discipline dis6 = new Discipline("Master in de industriële wetenschappen: energie");
			//campus leuven
			Discipline dis7 = new Discipline("Master of Biology");
			Discipline dis8 = new Discipline("Master of Chemistry");
			disciplineRepository.saveAll(List.of(dis1, dis2, dis3, dis4, dis5, dis6, dis7, dis8));

			//Campussen
			Campus campus1 = new Campus("Leuven", 50.8798380208875, 4.700039868622543);
			Campus campus2 = new Campus("Groep T Leuven", 50.875368396715565, 4.707853500898213);
			Campus campus3 = new Campus("Brussel", 50.84966513795105, 4.356227224171277);
			Campus campus4 = new Campus("Sint-Lucas Brussel", 50.86258512552157, 4.367840446916494);
			Campus campus5 = new Campus("Antwerpen", 51.22064156447371, 4.407772490407685);
			Campus campus6 = new Campus("Geel", 51.1606042989086, 4.961861377236998);
			Campus campus7 = new Campus("De Nayer Sint-Katelijne-Waver", 51.06905645869098, 4.49833527088901);
			Campus campus8 = new Campus("Sint-Lucas Gent", 51.055526458684, 3.71292830895948);
			Campus campus9 = new Campus("Technologiecampus Gent", 51.0605561950515, 3.7085667396470816);
			Campus campus10 = new Campus("Aalst", 50.93291338340127, 4.023351053250854);
			Campus campus11 = new Campus("Kulak Kortrijk", 50.80650883614343, 3.2927428929074503);
			Campus campus12 = new Campus("Brugge", 51.19521867012457, 3.2178691243900936);
			Campus campus13 = new Campus("Diepenbeek", 50.92880854884159, 5.395334413327267);

			//Linking of disciplines to campus
//			campus9.addDisciplines(dis1);
//			campus9.addDisciplines(dis2);
//			campus9.addDisciplines(dis3);
//			campus9.addDisciplines(dis4);
//			campus9.addDisciplines(dis5);
//			campus9.addDisciplines(dis6);
//
//			campus1.addDisciplines(dis7);
//			campus1.addDisciplines(dis8);

			//Linking of campus to disciplines
			dis1.addCampus(campus9);
			dis2.addCampus(campus9);
			dis3.addCampus(campus9);
			dis4.addCampus(campus9);
			dis5.addCampus(campus9);
			dis6.addCampus(campus9);

			dis7.addCampus(campus1);
			dis8.addCampus(campus1);

			Subject sub= subjectRepository.findSubjectById(15);
			dis5.getSubjects().add(sub);


			disciplineRepository.saveAll(List.of(dis1, dis2, dis3, dis4, dis5, dis6, dis7, dis8));
			campusRepository.saveAll(List.of(campus1, campus2, campus3, campus4, campus5, campus6, campus7, campus8, campus9, campus10, campus11, campus12, campus13));
		};
	}
}
