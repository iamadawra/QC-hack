package com.qualcomm.houseofcards;

import com.qualcomm.houseofcards.card.*;

public class HelloWorld {

    public static void main(String args[]) {
        HouseOfCardsDealer dealer = new HouseOfCardsDealer();

        int i = 0;
        while(dealer.hasCards()) {
            System.out.println(i++ + " -- " + dealer.drawCard().toString());
        }

        dealer = new HouseOfCardsDealer();
        dealer.shuffle();

        while(dealer.hasCards()) {
            System.out.println(i++ + " -- " + dealer.drawCard().toString());
        }
    }

} /* HelloWorld */
