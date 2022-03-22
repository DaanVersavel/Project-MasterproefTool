package com.example.MasterproofTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
}
