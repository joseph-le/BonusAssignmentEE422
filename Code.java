package bonus;

import java.util.Random;

public class Code {

	private Random rand = new Random();
	private String validCodeColors = "BGOPRY"; // valid colors are blue, green,
												// orange, purple, red, and
												// yellow
	private CodeValidator validator;
	private char myCode[];

	public Code() {
		validator = new CodeValidator();
		myCode = new char[4]; // default with 4
		// generate a random code
		for (int i = 0; i < 4; i++) {
			int index = rand.nextInt(6); // 0, 1, 2, 3, 4, 5
			myCode[i] = validCodeColors.charAt(index);
		}
	}

	public Code(int codeLength) {
		validator = new CodeValidator();
		// generate a random code
		myCode = new char[codeLength];
		for (int i = 0; i < codeLength; i++) {
			int index = rand.nextInt(6); // 0, 1, 2, 3, 4, 5
			myCode[i] = validCodeColors.charAt(index);
		}
	}

	// assumes code given is valid string
	public int[] compareCodes(String code) {
		code = code.toUpperCase();

		int result[] = new int[2];
		if (code.equalsIgnoreCase(getCode())) {
			result[0] = myCode.length;
			result[1] = 0;
		}
		result[0] = 0; // initialize black count
		result[1] = 0; // initialize white count
		boolean taken[] = new boolean[myCode.length]; // used as a helper
		for (int i = 0; i < myCode.length; i++) {
			taken[i] = false;
		}
		// check blacks
		for (int j = 0; j < myCode.length; j++) {
			// System.out.println("comparing " + myCode[j] + " and " +
			// code.charAt(j));
			if (myCode[j] == code.charAt(j)) { // check matching positions
				taken[j] = true;
				result[0]++;
			}
		}

		boolean taken2[] = new boolean[myCode.length];

		for (int o = 0; o < myCode.length; o++) {
			taken2[o] = taken[o]; // set all the black pegs
		}

		// check whites
		for (int k = 0; k < myCode.length; k++) { // look for white pegs
			if (taken[k]) { // k is the index of a black peg, so skip
				continue;
			} else { // k is the index of a white peg, let's check this
				for (int a = 0; a < myCode.length; a++) { // loop through and
															// find
					// duplicates
					if (taken[a]) { // check if a is the index of a black peg,
									// if yes, skip
						continue;
					}
					if (taken2[a]) { // check if a is the index of a black or
										// white peg, if yes, skip
						continue;
					}
					if (code.charAt(k) == myCode[a]) {
						taken2[a] = true; // claim this spot as a white peg
						result[1]++;
						break;
					}
				}
			}
		}

		return result;
	}

	public char get(int index) {
		// verify the index
		if (index >= 0 && index < myCode.length) {
			return myCode[index];
		}
		return 0; // means null or something
	}

	public String getCode() {
		String strCode = "";
		for (char character : myCode) {
			strCode += character;
		}
		return strCode;
	}

	public boolean set(int index, char color) {
		if (index >= 0 && index < myCode.length) {
			if (validator.isValidColor(color)) {
				myCode[index] = color;
				return true;
			}
		}
		return false;
	}

}
