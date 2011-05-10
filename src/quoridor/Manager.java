package quoridor;

import java.util.Scanner;

public class Manager {
	
	static final String WELCOME_MSG = "Welcome to Quoridor!";
	static final String PROMPT = "> ";
	
	static final int UNKNOWN = -1;
	static final int EXIT = 0;
	static final int NEW_GAME = 1;
	
	static Game currentGame = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		welcome();
		
		

	}
	
	public static void welcome() {
		System.out.println(WELCOME_MSG);
	}
	
	public static void prompt() {
		
		boolean exit = false;
		String command;
		int task;
		Scanner s = new Scanner(System.in);
		
		while (!exit) {
			command = s.nextLine();
			task = interpret(command);
			
			if (task == EXIT) {
				exit = false;
			} else if (task == NEW_GAME) {
				currentGame = new GameImpl(enterPlayers());
				currentGame.play();
			}
			
		}
		
	}
	
	public static Pair <Player> enterPlayers() {
		
		Player p1;
		Player p2;
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("Player 1 enter your name: ");
		p1 = new PlayerImpl(s.nextLine());
		
		System.out.print("Player 2 enter your name: ");
		p2 = new PlayerImpl(s.nextLine());		
		
		return new PairImpl <Player> (p1, p2);
	}
	
	public static int interpret(String command) {
		int result = UNKNOWN;
		
		if (command.equals("exit")) {
			result = EXIT;
		} else if (command.equals("new")) {
			result = NEW_GAME;
		}
		
		return result;
	}
	
	

}
