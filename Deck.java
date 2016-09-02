package blackJack;

public class Deck {
	
	// define fields here
		Card[] d;
		int index;
		// This constructor builds a deck of 52 cards.
		public Deck(Card[] deck)
		{
			d = deck;
			index = -1;
		}
		public void shuffle(Card[] cardArray)//not needed because there's one in card.java
		{
			Card holder;
			int randNum;
			for(int i=0;i<52;i++){//#of times to shuffle deck
				holder=new Card(cardArray[i].getSuit(),cardArray[i].getFace());//don't want to lose the card cardArray[i] is pointing to
				randNum=(int)Math.floor(Math.random() * (51 - 0 + 1) + 0);//keeping track of most recent random #
				cardArray[i]=new Card(cardArray[randNum].getSuit(),cardArray[randNum].getFace());
				cardArray[randNum]=new Card(holder.getSuit(),holder.getFace());
			}
		}
	
		
		// This method takes the top card off the deck and returns it.
		public Card deal()
		{
			index++;
			if (index == d.length){
			    System.out.println("-----------------------NEW DECK--------------------------");
			    shuffle(d);
			    index=-1;
			    deal();
			    return d[index];
			    
			}
			else {
				return d[index];
			}
		}
		
		
		public boolean isEmpty()
		{
			return index == d.length ; 
		}

}
