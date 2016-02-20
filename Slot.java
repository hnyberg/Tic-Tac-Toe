//----------------------------------------------------------------
// Filnamn: Slot.java
// Syfte:	Spelruta för plan för 3-i-rad
// Indata: 	-
// Utdata: 	true/false
// Skapare: Hannes Nyberg
// Skapad:  15-09-15
//----------------------------------------------------------------

public class Slot{

	// fields

	private int owner = 2; 		// 2 represents neutral slot; 1 and 2 are players
	private char mark = 'L'; 	// The capital letter L resembles an empty square (close enough).

	// constructors

	public Slot(){}

	// setters

	public void setOwner(int newOwner){
		owner = newOwner;
	}

	public void setMark(char newMark){
		mark = newMark;
	}

	// getters

	public int getOwner(){
		return owner;
	}

	public char getMark(){
		return mark;
	}
}