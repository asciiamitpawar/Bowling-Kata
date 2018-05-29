package com.amit.spring.example.service;

/**
 * @author AmitPawar
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class BowlingService {

	/**
	 * 
	 * @return Individual player score of Bowling game.
	 */
	public int letsCalculateBowlingScore() {
		int score = 0;
		List<String> seriesOfPinDropSequence = new ArrayList<>();
		List<String> scoreFrame = new ArrayList<>();
		seriesOfPinDropSequence = letsBowl();
		score = getScoreFromPinDropSequence(seriesOfPinDropSequence);
		for (String str : seriesOfPinDropSequence) {
			if ("0".equals(str)) {
				scoreFrame.add("-");
			} else {
				scoreFrame.add(str);
			}
		}
		System.out.println("Score Frame : " + scoreFrame);
		return score;
	}

	/**
	 * 
	 * @param seriesOfPinDropSequence
	 * @return score calculated based on number of rules applied on sequence of pin
	 *         down.
	 */
	public int getScoreFromPinDropSequence(List<String> seriesOfPinDropSequence) {
		int score = 0;
		for (int count = 0; count < (seriesOfPinDropSequence.size() - 1); count++) {
			if (10 == count && seriesOfPinDropSequence.get(count).equals("X")) {
				break;
			} else if (seriesOfPinDropSequence.get(count).equals("X")) {
				if (seriesOfPinDropSequence.get(count + 1).equals("X")) {
					if (seriesOfPinDropSequence.get(count + 2).equals("X")) {
						score += 10 + 10 + 10;
					} else {
						score += 10 + 10 + Integer.valueOf(seriesOfPinDropSequence.get(count + 2));
					}
				} else {
					score += 10 + Integer.valueOf(seriesOfPinDropSequence.get(count + 1))
							+ Integer.valueOf(seriesOfPinDropSequence.get(count + 2));
				}
			} else if (seriesOfPinDropSequence.get(count).equals("/")) {
				if (seriesOfPinDropSequence.get(count + 1).equals("X")
						|| seriesOfPinDropSequence.get(count + 1).equals("/")) {
					score += 10 + 10 - Integer.valueOf(seriesOfPinDropSequence.get(count - 1));
				} else {
					score += 10 + Integer.valueOf(seriesOfPinDropSequence.get(count + 1))
							- Integer.valueOf(seriesOfPinDropSequence.get(count - 1));
				}
			} else {
				score += Integer.valueOf(seriesOfPinDropSequence.get(count));
			}
		}
		return score;
	}

	/**
	 * 
	 * @return List of String where each string literal represent sequence of pin
	 *         down.
	 */
	public List<String> letsBowl() {
		List<String> seriesOfPinDropSequence = new ArrayList<>();
		List<String> finalRoundPinDropSequence = new ArrayList<>();

		for (int frame = 1; frame <= 10; frame++) {
			if (10 == frame) {
				finalRoundPinDropSequence = getFinalRoundScoreSequence();
			} else {
				int firstRollPinDown = generateFirstPinDownValue();
				if (isStrike(firstRollPinDown)) {
					seriesOfPinDropSequence.add("X");
				} else {
					int secondRollPinDown = generateSecondPinDownValue((10 - firstRollPinDown + 1));
					if (isSpare(firstRollPinDown, secondRollPinDown)) {
						seriesOfPinDropSequence.add(String.valueOf(firstRollPinDown));
						seriesOfPinDropSequence.add("/");
					} else {
						seriesOfPinDropSequence.add(String.valueOf(firstRollPinDown));
						seriesOfPinDropSequence.add(String.valueOf(secondRollPinDown));
					}
				}
			}
		}
		for (String str : finalRoundPinDropSequence) {
			seriesOfPinDropSequence.add(str);
		}
		return seriesOfPinDropSequence;
	}

	/**
	 * 
	 * @return List of String where each string literal represent sequence of pin
	 *         down for final round.
	 */
	public List<String> getFinalRoundScoreSequence() {
		List<String> pinDropSequence = new ArrayList<>();
		int firstRollPinDown = generateFirstPinDownValue();
		if (isStrike(firstRollPinDown)) {
			pinDropSequence.add("X");
			int bonusFirstRollPinDown = generateFirstPinDownValue();
			if (isStrike(bonusFirstRollPinDown)) {
				pinDropSequence.add("X");
				int bonusSecondRollPinDown = generateFirstPinDownValue();
				if (isStrike(bonusSecondRollPinDown)) {
					pinDropSequence.add("X");
				} else {
					pinDropSequence.add(String.valueOf(bonusSecondRollPinDown));
				}
			} else {
				pinDropSequence.add(String.valueOf(bonusFirstRollPinDown));
				int bonusSecondRollPinDown = generateFirstPinDownValue();
				if (isStrike(bonusSecondRollPinDown)) {
					pinDropSequence.add("X");
				} else {
					pinDropSequence.add(String.valueOf(bonusSecondRollPinDown));
				}
			}
		} else {
			int secondRollPinDown = generateSecondPinDownValue((10 - firstRollPinDown + 1));
			if (isSpare(firstRollPinDown, secondRollPinDown)) {
				pinDropSequence.add(String.valueOf(firstRollPinDown));
				pinDropSequence.add("/");
				int bonusFirstRollPinDown = generateFirstPinDownValue();
				if (isStrike(bonusFirstRollPinDown)) {
					pinDropSequence.add("X");
				} else {
					pinDropSequence.add(String.valueOf(bonusFirstRollPinDown));
				}
			} else {
				pinDropSequence.add(String.valueOf(firstRollPinDown));
				pinDropSequence.add(String.valueOf(secondRollPinDown));
			}
		}
		return pinDropSequence;
	}

	/**
	 * 
	 * @param pinDown
	 * @return boolean value in case if it is Strike.
	 */
	public boolean isStrike(int pinDown) {
		boolean result = false;
		if (10 == pinDown) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @param firstRollPinDown
	 * @param secondRollPinDown
	 * @return boolean value in case if it is Spare.
	 */
	public boolean isSpare(int firstRollPinDown, int secondRollPinDown) {
		boolean result = false;
		if (10 == (firstRollPinDown + secondRollPinDown)) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @return Random value generated for first attempt of pin down.
	 */
	public int generateFirstPinDownValue() {
		int pinDown = 0;
		Random random = new Random();
		pinDown = random.nextInt(11);
		return pinDown;
	}

	/**
	 * 
	 * @param remainingPin
	 * @return Random value generated for second attempt of pin down based on
	 *         remaining pin after first attempt of pin down.
	 */
	public int generateSecondPinDownValue(int remainingPin) {
		int pinDown = 0;
		Random random = new Random();
		pinDown = random.nextInt(remainingPin);
		return pinDown;
	}
}