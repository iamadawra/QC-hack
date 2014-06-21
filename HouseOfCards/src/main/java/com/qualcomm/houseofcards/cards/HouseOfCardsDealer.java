package com.qualcomm.houseofcards.card;

// External Includes
import java.util.*;

/**
 * An entire deck of cards
 */
public class HouseOfCardsDealer {

    /* ------------------------- PUBLIC VARIABLES --------------------------- */
    Queue<HouseOfCardsCard> m_cards;

    /* ------------------------- PUBLIC FUNCTIONS --------------------------- */
    /**
     * Create a new dealer with only one deck and no jokers.
     */
    public HouseOfCardsDealer() {
        m_cards = new LinkedList<HouseOfCardsCard>();
        addDeck(false);
    } /* HouseOfCardsDealer */

    /**
     * Create a new dealer with a variable number of cards and determine if
     * there are jokers
     *
     * @param  num_decks    Number of decks to add to this dealer
     * @param  jokers       Whether or not jokers are in the deck
     */
    public HouseOfCardsDealer(int num_decks, boolean jokers) {
        m_cards = new LinkedList<HouseOfCardsCard>();
        for(int i = 0; i < num_decks; i++) {
            addDeck(jokers);
        }
    } /* HouseOfCardsDealer */

    /**
     * Determine if the dealer still has cards left
     *
     * @return Whether or not the dealer still has cards left to give
     */
    public boolean hasCards() {
        boolean ret_val = !m_cards.isEmpty();

        /* ----------------------- END INIT VARIABLES ----------------------- */
        return ret_val;
    } /* hasCards */

    /**
     * Pop a new card from the queue if possible.
     *
     * @return New card from the deck
     *
     * @throws IllegalMonitorStateException
     */
    public HouseOfCardsCard drawCard() {
        if (hasCards()) {
            return m_cards.remove();
        } else {
            throw new IllegalMonitorStateException("No cards in the deck");
        }
    } /* drawCard */

    /**
     * Discard one card into oblivion
     *
     * @throws IllegalMonitorStateException
     */
    public void discard() {
        if (hasCards()) {
            m_cards.remove();
        } else {
            throw new IllegalMonitorStateException("No cards in the deck");
        }
    } /* discard */

    /**
     * Randomly permute the cards in our linked list
     */
    public void shuffle() {
        Collections.shuffle((List) m_cards);
    } /* shuffle */

    /* ------------------------- PRIVATE FUNCTIONS -------------------------- */
    /**
     * Add a new deck of 52 (56) cards to this dealer depending on whether
     * or not we want jokers included in the deck.
     *
     * @param jokers Include jokers in this deck or not
     */
    private void addDeck(boolean jokers) {
        /* Add Normal Cards to the Deck */
        for(int i = HouseOfCardsCard.CARD_MIN; i < HouseOfCardsCard.CARD_MAX; i++) {
            m_cards.add( new HouseOfCardsCard(i));
        }

        /* Add Jokers to the Deck */
        if(jokers) {
            for(int i = 0; i < 4; i++) {
                m_cards.add( new HouseOfCardsCard(HouseOfCardsCard.CARD_JOKER));
            }
        }
    } /* addDeck */

} /* HouseOfCardsDealer */
