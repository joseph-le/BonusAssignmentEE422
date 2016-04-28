package bonus;

import java.util.Scanner;

public class Game {

	private History history;
	private int numMoves;
	private int movesLeft;
	private CodeValidator validator;
	private Code theCode;
	private boolean hardMode;
	private boolean gameEnded;
	private String strCode;
	private int codeLength;
	Scanner in;

	public Game(int numMoves) {
		// TODO Auto-generated constructor stub
		history = new History();
		validator = new CodeValidator();
		this.numMoves = numMoves;
		movesLeft = numMoves;
		codeLength = 4;
		theCode = new Code(codeLength);
		strCode = theCode.getCode();
		hardMode = false;
		gameEnded = false;
		in = new Scanner(System.in);
	}

	public Game(int numMoves, boolean hardMode) {
		// TODO Auto-generated constructor stub
		this(numMoves);
		this.hardMode = hardMode;
	}

	public String processRequest(String request) {
		if (request == null) {
			return "Invalid request";
		} else if (request.equalsIgnoreCase("history")) {
			return history.returnFullHistory();
		} else if (request.equalsIgnoreCase("Reset")) {
			reset();
			return "Reset the game, there is now a new code";
		} else if (request.equalsIgnoreCase("hard")) {
			return "Changing the game to hard mode, this does not affect current number of guesses";
		} else if (request.equalsIgnoreCase("easy")) {
			return "Changing the game to easy mode, this does not affect current number of guesses";
		} else if (request.equalsIgnoreCase("cheat")) {
			return "There is no cheating in this game. I lied in the help.";
		} else if (request.equalsIgnoreCase("SetGuessLimit")) {
			System.out.println("Enter new guess limit: ");
			numMoves = in.nextInt();
			if (numMoves < movesLeft) {
				movesLeft = numMoves;
			}
			return "Setting the maximum number of guesses and adjusting guesses left. " + numMoves + " guesses left.";
			
		} else if (request.equalsIgnoreCase("SetCodeLength")) {
			System.out.print("Enter new code length: ");
			int newCodeLength = in.nextInt();
			codeLength = newCodeLength;
			validator.setCodeLength(newCodeLength);
			reset();
			return "Changed the code length to " + newCodeLength + ". Reseting game...";
		} else if (request.equalsIgnoreCase("help")) {
			String response = "\n";
			response += "How to play the game:\n";
			response += "https://en.wikipedia.org/wiki/Mastermind_(board_game)\n\n";
			response += "List of Commands:\n";
			response += "'History' gives a history of events.\n";
			response += "'Reset' resets the game.\n";
			response += "'Hard' changes the game to hard mode, invalid guesses reduce number of guesses allowed.\n";
			response += "'Easy' changes the game to easy mode, invalid guesses do not affect number of guesses left.\n";
			response += "'Cheat' ends the game early. Reveals the code\n";
			response += "'SetGuessLimit' changes the number of guesses\n";
			response += "'SetCodeLength' changes the length of the code\n";
			response += "'Exit' or 'Stop' ends the program.\n";
			return response;
		} else {
			if (movesLeft <= 0) {
				return "No more guesses, the code was " + strCode;
			}
			return processGuess(request);
		}
	}

	private String processGuess(String request) {
		// TODO Auto-generated method stub
		String response;
		if (gameEnded) {
			return "The game as already ended, the code was " + strCode;
		}
		if (validator.isValidCode(request) == false) {
			if (hardMode) {
				movesLeft--;
				response = "Invalid Guess " + movesLeft + " guesses left. ";
			} else {
				response = "Invalid Guess " + movesLeft + " guesses left. ";
			}
		} else {
			int[] result = theCode.compareCodes(request);
			if (result[0] == 4) {
				gameEnded = true;
				response = "You guessed " + theCode.getCode() + " right!! With " + movesLeft + " guesses left";
			} else {
				movesLeft--;
				response = result[0] + " black peg(s) and " + result[1] + " white peg(s). " + movesLeft
						+ " guesses left.";
			}
		}
		history.add(request, response);
		return response + "\n";
	}

	public void reset() {
		history = new History();
		theCode = new Code(codeLength);
		strCode = theCode.getCode();
		movesLeft = numMoves;
		hardMode = false;
		gameEnded = false;
	}

	public void reset(int moves) {
		reset();
		numMoves = moves;
		movesLeft = moves;
	}

}
