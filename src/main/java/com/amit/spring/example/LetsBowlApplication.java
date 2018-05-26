package com.amit.spring.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amit.spring.example.service.BowlingService;

@SpringBootApplication
public class LetsBowlApplication implements CommandLineRunner {

	@Autowired
	BowlingService bowlingService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(LetsBowlApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		int score = bowlingService.calculateScore();
		System.out.println("##########################");
		System.out.println("   Final Score is : " + score);
		System.out.println("##########################");
	}
}
