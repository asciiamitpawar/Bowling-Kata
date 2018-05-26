package com.amit.spring.example.service;

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
	public void testScoreRangeValidity() {
		int score = bowlingService.calculateScore();
		assertTrue(0 <= score && score <= 300);
	}

	@Test
	public void testPinDownValidity() {
		List<Integer> expectedNumbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		int pinDownNumber = bowlingService.generateFirstPinDownValue();
		assertThat(expectedNumbers, hasItems(pinDownNumber));
	}

	@Test
	public void testIsSpareSuccess() {
		int firstPinDown = 7;
		int secondPinDown = 3;
		assertTrue(10 == (firstPinDown + secondPinDown));
	}

	@Test
	public void testIsSpareFailure() {
		int firstPinDown = 4;
		int secondPinDown = 3;
		assertFalse(10 == (firstPinDown + secondPinDown));
	}

	@Test
	public void testIsStrikeSuccess() {
		int firstPinDown = 10;
		assertTrue(10 == (firstPinDown));
	}

	@Test
	public void testIsStrikeFailure() {
		int firstPinDown = 4;
		assertFalse(10 == (firstPinDown));
	}

	@Test
	public void testLetsBowlReturnType() {
		List<String> expectedReturnType = new ArrayList<>();
		expectedReturnType.add("1");
		expectedReturnType.add("X");
		expectedReturnType.add("/");
		List<String> actualReturnType = bowlingService.letsBowl();
		assertEquals(expectedReturnType.getClass(), actualReturnType.getClass());
	}
	
}
