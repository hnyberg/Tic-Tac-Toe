//----------------------------------------------------------------
// Filnamn: Turn.java
// Syfte:	Tur för 3-i-rad
// Indata: 	-
// Utdata: 	-
// Skapare: Hannes Nyberg
// Skapad:  15-09-15
//----------------------------------------------------------------

import java.util.Scanner;

public class Turn{

	// fields

	private static int slotPick;
	private static String input;
	private static boolean correctInput;
	private static boolean slotIsFree;

	public static Scanner scan = new Scanner(System.in);

	// methods

	public static void newMove(Player player, Board board){
		if (player.isBot()){
			board.botMove(player);
		}
		else {
			slotIsFree = false;
			while (!slotIsFree){

				// be om input
				correctInput = false;
				while (!correctInput){
					slotPick = -1;	// reset picked slot for every try
					System.out.println("\n"+player.getName()+", pick a slot 1-"+board.getBoardSize());
					input = scan.nextLine();
					try {
						slotPick = Integer.parseInt(input)-1;
					}
					catch (Exception noInteger){
						//System.out.println("Incorrect input; must be an number 1-"+board.getBoardSize());
					}
					if (slotPick >= 0 && slotPick < board.getBoardSize()){
						correctInput = true;
					}
					else if (slotPick < 0 || slotPick >= board.getBoardSize()){
						System.out.println("Incorrect input; must be an number 1-"+board.getBoardSize());
					}
				}
				// om ruta ledig, ta den
				if (board.isSlotFree(slotPick)){
					slotIsFree = true;
					board.pickSlot(player, slotPick);
				}
				else {	// annars, gör om och välj ny
					System.out.println("Sorry, slot already taken, try another one");
				}
			}
		}
	}
}