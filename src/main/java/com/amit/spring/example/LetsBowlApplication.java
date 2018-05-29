package com.amit.spring.example;

/**
 * @author AmitPawar
 */

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

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(LetsBowlApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		int score = bowlingService.letsCalculateBowlingScore();
		System.out.println("##########################");
		System.out.println("   Final Score is : " + score);
		System.out.println("##########################");
	}
}
