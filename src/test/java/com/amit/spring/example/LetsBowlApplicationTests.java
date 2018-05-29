package com.amit.spring.example;

/**
 * @author AmitPawar
 */

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amit.spring.example.service.BowlingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetsBowlApplicationTests {

	BowlingService bowlingService = new BowlingService();

	@Test
	public void contextLoads() {
	}

	@Test
	public void checkBowlingScoreReturnType() {
		int score = bowlingService.letsCalculateBowlingScore();
		assertTrue(Integer.MIN_VALUE <= score && score <= Integer.MAX_VALUE);
	}

}
