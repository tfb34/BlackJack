package blackJack;

import assign1.IO;

// This class represents one blackjack player (or the dealer)
public class Player
{
	private String fName;
	private double money=1000;
	private Hand playerHand;//hand at the most recent round; will be discarded after every round
	private boolean dealer;
	public double bet;
	private Hand splitH=new Hand();
	private Hand splitH2=new Hand();
	// define fields here

	// This constructor creates a player.
	// If isDealer is true, this Player object represents the dealer.
	public Player(String playerName, boolean isDealer)
	{
		fName=playerName;
		dealer=isDealer;
		playerHand=new Hand();
		
		// complete this method
	}

	// This method retrieves the player's name.
	public String getName()
	{
		return fName;
	}
	public void askForBet(){
		System.out.println("You can bet $0,$5,$10,$25,$100, or $"+money);
		bet=IO.readDouble();
		while(bet!=5&&bet!=10&&bet!=25&&bet!=100&&bet!=money||bet<0){//NEEDS FIXING
			System.out.println("You can bet $0,$5,$10,$25,$100, or $"+money);
			bet=IO.readDouble();
		}
	}
	public double bet(){//made askForBet() because kept getting an error message 
		askForBet();
		double moneyB=bet;
		return moneyB;
	}
	public double getBet(){//don't need it
		return bet;
	}
	public void currentMoney(int dealerScore){//int bet,int dealerScore
		if(playerHand.getScore()>21)
			money=money-bet;
		else if(dealerScore>21)
			money=money+bet;
		else if(playerHand.getScore()>dealerScore)
			  money=money+bet;
		else if(playerHand.getScore()<dealerScore)
			money=money-bet;
		else if(playerHand.getScore()==21&&dealerScore!=21)
			money=money+bet;
			
	}
//wage
	
	// This method retrieves the player's hand of cards.
 // returning an array of cards
	public Hand getHand()
	{
		return playerHand;
		
	}
	public void split(Deck deck){
		splitH.addCard(playerHand.getCard(0));
		splitH2.addCard(playerHand.getCard(1));
		playerHand.addCard(deck.deal());
		splitH.addCard(playerHand.getCard(2));
		playerHand.addCard(deck.deal());
		splitH2.addCard(playerHand.getCard(3));
		System.out.println("Hand 1: "+playerHand.getCard(0).getSuitWord()+" "+playerHand.getCard(0).getFace()+"   "+
				playerHand.getCard(2).getSuitWord()+" "+playerHand.getCard(2).getFace());
				System.out.println("Hand 2: "+playerHand.getCard(1).getSuitWord()+" "+playerHand.getCard(1).getFace()+
						"    "+playerHand.getCard(3).getSuitWord()+" "+playerHand.getCard(3).getFace());
				System.out.println(splitH.getScore());
				System.out.println(splitH2.getScore());
		bet=2*bet;
		
		
	}
	
	// This method deals two cards to the player (one face down if this is the dealer).
	// The window input should be used to redraw the window whenever a card is dealt.
	public void startRound(Deck deck)//took out, BlackjackWindow window
	{
		
		playerHand.addCard(deck.deal());
		playerHand.addCard(deck.deal());
		
	}
	public void blackJack(){//has to be called in finishround....blackJack(dealerScore);
			 System.out.println(getName()+"............."+"BLACKJACK! MONEY: $"+money);//problem 
		 
	}
	public void doubleDown(boolean k,Deck deck){
		if(playerHand.getScore()==21){
			k=false;
			System.out.println("Denied. You should stand!");
			playRound(deck);
		}
		else if(k==true){
			bet=2*bet;
		    playerHand.addCard(deck.deal());
		    System.out.println(playerHand.getCard(playerHand.newCardLocation(playerHand)-1).getSuitWord()+" "
					+playerHand.getCard(playerHand.newCardLocation(playerHand)-1).getFace());
		}
	}
	public void splitScores(int dealerS){

		if(splitH.getScore()<=21&&splitH2.getScore()<=21){//take the dealer into consideration
			if(dealerS<=21){//if there are no busts
				if(splitH.getScore()<dealerS&&splitH2.getScore()<dealerS)//if both hands lose to dealer, player loses both bets
					money=money-bet;
				
				else if(splitH.getScore()>dealerS&&splitH2.getScore()>dealerS)//if both hands win, player wins both bets
					money=money+bet;
			}//else you break even
			else
				money=money+bet;
		}
		else{
			if(splitH.getScore()>21&&splitH2.getScore()>21)//if both hands bust, you lose total bet
				money-=bet;
			else if(splitH.getScore()<=21&&splitH2.getScore()>21&&dealerS<=21){//if one hand busts and the other is less than dealer's
				if(splitH.getScore()<dealerS)
					money-=bet;//lose total bet
			}
			else if(splitH.getScore()>21&&splitH2.getScore()<=21&&dealerS<=21){//if one hands bust 
				if(splitH2.getScore()<dealerS)//and the other hand is less than dealer's
					money-=bet;//you lose total bet
			}
				
		}
		System.out.println(getName()+"..........Hand1Score: "+splitH.getScore()+"  Hand2Score: "+splitH2.getScore()+"  MONEY: $"+money);
	}

	// This method executes gameplay for one player.
	// If this player is the dealer:
	//	- hits until score is at least 17
	// If this is an ordinary player:
	//	- repeatedly asks the user if they want to hit (draw another card)
	//	  until either the player wants to stand (not take any more cards) or
	//	  his/her score exceeds 21 (busts).
	// The window input should be used to redraw the window whenever a card is dealt or turned over.
	public void playRound(Deck deck)//took out  BlackjackWindow window
	{
		if(dealer==true){
			System.out.println("  ^_^ Dealer's turn...");
			while(playerHand.getScore()<17){
				playerHand.addCard(deck.deal());
				
			    System.out.println(playerHand.getCard(playerHand.newCardLocation(playerHand)-1).getSuitWord()+" "+
				playerHand.getCard(playerHand.newCardLocation(playerHand)-1).getFace());
			}
			System.out.println("Hidden card revealed: "+ playerHand.getCard(0).getSuitWord()+" "+
				playerHand.getCard(0).getFace());
			System.out.println("Dealer's Score: "+playerHand.getScore());
			
		}
		else{
			boolean h;
			boolean b=false;
			System.out.println("Double down? y/n");
			h=IO.readBoolean();
			if(h==true){
				doubleDown(h,deck);
			}
			else{
				if(playerHand.getCard(0).getFace()==playerHand.getCard(1).getFace()){
				      System.out.println("Split? y/n");
				        b=IO.readBoolean();
				        if(b==true){
					        split(deck);
				        }
				   }
				if(b==false){
				String decision="badInput";
			  while(decision.equalsIgnoreCase("hit")==false&&decision.equalsIgnoreCase("stand")==false){//error condition
			   System.out.println("Hit or stand?");
			   decision=IO.readString();
			   }
			
			   if(decision.equalsIgnoreCase("stand")){//at this point player has only 2 cards
				 System.out.println("Your score: "+playerHand.getScore());
			      }
			   while(playerHand.getScore()<=21 && decision.equalsIgnoreCase("stand")==false){
				playerHand.addCard(deck.deal());
				System.out.println(playerHand.getCard(playerHand.newCardLocation(playerHand)-1).getSuitWord()+" "
						+playerHand.getCard(playerHand.newCardLocation(playerHand)-1).getFace());
				if(playerHand.getScore()>21){
					System.out.println("Busted! Score: "+playerHand.getScore());
					break;
			        }
				decision="badInput";
				while(decision.equalsIgnoreCase("hit")==false&&decision.equalsIgnoreCase("stand")==false){//error condition
				System.out.println("Hit or stand?");
				decision=IO.readString();
				}
				if(decision.equalsIgnoreCase("Stand")){
					System.out.println("Your score: "+playerHand.getScore());
					break;
				}
				else{
					playerHand.addCard(deck.deal());
					System.out.println(playerHand.getCard(playerHand.newCardLocation(playerHand)-1).getSuitWord()+" "
							+playerHand.getCard(playerHand.newCardLocation(playerHand)-1).getFace());
					if(playerHand.getScore()>21){
						System.out.println("Busted! Score: "+playerHand.getScore());
						break;
				        }
				}
				 decision="badInput";
					while(decision.equalsIgnoreCase("hit")==false&&decision.equalsIgnoreCase("stand")==false){//error condition
					System.out.println("Hit or stand?");
					decision=IO.readString();
					if(decision.equalsIgnoreCase("Stand")){
						System.out.println("Your score: "+playerHand.getScore());
					}
					}
			     }
			}
			}
		}
		// complete this method
	}

	// This method informs the player about whether they won, lost, or pushed.
	// It also discards the player's cards to prepare for the next round.
	// The window input should be used to redraw the window after cards are discarded.
	public void finishRound(int dealerScore)//, BlackjackWindow window took it out
	{//if the player is not the dealer,
		System.out.println();
		System.out.println("----RESULTS---");
	if(dealer==false){
		
		if(playerHand.checkAce()==true && dealerScore!=21){// if player has 21 with the first two cards
			bet=bet+(bet/2);
			currentMoney(dealerScore);
			blackJack();
		}
		else if(splitH.getCard(0)!=null){
			splitScores(dealerScore);
	        splitH.discardAll();
	        splitH2.discardAll();
		}
		else{
			currentMoney(dealerScore);
		    if(playerHand.getScore()>21)
	         	System.out.println(getName()+"..........BUSTED! Score: "+playerHand.getScore()+"  MONEY: $"+money);
	        else if(dealerScore>21)
		         System.out.println(getName()+".........You win!  Score: "+playerHand.getScore()+"  MONEY: $"+money);
	        else if(playerHand.getScore()>dealerScore)
			System.out.println(getName()+"..........You win!  Score: "+playerHand.getScore()+"  MONEY: $"+money);
	       else if(playerHand.getScore()<dealerScore)
			System.out.println(getName()+"..........You lose!  Score: "+playerHand.getScore()+"  MONEY: $"+money);
	       else
			System.out.println(getName()+".......... PUSHED    Score: "+playerHand.getScore()+"  MONEY: $"+money);
		
	}
	}
		//display message win, lose, or push (depending on player's score relative to dealer)
//discard all cards
      playerHand.discardAll();

	}	
		// complete this method
	}
