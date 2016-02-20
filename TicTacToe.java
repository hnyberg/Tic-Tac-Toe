//----------------------------------------------------------------
// Filnamn: TicTacToe.java
// Syfte:	Huvudprogrammet för TicTacToe
// Indata: 	user input
// Utdata: 	game results
// Skapare: Hannes Nyberg
// Skapad:  15-09-16
//----------------------------------------------------------------

import java.util.Scanner;

public class TicTacToe{

	public static void main(String[] args){

		// initiations and declarations

		// Here, 9 is just an arbitrary number used for initialization
		int turn = 9;		//	turn-switch
		int numPlayers = 9;
		int rowLength = 9;
		int getsX = 9;
		String input;

		// booleans for while-loops
		boolean quitGame = false;
		boolean newGame = false;
		boolean correctInput;

		Scanner scan = new Scanner(System.in);
		Board board = new Board(0);	// needs to be initialized

		Player[] players = new Player[2];	// create a list of 2 players
		players[0] = new Player(0);			// player 1 has index 0
		players[1] = new Player(1);			// player 2 has index 1

		// START GAME

		System.out.println("Welcome");

		// ask for number of players

		correctInput = false;		// set boolean to false just to start while-loop
		while (!correctInput){		// as long as input is not integer, repeat input request
			correctInput = true;	// input is correct until exception is thrown or input is outside scope
			System.out.println("\nChoose number of players (pick 1 or 2)\nTo quit game, choose 3");
			input = scan.nextLine();
			try {
				numPlayers = Integer.parseInt(input);
			}
			catch (NumberFormatException ex){
				correctInput = false;
			}
			switch (numPlayers){
				case 1:	players[1].setBot();	// set player 2 to bot
						break;
				case 2: 		// nothing happens (both players are human by default)
						break;
				case 3: quitGame = true;	// quit game
						break;
				default: System.out.println("Incorrect input; must be an integer");
						correctInput = false;	// if input is incorrect, repeat
						break;
			}
		}
		
		if (quitGame){ // if user chose to quit game, the game quits
			return; 
		}

		// pick names (method in Player-class)

		for (int i = 0; i < 2; i++){
			if (!players[i].isBot()){
				players[i].setName();
			}
		}

		// NEW ROUND

		// create new games until the user quits

		while (!quitGame){

			// show score so far
			System.out.println("\nNew round!\nScore:\n"
				+players[0].getName()+": "+players[0].getScore()+"\n"
				+players[1].getName()+": "+players[1].getScore());

			// who starts this game?

			correctInput = false;
			while (!correctInput){
				correctInput = true;
				System.out.println("\nWho starts? (pick 1 or 2)\nTo quit game, choose 3");
				input = scan.nextLine();
				try {
					turn = Integer.parseInt(input)-1;
				}
				catch (NumberFormatException ex){
					correctInput = false;
				}
				switch (turn){
					case 0:	System.out.println("Player "+(turn+1)+" chosen");
							break;
					case 1:	System.out.println("Player "+(turn+1)+" chosen");
							break;
					case 2: quitGame = true;
							System.out.println("Quiting game");
							break;
					default: System.out.println("Incorrect input; must be an integer 1-3");
							correctInput = false;
							break; 
				}
			}
			if (quitGame){ 
				return; 
			}

			// who gets X?

			correctInput = false;
			while (!correctInput){
				correctInput = true;
				System.out.println("\nWho gets X? (pick 1 or 2)\nTo quit game, choose 3");
				input = scan.nextLine();
				try {
					getsX = Integer.parseInt(input)-1;
				}
				catch (NumberFormatException ex){
					correctInput = false;
				}
				switch (getsX){
					case 0:	System.out.println("Player "+(getsX+1)+" chosen");
							players[getsX].setMark('X');
							break;
					case 1:	System.out.println("Player "+(getsX+1)+" chosen");
							players[getsX].setMark('X');
							break;
					case 2: quitGame = true;
							System.out.println("Quiting game");
							break;
					default: System.out.println("Incorrect input; must be an integer 1-3");
							correctInput = false;
							break; 
				}
			}
			if (quitGame){ 
				return; 
			}
		
			// build game board

			System.out.println("\nBuilding board: How many rows?");
			correctInput = false;
			while (!correctInput){
				correctInput = true;
				input = scan.nextLine();
				try {
					rowLength = Integer.parseInt(input);
				}
				catch (NumberFormatException ex){
					correctInput = false;
				}
				if (correctInput && rowLength > 0){
					board = new Board(rowLength);
					System.out.println("Board built");
				}
				else {
					System.out.println("Must be an integer over 0; pick again");
					correctInput = false;
				}
			}

			//	start turn, then alternate player until draw or win

			while (!newGame){
				//	draw current board
				board.draw();
				//	new move
				Turn.newMove(players[turn], board);
				//	check for winner
				if (board.haveWinner(players[turn])){
					System.out.println("\nCONGRATULATIONS "
						+players[turn].getName()
						+", you won this round!");
					players[turn].addScore();
					newGame = true;
				}
				//	check for draw
				else if (board.isDraw()){
					System.out.println("\nNo slots left; it's a draw");
					newGame = true;
				}
				// switch turn to other player
				if (turn == 0){
					turn = 1;
				}
				else {
					turn = 0;
				}
			}
		}
	}
}