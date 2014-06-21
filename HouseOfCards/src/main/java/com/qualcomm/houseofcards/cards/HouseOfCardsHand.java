package com.qualcomm.houseofcards.card;

import java.util.ArrayList;


public abstract class HouseOfCardsHand{

	protected ArrayList<HouseOfCardsCard> hand;

	public abstract int getSize();

	public abstract void addCard(HouseOfCardsCard card);

	public abstract int getValue(boolean min);

	public abstract void clearHand();
}
