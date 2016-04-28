package bonus;

public class CodeValidator {

	private String validCodeColors = "BGOPRY"; // valid colors are blue, green,
												// orange, purple, red, and
												// yellow
	private int codeLength;

	public CodeValidator() {
		codeLength = 4;
	}

	boolean isValidCode(char[] code) {
		String thing = code.toString();
		return isValidCode(thing);
	}

	boolean isValidCode(String code) {
		if (code == null) {
			return false;
		}

		if (code.length() != codeLength) {
			return false;
		}

		// don't care about case sensitivity
		code = code.toUpperCase();

		for (int i = 0; i < codeLength; i++) {
			char currentChar = code.charAt(i);
			if (!isValidColor(currentChar)) {
				return false;
			}
		}

		// passed all validation issues
		return true;
	}

	boolean isValidColor(char color) {
		String temp = "";
		temp = temp + color;
		temp.toUpperCase();
		color = temp.charAt(0);
		if (validCodeColors.indexOf(color) == -1) {
			return false;
		}
		return true;
	}

	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}

}
