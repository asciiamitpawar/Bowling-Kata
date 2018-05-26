package com.amit.spring.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class BowlingService {

	public int calculateScore() {
		int score = 0;
		List<String> pinDropSequence = new ArrayList<>();
		pinDropSequence = letsBowl();
		for (int count = 0; count < pinDropSequence.size(); count++) {
			if (pinDropSequence.get(count).equals("X")) {
				if (pinDropSequence.get(count + 1).equals("X") || pinDropSequence.get(count + 1).equals("/")) {
					if (pinDropSequence.get(count + 2).equals("X") || pinDropSequence.get(count + 2).equals("/")) {
						score += 10 + 10 + 10;
					} else {
						score += 10 + 10 + Integer.valueOf(pinDropSequence.get(count + 2));
					}
				} else {
					score += 10 + Integer.valueOf(pinDropSequence.get(count + 1))
							+ Integer.valueOf(pinDropSequence.get(count + 2));
				}
			} else if (pinDropSequence.get(count).equals("/")) {
				if (pinDropSequence.get(count + 1).equals("X") || pinDropSequence.get(count + 1).equals("/")) {
					score += 10 + 10;
				} else {
					score += 10 + Integer.valueOf(pinDropSequence.get(count + 1));
				}
			} else {
				score += Integer.valueOf(pinDropSequence.get(count));
			}
		}
		return score;
	}

	public List<String> letsBowl() {
		List<String> pinDropSequence = new ArrayList<>();

		for (int frame = 1; frame <= 10; frame++) {
			if (10 == frame) {
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
			} else {
				int firstRollPinDown = generateFirstPinDownValue();
				if (isStrike(firstRollPinDown)) {
					pinDropSequence.add("X");
				} else {
					int secondRollPinDown = generateSecondPinDownValue((10 - firstRollPinDown + 1));
					if (isSpare(firstRollPinDown, secondRollPinDown)) {
						pinDropSequence.add("/");
					} else {
						pinDropSequence.add(String.valueOf(firstRollPinDown));
						pinDropSequence.add(String.valueOf(secondRollPinDown));
					}
				}
			}
		}
		return pinDropSequence;
	}

	public boolean isStrike(int pinDown) {
		boolean result = false;
		if (10 == pinDown) {
			result = true;
		}
		return result;
	}

	public boolean isSpare(int firstRollPinDown, int secondRollPinDown) {
		boolean result = false;
		if (10 == (firstRollPinDown + secondRollPinDown)) {
			result = true;
		}
		return result;
	}
	
	public int generateFirstPinDownValue() {
		int pinDown = 0;
		Random random = new Random();
		pinDown = random.nextInt(11);
		return pinDown;
	}

	public int generateSecondPinDownValue(int remainingPin) {
		int pinDown = 0;
		Random random = new Random();
		pinDown = random.nextInt(remainingPin);
		return pinDown;
	}
}