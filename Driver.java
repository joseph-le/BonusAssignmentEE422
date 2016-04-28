package bonus;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prompt = "Mastermind! Enter guess ('Help' for list of commands): ";
		Game game = new Game(12);
		Scanner in = new Scanner(System.in);
		System.out.print(prompt);
		do {
			String request = in.nextLine();
			if (request.equalsIgnoreCase("exit") || request.equalsIgnoreCase("stop")) {
				break;
			}
			String response = game.processRequest(request);
			System.out.println(response);
			System.out.print(prompt);
		} while (in.hasNext());
		System.out.println("Exiting");
	}

}
