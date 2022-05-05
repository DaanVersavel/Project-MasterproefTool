package com.example.MasterproofTool;

import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectRepository;
import com.example.MasterproofTool.user.appUser.Appuser;
import com.example.MasterproofTool.user.appUser.UserService;
import com.example.MasterproofTool.user.campus.Campus;
import com.example.MasterproofTool.user.campus.CampusRepository;
import com.example.MasterproofTool.user.company.Company;
import com.example.MasterproofTool.user.company.CompanyService;
import com.example.MasterproofTool.user.coördinator.Coördinator;
import com.example.MasterproofTool.user.coördinator.CoördinatorRepository;
import com.example.MasterproofTool.user.disciplines.Discipline;
import com.example.MasterproofTool.user.disciplines.DisciplineRepository;
import com.example.MasterproofTool.user.promotor.Promotor;
import com.example.MasterproofTool.user.promotor.PromotorRepository;
import com.example.MasterproofTool.user.role.Role;
import com.example.MasterproofTool.user.student.Student;
import com.example.MasterproofTool.user.student.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@SpringBootApplication
@Configuration
@ComponentScan({"com.example.MasterproofTool.security"
		,"com.example.MasterproofTool.user"
		,"com.example.MasterproofTool.subject"
		,"com.example.MasterproofTool.user.appUser"})

public class MasterproefToolApplication {
	//roles
	public static final String ROLE_ADMIN="ROLE_ADMIN";
	public static final String ROLE_COÖRDINATOR="ROLE_COÖRDINATOR";
	public static final String ROLE_PROMOTOR="ROLE_PROMOTOR";
	public static final String ROLE_STUDENT="ROLE_STUDENT";
	public static final String ROLE_COMPANY="ROLE_COMPANY";

	public static void main(String[] args) {
		SpringApplication.run(MasterproefToolApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run1(UserService userService, StudentService studentService, CompanyService companyService){
		return args -> {
			//UserService
			userService.saveRole(new Role(null,ROLE_ADMIN));
			userService.saveRole(new Role(null,ROLE_COÖRDINATOR));
			userService.saveRole(new Role(null,ROLE_PROMOTOR));
			userService.saveRole(new Role(null,ROLE_STUDENT));
			userService.saveRole(new Role(null,ROLE_COMPANY));

			userService.saveAppuser(new Appuser("Bob", "Smith", "0489005054","bob.smith@gmail.com","bob123"));
			userService.saveAppuser(new Appuser("Bart", "Smith", "0489005054","bart.smith@gmail.com","bart123"));

			userService.addRoleToAppuser("bob.smith@gmail.com",ROLE_ADMIN);
			userService.addRoleToAppuser("bart.smith@gmail.com",ROLE_STUDENT);

			//student service
			studentService.saveStudent(new Student("lotte", "Vanlanduyt", "084655465", "lotte@gmail.com","R045645" ,"lotte123"));

			userService.addRoleToAppuser("lotte@gmail.com",ROLE_STUDENT);


			//Company service
			//ecompanyService.saveNewCompany(new Company("Filip", "Desmet", "0456454", "filip@gmail.com", "alfons", 545154.545,-4523.5151, "djsfhldsh ","filip123"));
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
			campusRepository.saveAll(List.of(campus1,campus2, campus3, campus4, campus5, campus6, campus7, campus8, campus9, campus10, campus11, campus12, campus13));

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

			Campus c=campusRepository.findByName("Leuven");
			dis7.addCampus(c);
			dis8.addCampus(c);

			Subject sub= subjectRepository.findSubjectById(5);
			dis5.getSubjects().add(sub);

			disciplineRepository.saveAll(List.of(dis1, dis2, dis3, dis4, dis5, dis6, dis7, dis8));
			campusRepository.saveAll(List.of(campus1,campus2, campus3, campus4, campus5, campus6, campus7, campus8, campus9, campus10, campus11, campus12, campus13));
		};
	}

	@Bean
	CommandLineRunner run3(CoördinatorRepository coordinatorRepository, DisciplineRepository disciplineRepository,
						   CampusRepository campusRepository, PromotorRepository promotorRepository){

		return args -> {

			Discipline elict= disciplineRepository.findByNaam("Master in de industriële wetenschappen: elektronica-ICT");
			Campus gent= campusRepository.findByName("Technologiecampus Gent");
			coordinatorRepository.save(new Coördinator( "Dirk", "Allo", "0484135424", "dirk@gmail.com", elict,  gent,"dirk123"));
			promotorRepository.save( new Promotor("James", "Cooke", "4054654","james@gmail.com", gent, elict,"james123"));



		};
	}

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods(GET, POST, PUT, DELETE)
						.allowedHeaders("*")
						.allowedOriginPatterns("*")
						.allowCredentials(true);
			}
		};
	}
}
