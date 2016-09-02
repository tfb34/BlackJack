package blackJack;

//This class represents the set of cards held by one player (or the dealer).
public class Hand
{
	
	private Card[] pHand;
	private String[][] arr=new String[11][10];
	// define fields here

	// This constructor builds a hand (with no cards, initially).
	public Hand()
	{
		pHand=new Card[11];
	}
	
	// This method retrieves the size of this hand.
	public int getNumberOfCards(Card [] pHand)
	{//may just delete i
		int num=0;
		while(pHand[num]!=null){
			num+=1;
		}
		return num; // replace this line with your code
	}
    public int newCardLocation(Hand hell){
    	
    	return getNumberOfCards(pHand);
    	
    }
	// This method retrieves a particular card in this hand.  The card number is zero-based.
	public Card getCard(int index)
	{
		if(index>=0){
			return pHand[index];
		}
		else
		 return null; 
	}

	// This method takes a card and places it into this hand.
	public void addCard(Card newcard)
	{
		pHand[getNumberOfCards(pHand)]=newcard;
	}
    public boolean checkAce(){
    	boolean j=false;
    	if(pHand[0].getValue()==1){
    		if((pHand[1].getValue()+11)==21){
    			j=true;
    		}
    	}
    	else if(pHand[1].getValue()==1){
    		if((pHand[0].getValue()+11)==21)
    			j= true;
    	}
    	return j;
    		
    }
    public boolean adviceDoubleD(){
    	if((pHand[0].getValue()+pHand[1].getValue())==11)
    		return true;
    	else
    		return false;
    }

	// This method computes the score of this hand.
	public int getScore()
	{
		int numAce=0;
		int score=0;
		for(int i=0;i<getNumberOfCards(pHand);i++){
			if(pHand[i].getFace()==1){
				numAce+=1;
			}
			score+=pHand[i].getValue();
		}
		if(numAce!=0){
			int score2=score+10;
			if(score2>score&&score2<=21)
				return score2;
		}
		return score; // replace this line with your code
	}
	public int indexRowOfHHand(){
		int index=0;
		int total=pHand[0].getValue()+pHand[1].getValue();
		if(total>=5&&total<=7)
			return 0;
		for(int i=8;i<17;i++){
			if(total==i){
				return index+1;
			}
			index++;
		}
		return 10;
	}
	public int indexRowOfDealerHH(int dealerS){//just give method the dealer's second card's value
		int index=0;
		for(int i=2;i<11;i++){
			if(dealerS==i)
				return index;
			index++;
		}
		return 9;
	}
	public String hardHand(int dealerS){
		 for(int i=0;i<arr.length;i++){
        	 for(int x=0;x<arr[0].length;x++){
        		 arr[i][x]="Hit";
        	 }
         }
         arr[1][3]="Double down ";
         arr[1][4]="Double down";
         for(int s=2;s<5;s++){
        	 for(int x=0;x<5;x++)
        		 arr[s][x]="Double down";
         }
         for(int i=3;i<5;i++){
        	 for(int x=5;x<8;x++)
        		arr[i][x]="Double down"; 
        	 }
         arr[4][8]="Double down";
         arr[4][9]="Double down";
         for(int i=6;i<11;i++){
        	 for(int x=0;x<5;x++)
        		 arr[i][x]="stand";
         }
         for(int i=10;i<11;i++){
        	 for(int x=0;x<10;x++)
        		 arr[i][x]="stand";
         }
         return arr[indexRowOfHHand()][indexRowOfDealerHH(dealerS)];
         
	}
	public int rowOfSHand(){
		int index=0;
		for(int i=13;i<20;i++){
			if(getScore()==i)
				return index;
			index++;
		}
		return 7;
	}
	public String softHand(int dealerS){
		String[][] arr=new String[8][10];//hard hand
        for(int i=0;i<6;i++){
      	 for(int x=0;x<10;x++){
      		 arr[i][x]="Hit";
      	 }
       }
       for(int i=0;i<6;i++){
      	 for(int x=2;x<5;x++)
      		 arr[i][x]="double down";
       }
       for(int i=5;i<8;i++){
      	 for(int x=0;x<10;x++)
      		 arr[i][x]="stand";
       }
       arr[4][0]="double down";
       arr[4][1]="double down";
       arr[5][1]="double down";
       arr[5][2]="double down"; 
       arr[5][3]="double down";
       arr[5][4]="double down";
       arr[5][7]="Hit";
       arr[5][8]="Hit";
       arr[6][4]="double down";
      return arr[rowOfSHand()][indexRowOfDealerHH(dealerS)];
       
	}
	public int indexSplitH(){
		int index=0;
		for(int i=2;i<5;i++){
			if((getScore()/2)==i)
				return index;
			index++;
		}
		index=3;
		for(int i=6;i<10;i++){
			if((getScore()/2)==i)
				return index;
			index++;
		}
		return 7;
	}
	public String splitHand(int dealerS){
		String[][] arr=new String [8][10];
        for(int i=0;i<8;i++){
       	 for(int x=0;x<10;x++)
       		 arr[i][x]="split";
        }
        for(int i=0;i<5;i++){
       	 for(int x=6;x<10;x++)
       		 arr[i][x]="Hit";
        }
        for(int i=1;i<3;i++){
       	 for(int x=0;x<2;x++)
       		 arr[i][x]="Hit";
        }
        arr[0][0]="Hit";
        arr[4][8]="stand";
        arr[6][8]="stand";
        arr[6][9]="stand";
        arr[6][5]="stand";
        arr[3][5]="Hit";
        arr[4][7]="Hit";
        arr[2][2]="Hit";
        arr[2][3]="double down";
        arr[2][4]="double down";
        arr[2][5]="Hit";
        return arr[indexSplitH()][indexRowOfDealerHH(dealerS)]; 
	}

	// This methods discards all cards in this hand.
	public void discardAll()
	{
		for(int i=0;i<pHand.length;i++){
			pHand[i]=null;
		}
		
	}
}
