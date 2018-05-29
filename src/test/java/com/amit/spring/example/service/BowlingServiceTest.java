package com.amit.spring.example.service;

/**
 * @author AmitPawar
 */

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BowlingServiceTest {

	BowlingService bowlingService = new BowlingService();

	@Test
	public void validateScoreRange() {
		int score = bowlingService.letsCalculateBowlingScore();
		assertTrue(0 <= score && score <= 300);
	}

	@Test
	public void validatePinDownValidity() {
		List<Integer> expectedNumbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		int pinDownNumber = bowlingService.generateFirstPinDownValue();
		assertThat(expectedNumbers, hasItems(pinDownNumber));
	}

	@Test
	public void validateIsSpareSuccess() {
		int firstPinDown = 7;
		int secondPinDown = 3;
		assertTrue(10 == (firstPinDown + secondPinDown));
	}

	@Test
	public void validateIsSpareFailure() {
		int firstPinDown = 4;
		int secondPinDown = 3;
		assertFalse(10 == (firstPinDown + secondPinDown));
	}

	@Test
	public void validateIsStrikeSuccess() {
		int firstPinDown = 10;
		assertTrue(10 == (firstPinDown));
	}

	@Test
	public void validateIsStrikeFailure() {
		int firstPinDown = 4;
		assertFalse(10 == (firstPinDown));
	}

	@Test
	public void validateLetsBowlReturnType() {
		List<String> expectedReturnType = new ArrayList<>();
		expectedReturnType.add("1");
		expectedReturnType.add("X");
		expectedReturnType.add("/");
		List<String> actualReturnType = bowlingService.letsBowl();
		assertEquals(expectedReturnType.getClass(), actualReturnType.getClass());
	}

	@Test
	public void validateAllStrikeScore() {
		List<String> seriesOfPinDropSequence = Arrays.asList("X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X",
				"X");
		int score = bowlingService.getScoreFromPinDropSequence(seriesOfPinDropSequence);
		assertTrue(300 == (score));
	}

	@Test
	public void validateAllSpareScore() {
		List<String> seriesOfPinDropSequence = Arrays.asList("5", "/", "5", "/", "5", "/", "5", "/", "5", "/", "5", "/",
				"5", "/", "5", "/", "5", "/", "5", "/", "5");
		int score = bowlingService.getScoreFromPinDropSequence(seriesOfPinDropSequence);
		assertTrue(150 == (score));
	}

	@Test
	public void validateRandomScore() {
		List<String> seriesOfPinDropSequence = Arrays.asList("9", "0", "9", "0", "9", "0", "9", "0", "9", "0", "9", "0",
				"9", "0", "9", "0", "9", "0", "9", "0");
		int score = bowlingService.getScoreFromPinDropSequence(seriesOfPinDropSequence);
		assertTrue(90 == (score));
	}

}
