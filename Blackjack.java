//package blackJack;


//This is the main program for the blackjack game.
public class Blackjack
{
	// This method should:
	//	- Ask the user how many people want to play (up to 3, not including the dealer).
	//	- Create an array of players.
	//	- Create a Blackjack window.
	// 	- Play rounds until the players want to quit the game.
	//	- Close the window.
	public static void main(String[] args)
	{
		System.out.println("Welcome to BlackJack! You will be able to double, split, hit, stand, and if lucky win blackjack. ");
		System.out.println("Rules: \n1)one deck will be used at a time \n2)you cannot double after split \n3)In the start of each round depending on your hand(hard,soft,split)\n a hint will be given to you as to whether you should hit,stand,double,or split");
		System.out.println("4) you will be notified when a new deck is being played \n5)split option is only offered to a hand with the same card face\n6)in this game it is possible to get into debt so you shouldn't bet what you don't have");
		System.out.println("note: basic strategy charts(hints system) were provided by the WizardOfOdds website\n");
		int test=-1;
		while(test<=0||test>3){
		System.out.println("How many people want to play? NOTE:Maximum of 3 people,excluding dealer");
		test=IO.readInt();
		}
		Player[] listOfPlayers=new Player[test];
		int playAgain=0;
		playRound(listOfPlayers);
		System.out.println("To start enter 1 : ");
		playAgain=IO.readInt();
		while(playAgain==1){
	         test=4;
			while(test<=0||test>3){
				System.out.println("How many people want to play? NOTE:Maximum of 3 people,excluding dealer");
				test=IO.readInt();
				}
		listOfPlayers=new Player[test];
		playRound(listOfPlayers);
		System.out.println("To start enter 1 : ");
		playAgain=IO.readInt();
		}
	}

	// This method executes an single round of play (for all players).  It should:
	//	- Create and shuffle a deck of cards.
	//	- Start the round (deal cards) for each player, then the dealer.
	//	- Allow each player to play, then the dealer.
	//	- Finish the round (announce results) for each player.
	public static void playRound(Player[] players)//took out , BlackjackWindow window;
	{
		//im creating a dealer
		
		
		
		for(int i=0;i<players.length;i++){// getting each player's info
			System.out.println("Player "+(i+1)+" Enter your name: ");
			String name=IO.readString();
			//System.out.println("Will you play as the dealer?");//guessing this will always be false;
			//boolean b=IO.readBoolean();
	    	players[i]=new Player(name,false);
	    	players[i].bet();
		}
		Card[] deckf=Card.createCardArray();
		Card.shuffle(deckf);
		Deck one=new Deck(deckf);
		int g=1;
		int k=1;
		 System.out.println("__________________Round "+k+"______________________");
		while(g==1){
	   Player dealer=new Player("Dealer",true);
	   if(k>1){
		   System.out.println("__________________Round "+k+"______________________");
	   }
		for(int i=0;i<players.length;i++){//starting for each player not including dealer
			if(k>1){
				System.out.println(players[i].getName()+": ");
				players[i].bet();
			}
		    players[i].startRound(one);
		    System.out.println("****"+players[i].getName());
		    for(int x=0;x<2;x++){//at this point player will always start with two cards
		    	System.out.println(players[i].getHand().getCard(x).getSuitWord()+" "+players[i].getHand().getCard(x).getFace());
		    }
		    System.out.println();
		}
		dealer.startRound(one);
		System.out.println(dealer.getName());
		System.out.println("____");
		System.out.println("|* | ");
		System.out.println("| *| "+dealer.getHand().getCard(1).getSuitWord()+" "+dealer.getHand().getCard(1).getFace());
		System.out.println("|*_| ");
		
		for(int i=0;i<players.length;i++){
			System.out.println(players[i].getName()+"'s turn:");
			if((players[i].getHand().getScore()/2)!=5&&(players[i].getHand().getScore()/2)!=10&&players[i].getHand().getCard(0).getFace()==
					players[i].getHand().getCard(1).getFace()){
				System.out.println("According to the single deck blackJack strategy you should "
			+players[i].getHand().splitHand(dealer.getHand().getCard(1).getValue()));
			}
			else if(players[i].getHand().getCard(0).getFace()!=1&&players[i].getHand().getCard(1).getFace()!=1){//no ace=hard hand
				System.out.println("According to the single deck blackJack strategy you should "
			+players[i].getHand().hardHand(dealer.getHand().getCard(1).getValue()));
			}
			else if(players[i].getHand().getScore()!=12){//if the hand has only one ace it's a soft hand
				System.out.println("According to the single deck blackJack strategy you should "
			     +players[i].getHand().softHand(dealer.getHand().getCard(1).getValue()));
			}
			players[i].playRound(one);
		}
		dealer.playRound(one);
	    for(int i=0;i<players.length;i++){
	    	players[i].finishRound(dealer.getHand().getScore());
	    }
		// complete this method
	    g=0;
	    while(g!=1&&g!=2){
	    System.out.println("Enter 1 to play another round. To reset game enter 2" );
		g=IO.readInt();
	    }
		k=k+1;
	}
		
 }
}
