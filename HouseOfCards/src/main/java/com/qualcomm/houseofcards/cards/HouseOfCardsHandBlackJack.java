package com.qualcomm.houseofcards.card;

import java.util.ArrayList;

public class HouseOfCardsHandBlackJack extends HouseOfCardsHand {

	private char[][] defaultActions= new char [][]{
			{'h','h','d','d','d','d','h','h','h','h'},
			{'h','d','d','d','d','d','d','d','d','h'},
			{'h','d','d','d','d','d','d','d','d','d'},
			{'h','h','h','s','s','s','h','h','h','h'},
			{'h','s','s','s','s','s','h','h','h','h'},
			{'h','s','s','s','s','s','h','h','h','h'},
			{'h','s','s','s','s','s','h','h','h','h'},
			{'h','s','s','s','s','s','h','h','h','h'}
	};

	private char[][] duplicateActions= new char [][]{
			{'p','p','p','p','p','p','p','p','p','p'},
			{'h','p','p','p','p','p','p','h','h','h'},
			{'h','p','p','p','p','p','p','h','h','h'},
			{'h','h','h','h','p','p','h','h','h','h'},
			{'h','d','d','d','d','d','d','d','d','h'},
			{'h','p','p','p','p','p','h','h','h','h'},
			{'h','p','p','p','p','p','p','h','h','h'},
			{'p','p','p','p','p','p','p','p','p','p'},
			{'s','p','p','p','p','p','s','p','p','s'},
			{'s','s','s','s','s','s','s','s','s','s'}
	};

	private char[][] aceActions= new char [][]{
			{'h','h','h','h','d','d','h','h','h','h'},
			{'h','h','h','h','d','d','h','h','h','h'},
			{'h','h','h','d','d','d','h','h','h','h'},
			{'h','h','h','d','d','d','h','h','h','h'},
			{'h','h','d','d','d','d','h','h','h','h'},
			{'h','s','d','d','d','d','s','s','h','h'},
			{'s','s','s','s','s','s','s','s','s','s'},
			{'s','s','s','s','s','s','s','s','s','s'},
	};

	public HouseOfCardsHandBlackJack(){
		hand = new ArrayList<HouseOfCardsCard>();
	}

	public int getSize(){
		return hand.size();
	}

	public void addCard(HouseOfCardsCard card){
		hand.add(card);
	}

	public int getValue(boolean min){
		if(min)
			return getMinValue();
		else
			return getMaxValue();
	}

	public int getMaxValue(){
		int total = 0;
		for(HouseOfCardsCard c:hand){
			total+=(c.getValue()==HouseOfCardsCard.ACE)?11:c.getBlackJackWeight();
		}
		return total;
	}

	public int getMinValue(){
		int total = 0;
		for(HouseOfCardsCard c:hand){
			total+=c.getBlackJackWeight();
		}
		return total;
	}

	public void clearHand(){
		hand.clear();
	}

	public char getAction(HouseOfCardsCard dealer){

		char ret;
		if(hand == null || dealer == null)
			ret = 'e';
		else if(hand.size() == 0)
			ret = 'h';
		else if(getDuplicate()!=null){
			if(hand.size()==2)
				ret = decideActionDuplicate(dealer);
			else
				ret = decideActionDefault(dealer);
		}
		else if(containsAce()){
			if(hand.size()==2)
				ret = decideActionAce(dealer);
			else
				ret = decideActionDefault(dealer);
		}
		else
			ret = decideActionDefault(dealer);
		return ret;
	}

	private char decideActionDefault(HouseOfCardsCard dealer){
		int total = getValue(true);
		if(total<9)
			return 'h';
		if(total>16)
			return 's';
		return defaultActions[total-9][dealer.getBlackJackWeight()-1];
	}

	private char decideActionDuplicate(HouseOfCardsCard dealer){
		return duplicateActions[getDuplicate().getBlackJackWeight()-1][getDuplicate().getBlackJackWeight()-1];
	}

	private char decideActionAce(HouseOfCardsCard dealer){
		int value = (hand.get(0).getBlackJackWeight()!=1)?hand.get(0).getBlackJackWeight():hand.get(1).getBlackJackWeight();
		return aceActions[value-1][dealer.getBlackJackWeight()-1];
	}

	private HouseOfCardsCard getDuplicate(){
		for (int j=0;j<hand.size();j++)
		  for (int k=0;k<hand.size();k++)
		    if (j!=k && hand.get(k).getBlackJackWeight() == hand.get(j).getBlackJackWeight()){
		    	return hand.get(k);
		    }
		return null;
	}

	private boolean containsAce(){
		for (int j=0;j<hand.size();j++)
			if(hand.get(j).getBlackJackWeight()==HouseOfCardsCard.ACE)
				return true;
		return false;
	}


}
