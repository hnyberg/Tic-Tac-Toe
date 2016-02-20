//----------------------------------------------------------------
// Filnamn: Board.java
// Syfte:	Spelplan för 3-i-rad
// Indata: 	-
// Utdata: 	true/false
// Skapare: Hannes Nyberg
// Skapad:  15-09-15
//----------------------------------------------------------------

import java.util.Random;

public class Board{

	// fields

	int side;
	int boardSize;
	int count = 0;
	int botSlot;

	boolean slotIsFree = false;
	boolean winner = false;
	boolean noSlotsLeft = false;

	Slot[] board;

	Random rand = new Random();

	// constructors

	public Board(int inputSide){
		side = inputSide;
		boardSize = side * side;		//	get board size
		board = new Slot[boardSize];	// create new board
		for (int i = 0; i < boardSize; i++){
			board[i] = new Slot();
		}
	}

	// setters

	public void pickSlot(Player player, int index){
		board[index].setOwner(player.getID());
		board[index].setMark(player.getMark());
	}

	public void botMove(Player player){
		while (!slotIsFree){
			botSlot = rand.nextInt(boardSize);
			if (isSlotFree(botSlot)){
				slotIsFree = true;
				board[botSlot].setOwner(player.getID());
				board[botSlot].setMark(player.getMark());
				System.out.println("\n\n"+player.getName()+" chose slot "+botSlot);
			}
		}
		slotIsFree = false;
	}

	public void draw(){
		for (int i = 0; i < boardSize; i++){
			if (i % side == 0){
				System.out.println("");
			}
			System.out.print(" "+board[i].getMark());
		}
		System.out.println("");
	}

	// getters

	public int getBoardSize(){
		return boardSize;
	}

	public boolean isSlotFree(int index){	// if slot available, make turn
		if (board[index].getOwner() == 2){	// ...else, ask for new input
			return true;
		}
		else return false;
	}

	public boolean isDraw(){
		noSlotsLeft = true;
		for (int i = 0; i < boardSize; i++){
			if (board[i].getOwner() == 2){
				noSlotsLeft = false;
			}
		}
		return noSlotsLeft;
	}

	public boolean haveWinner(Player player){
		
		// check horisontal rows
		for (int i = 0; i < side; i++){
			count = 0;
			for (int j = 0; j < side; j++){
				if (board[i*side + j].getOwner() == player.getID()){
					count++;
				}
			}
			if (count == side){
				winner = true;
			}
		}
		count = 0;

		// check vertical rows
		for (int i = 0; i < side; i++){
			count = 0;
			for (int j = 0; j < side; j++){
				if (board[i + j*side].getOwner() == player.getID()){
					count++;
				}
			}
			if (count == side){
				winner = true;
			}
		}
		count = 0;

		// check top-left diagonal row
		for (int i = 0; i < side; i++){
			if (board[i*(side+1)].getOwner() == player.getID()){
				count++;
			}
			if (count == side){
				winner = true;
			}
		}
		count = 0;

		// check top-right diagonal row
		for (int i = 0; i < side; i++){
			if (board[(1+i)*(side-1)].getOwner() == player.getID()){
				count++;
			}
			if (count == side){
				winner = true;
			}
		}
		count = 0;

		return winner;
	}
}