//----------------------------------------------------------------
// Filnamn: Player.java
// Syfte:	Spelare för 3-i-rad
// Indata: 	-
// Utdata: 	-
// Skapare: Hannes Nyberg
// Skapad:  15-09-15
//----------------------------------------------------------------

import java.util.Scanner;

public class Player{

	// fields

	String name = "Computer";
	int id;	// 0 eller 1
	int score = 0;
	char mark = 'O';
	boolean bot = false;

	Scanner scan = new Scanner(System.in);

	// constructors

	public Player(int inputID){
		id = inputID;
	}

	// setters

	public void setName(){
		System.out.println("\nPlayer "+(id+1)+", pick a name");
		name = scan.nextLine();
		System.out.println("Hi, "+name);
	}

	public void addScore(){
		score++;
	}

	public void setMark(char inputMark){
		mark = inputMark;
	}

	public void setBot(){
		bot = true;
	}

	// getters

	public String getName(){
		return name;
	}

	public int getID(){
		return id;
	}

	public int getScore(){
		return score;
	}

	public char getMark(){
		return mark;
	}

	public boolean isBot(){
		return bot;
	}


}