package com.qualcomm.houseofcards.card;

/**
 * A single card instance.
 */
public class HouseOfCardsCard {

    /* ------------------------- PUBLIC VARIABLES --------------------------- */
    /* Suits */
    public static final int SPADES       = 0;
    public static final int HEARTS       = 1;
    public static final int DIAMONDS     = 2;
    public static final int CLUBS        = 3;
    public static final int JOKER        = 4;
    public static final int SUIT_MIN     = SPADES;
    public static final int SUIT_MAX     = JOKER;

    /* Values -- Numeric Cards */
    public static final int TWO          = 2;
    public static final int THREE        = 3;
    public static final int FOUR         = 4;
    public static final int FIVE         = 5;
    public static final int SIX          = 6;
    public static final int SEVEN        = 7;
    public static final int EIGHT        = 8;
    public static final int NINE         = 9;
    public static final int TEN          = 10;

    /* Values -- Face Cards */
    public static final int ACE          = 1;
    public static final int JACK         = 11;
    public static final int QUEEN        = 12;
    public static final int KING         = 13;

    public static final int VAL_MIN      = ACE;
    public static final int VAL_MAX      = KING;

    /* Card From Integer */
    public static final int CARD_JOKER   = 52;
    public static final int CARD_MAX     = CARD_JOKER;
    public static final int CARD_MIN     = 0;

    /* ------------------------- PRIVATE VARIABLES -------------------------- */

    /* Card From Integer */
    private static final int SUIT_DIV    = 13;
    private static final int SUIT_OFFSET = 1;
    private static final int VAL_MOD     = 13;
    private static final int VAL_OFFSET  = 1;

    /**
     * This card's suit, one of the constants SPADES, HEARTS, DIAMONDS,
     * CLUBS, or JOKER.  The suit cannot be changed after the card is
     * constructed.
     */
    private final int m_suit;

    /**
     * The card's value.  For a normal card, this is one of the values
     * 1 through 13, with 1 representing ACE.  For a JOKER, the value
     * can be anything.  The value cannot be changed after the card
     * is constructed.
     */
    private final int m_val;

    /* ------------------------- PUBLIC FUNCTIONS ---------------------------- */
    /**
     * Default Constructor
     *
     * Creates a Joker, with 1 as the associated value.
     */
    public HouseOfCardsCard() {
        m_suit  = JOKER;
        m_val   = ACE;
    } /* HouseOfCardsCard */

    /**
     * Creates a card with a specified suit and value.
     * @param val   Card Value from Constants in this Class
     *
     * @param suit  Suit Value from Constants in this Class
     *
     * @throws IllegalArgumentException Params out of range
     */
    public HouseOfCardsCard(int val, int suit) {
        if (suit > SUIT_MAX || suit < SUIT_MIN) {
          throw new IllegalArgumentException("Illegal playing card suit");
        }
        if ( (val < VAL_MIN) || (val > VAL_MAX) ) {
          throw new IllegalArgumentException("Illegal playing card value");
        }
        m_val  = val;
        m_suit = suit;
    } /* HouseOfCardsCard */

    /**
     * Create a new card from an integer value (0-52)
     *
     * @param  card     0-51 where we have a list of all SPADES, HEARTS,
     *                  DIAMONDS, CLUBS, and 51+ will return a JOKER
     *
     * @throws IllegalArgumentException Params Out of Range
     */
    public HouseOfCardsCard(int card) {
        if ( (card > CARD_MAX) || (card < CARD_MIN) ) {
            throw new IllegalArgumentException("Illegal card value");
        }
        else {
            m_val  = (card % VAL_MOD) + VAL_OFFSET;
            m_suit = (card / SUIT_DIV) + SUIT_OFFSET;
        }
    } /* HouseOfCardsCard *.

    /**
     * Returns the suit of this card as an integer.  Related to the constant
     * suit definitions in this class.
     *
     * @return   Suit of this Card: {SPADES, HEARTS, DIAMONDS, CLUBS, JOKER}
     */
    public int getSuit() {
        return m_suit;
    } /* getSuit */

    /**
     * Returns the value of this card as an integer.  Related to the constant
     * value definitions in this class.
     *
     * @return   [1-13]
     */
    public int getValue() {
        return m_val;
    } /* getValue */

    /**
     * Get the blackjack weight of this card.  Where face cards are valued
     * at 10 and other cards (besides Ace) are valued respectively.  Aces will
     * return 1 if ace_is_one is true else it will return 11.
     *
     * @param  ace_is_one   Determine value to return for Ace
     * @return 1-11 weight of card
     */
    public int getBlackJackWeight(boolean ace_is_one) {
        int ret_val = 0;

        /* ----------------------- END INIT VARIABLES ----------------------- */

        if(!isFaceCard()) {
            ret_val = m_val;
        } else if (m_val != ACE) {
            ret_val = 10;
        } else if (ace_is_one) {
            ret_val = 1;
        } else {
            ret_val = 11;
        }

        return ret_val;
    } /* getBlackJackWeight */

    /**
     * Convert card back to an integer
     *
     * @return Card value from 0 to 51+ with 51+ being a Joker
     */
    public int getID() {
        int ret_val = ((m_suit - SUIT_OFFSET) * SUIT_DIV) + (m_val - VAL_OFFSET);

        /* ----------------------- END INIT VARIABLES ----------------------- */
        return ret_val;
    } /* getID */

    /**
     * Determine if this card is a face value
     *
     * @return True if facecard else false
     */
    public boolean isFaceCard() {
        boolean ret_val = m_val == JACK
                          || m_val == QUEEN
                          || m_val == KING
                          || m_val == ACE;

        /* ----------------------- END INIT VARIABLES ----------------------- */
        return ret_val;
    } /* isFaceCard */

    /**
     * Returns a String representation of the card's suit.
     *
     * @return   {"Spades", "Hearts", "Diamonds", "Clubs", "Joker"}
     */
    public String suitToString() {
        String ret_val = "Error";

        /* ----------------------- END INIT VARIABLES ----------------------- */
        switch ( m_suit ) {
        case SPADES:
            ret_val = "Spades";
            break;

        case HEARTS:
            ret_val = "Hearts";
            break;

        case DIAMONDS:
            ret_val = "Diamonds";
            break;

        case CLUBS:
            ret_val = "Clubs";
            break;

        default:
            ret_val = "Joker";
            break;
        }

        return ret_val;
    } /* suitToString */

    /**
    * Returns a String representation of the card's value.
    *
    * @return   [2-10, Ace, Jack, Queen, King]
    */
    public String valToString() {
        String ret_val = "";

        /* ----------------------- END INIT VARIABLES ----------------------- */
        switch ( m_val ) {
            case ACE:
                ret_val = "Ace";
                break;

            case TWO:
                ret_val = "Two";
                break;

            case THREE:
                ret_val = "Three";
                break;

            case FOUR:
                ret_val = "Four";
                break;

            case FIVE:
                ret_val = "Five";
                break;

            case SIX:
                ret_val = "Six";
                break;

            case SEVEN:
                ret_val = "Seven";
                break;

            case EIGHT:
                ret_val = "Eight";
                break;

            case NINE:
                ret_val = "Nine";
                break;

            case TEN:
                ret_val = "Ten";
                break;

            case JACK:
                ret_val = "Jack";
                break;

            case QUEEN:
                ret_val = "Queen";
                break;

            case KING:
                ret_val = "King";
                break;

            default:
                ret_val = "Error";
                break;
        }

        return ret_val;
    } /* valToString */

    /**
     * Returns a string representation of this card, including both
     * its suit and its value.
     */
    public String toString() {
        String ret_val = "Err of Err";
        if (m_suit == JOKER) {
            ret_val = suitToString();
        }
        else {
            ret_val = valToString() + " of " + suitToString();
        }

        return ret_val;
    } /* toString */
} /* HouseOfCardsCard */
