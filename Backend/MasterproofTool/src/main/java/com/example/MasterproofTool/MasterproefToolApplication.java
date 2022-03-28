package com.example.MasterproofTool;

import com.example.MasterproofTool.user.Appuser;
import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.appUser.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


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

	//@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_COÖRDINATOR"));
			userService.saveRole(new Role(null,"ROLE_PROMOTOR"));
			userService.saveRole(new Role(null,"ROLE_STUDENT"));
			userService.saveRole(new Role(null,"ROLE_COMPANY"));

			userService.saveAppuser(new Appuser("Bob", "Smith", null, "0489005054","bob.smith@gmail.com","bob123"));

			userService.addRoleToAppuser("bob.smith@gmail.com","ROLE_ADMIN");


		};


	}
}
