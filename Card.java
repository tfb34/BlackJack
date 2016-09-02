//package blackJack;

//This class represents one playing card.
public class Card
{
	public static void main(String[] args){
		Card[] hello= createCardArray();
		for(int x=0;x<52;x++){//prints out a deck of cards A-k SPADES, A-K DIAMONDS,A-K CLUBS, A-K HEARTS
			System.out.print(hello[x].getSuit());
			System.out.print(hello[x].getFace());
			System.out.println();
		}
		shuffle(hello);
		for(int x=0;x<52;x++){
			System.out.print(hello[x].getSuit());
			System.out.print(hello[x].getFace());
			System.out.println();
		}
		
	}
	// Card suits (provided for your convenience - use is optional)
	public static final int SPADES   = 0;
	public static final int DIAMONDS = 1;
	public static final int CLUBS    = 2;
	public static final int HEARTS   = 3;

	// Card faces (provided for your convenience - use is optional)
	public static final int ACE      = 1;
	public static final int TWO      = 2;
	public static final int THREE    = 3;
	public static final int FOUR     = 4;
	public static final int FIVE     = 5;
	public static final int SIX      = 6;
	public static final int SEVEN    = 7;
	public static final int EIGHT    = 8;
	public static final int NINE     = 9;
	public static final int TEN      = 10;
	public static final int JACK     = 11;
	public static final int QUEEN    = 12;
	public static final int KING     = 13;


	// define fields here
	int suit;
	int face;
	
	// This constructor builds a card with the given suit and face, turned face down.
	public Card(int cardSuit, int cardFace)
	{
		suit = cardSuit;
		face = cardFace;
	}

	// This method retrieves the suit (spades, hearts, etc.) of this card.
	public int getSuit()
	{
		return suit; 
	}
	
	// This method retrieves the face (ace through king) of this card.
	public int getFace()
	{
		return face; 
	}
	
	// This method retrieves the numerical value of this card
	// (usually same as card face, except 1 for ace and 10 for jack/queen/king)
	public int getValue()
	{
		if (face <= 10)
			return face;
		else 
			return 10;
	}
	public String getSuitWord(){
		if(suit==0)
			return "SPADES";
		else if(suit==1){
			return "DIAMONDS";
		}
		else if(suit==2){
			return "CLUBS";
		}
		else
			return "HEARTS";
	}
	//#############################################333
	// This method determines whether the front of the card should be visible.
		public boolean isFaceUp()
		{
			return false; // replace this line with your code
		}

		// This method records that the front of the card should be visible.
		public void turnFaceUp()
		{
			
			// complete this method
		}
		
		// This method records that only the back of the card should be visible.
		public void turnFaceDown()
		{
			// complete this method
		}
	
	//##########################################333

	// This method allows you to print a card's values
	public String toString(){
	
		char f;
		char s;
		
		if (this.face == Card.ACE)
			f = 'A';
		else if(this.face == Card.JACK)
			f = 'J';
		else if(this.face == Card.QUEEN)
			f = 'Q';
		else if(this.face == Card.KING)
			f = 'K';
		else
			f = Character.forDigit(this.face,10);
			
		switch(this.suit){
		
			case Card.SPADES:
				s = 'S';
				break;
			case Card.DIAMONDS:
				s = 'D';
				break;
			case Card.HEARTS:
				s = 'H';
				break;
			case Card.CLUBS:
				s = 'C';
				break;
			default:
				s = 'E';
		}
		
		return ""+f+"["+s+"]";
	
	}
     public static Card[] createCardArray(){//Note: will come out in the order of A-k SPADES,A-K H,C,D
			int counter=0;
			Card[] arrayOfCards= new Card[52];
			for(int i=SPADES;i<4;i++){//for each suit there are thirteen cards
				for(int x=ACE;x<14;x++){
				    arrayOfCards[counter]=new Card(i,x);
				    counter+=1;
				}
			}
			return arrayOfCards;
		}
		public static void shuffle(Card[] cardArray){
			Card holder;
			int randNum;
			
			for(int i=0;i<52;i++){//#of times to shuffle deck
				holder=cardArray[i];
				randNum=(int)Math.floor(Math.random() * (51 - 0 + 1) + 0);
				cardArray[i]=cardArray[randNum];
				cardArray[randNum]=holder;
			}
			
		}
	
}
