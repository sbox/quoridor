package quoridor;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 * 
 * This is the class that controls the creation of new games, as well as saving
 * and loading game states.
 * 
 * @author steve
 * 
 */
public class Manager {

	// Some constants to make the code easier to read

	static final String WELCOME_MSG = "Welcome to Quoridor!\n";
	static final String PROMPT = "> ";

	static final short INVALID = -1;
	static final short EXIT = 0;
	static final short NEW_GAME = 1;
	static final short HELP = 2;
	static final short EMPTY = 3;

	// this will store the current game being played
	static Game currentGame = null;

	// the input parser that parses user's commands
	static Parser inputParser;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		welcome();

		System.out.println("newgame or loadgame?");
		//String test;
		/*try {
			loadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*
		 * This is the information required by the parser to create a syntax
		 * table and know how many arguments each command has.
		 */
		String[] commands = { "exit", "newgame", "help", "" };
		short[] tokens = { EXIT, NEW_GAME, HELP, EMPTY };
		short[] argCount = { 0, 2, 0, 0 };

		// initialise the input parser
		inputParser = new ParserImpl(commands, tokens, argCount, INVALID);
		// start up the input loop
		inputLoop(inputParser);

	}

	/**
	 * Prints the welcome message
	 */
	public static void welcome() {
		System.out.println(WELCOME_MSG);
	}

	/**
	 * Loops parsing input until the user invokes the exit command.
	 * 
	 * @param inputParser
	 *            the input parser that parses users' input
	 */
	public static void inputLoop(Parser inputParser) {
		boolean exit = false; // flag that tells the loop to terminate
		short token; // holds the token of the command just read

		while (!exit) {
			System.out.print(PROMPT); // print the prompt

			// read the next token
			token = inputParser.ensureCommand();

			if (token == EXIT) {
				exit = true;
			} else if (token == NEW_GAME) {

				Pair<Player> players;

				/*
				 * If the user gave arguments, we can create the players without
				 * prompting for more input
				 */
				if (inputParser.hasRequiredArgs()) {
					Player _1 = new PlayerImpl(inputParser.nextArg());
					Player _2 = new PlayerImpl(inputParser.nextArg());

					players = new PairImpl<Player>(_1, _2);
					_1.setOpponent(_2);
					_2.setOpponent(_1);

				} else {

					/*
					 * Give the user an error if they tried to give arguments
					 * but gave the wrong amount.
					 */
					if (inputParser.hasErroneousArgs()) {
						System.out
								.println("Warning: Incorrect number of arguments");
					}

					// prompt for the user to enter player names
					players = enterPlayers();

				}

				// initialise a game
				currentGame = new GameImpl(players);

				// tell the user the game is starting
				System.out.println("Starting game: "
						.concat(players._1().getName()).concat(" vs ")
						.concat(players._2().getName()));

				// play the game
				currentGame.play();

			} else if (token == HELP) {
				// this prints a list of valid commands
				System.out.print(inputParser);
			} else if (token == INVALID) {
				System.out.println("Invalid command!");
			}

		}

	}

	/**
	 * Prompts the user to enter player names, then creates and returns a pair
	 * of players with those names.
	 * 
	 * @return A pair of players with names specified by the user
	 */
	public static Pair<Player> enterPlayers() {

		Player _1;
		Player _2;

		Scanner s = new Scanner(System.in);

		// read player 1's name
		System.out.print("Player 1 enter your name: ");
		_1 = new PlayerImpl(s.nextLine());

		// read player 2's name
		System.out.print("Player 2 enter your name: ");
		_2 = new PlayerImpl(s.nextLine());

		_1.setOpponent(_2);
		_2.setOpponent(_1);
		
		// create and return a pair containing those players
		return new PairImpl<Player>(_1, _2);
	}

	public static String loadFile( ) throws IOException {
		String retVal = "";
		String fileName = "";
		File file = new File(fileName);
		String path = ".";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		boolean exit = false;
		String s;
		
		//Print out a list of all the current saved game states
		System.out.println("Saved game states:");
		for (int i = 4; i < listOfFiles.length-4; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println(listOfFiles[i].getName());
			}
			//System.out.println("i is " +i);
		}
		
		while (!file.exists() && exit == false) {
			System.out.println("Which game state would you like to load? Type exit to not load any.");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			if ((s = in.readLine()) != null) {
				/*if(!fileName.contains(".quoridor")) {
					fileName+=".quoridor";
				} else if (fileName.cont)*/
				if (s.contains("exit")) {
					exit = true;
				} else {
					fileName = s;
					if (!fileName.contains(".quoridor")) {
						fileName+=".quoridor";
					}
					file = new File(fileName);
				}
			}
		}
		
		//Open the file that they want to load 
		if (exit != true) {
			file.getName(); 
			
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream datain = new DataInputStream(fstream); 
			//create a buffer reader to go through the file 
			BufferedReader br = new BufferedReader(new InputStreamReader(datain)); 
			String moves; 
			//Read the file line by line 
			while ((moves = br.readLine()) != null) {
				retVal+=moves; 
			}
			datain.close();
			System.out.println(retVal);
		}
		return retVal;
	}

	public static void saveGame()
	//public static void saveGameState(String filename, String moves)
			throws IOException {
		System.out.println("What name would you like to save game as?");
		String filename="";
		filename += ".quoridor";
		// create a new file to be saved
		File newFile;
		newFile = new File(filename);

		// if the filename already exists delete it before creating a new one
		/*
		 * if (newFile.exists()) { newFile.delete(); }
		 */

		boolean exit = false;
		while (newFile.exists() && exit == false) {
			System.out
					.println("File already exists with that name. Would you like to replace? Yes or no?");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String s;
			if ((s = in.readLine()) != null) {
				if (s.contains("no")) {
					System.out.println("Please type a new name to save game as.");
					newFile = new File(in.readLine() + ".quoridor");
				} else if (s.contains("yes")) {
					exit = true;
					newFile.delete();
				}
			}
		}

		// create a new file with the filename
		newFile.createNewFile();
		// write the string of moves to the file
		Writer output = null;
		String text = currentGame.formatFile();
		//String text = "Testing the string file\n\n testing white space\n and more white space!";
		output = new BufferedWriter(new FileWriter(newFile));
		output.write(text);
		output.close();
		// createFile(newFile, moves);

	}


}
