/**
 * A concrete class representing a player in the card game.
 * This class extends GeneralPlayer<Card> and manages the player's hand, bank, points, and turn.
 */
public class CardPlayer extends GeneralPlayer<Card> {

    // Fields
    private int points;          // The number of points the player has earned
    private boolean turn;        // Indicates if it is the player's turn
    private CardSet hand;        // The player's hand of cards
    private CardSet bank;        // The player's bank of matched cards

    /**
     * Constructor to initialize a CardPlayer with a given name.
     * The player starts with 0 points, an empty hand, and an empty bank.
     *
     * @param name The name of the player.
     */
    public CardPlayer(String name) {
        this.name = name;
        this.points = 0;
        this.turn = false;
        this.hand = new CardSet();
        this.bank = new CardSet();
    }

    /**
     * Returns the number of points the player has earned.
     *
     * @return The player's points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the number of points the player has earned.
     *
     * @param points The new number of points.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Returns true if it is the player's turn.
     *
     * @return True if it is the player's turn, false otherwise.
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * Sets the player's turn.
     *
     * @param turn True to set it as the player's turn, false otherwise.
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * Returns the player's hand of cards.
     *
     * @return The CardSet representing the player's hand.
     */
    public CardSet getHand() {
        return hand;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to add to the hand.
     */
    public void addToHand(Card card) {
        hand.addCard(card);
    }

    /**
     * Returns a string representation of the player's hand.
     * The string includes the player's name, the number of cards in the hand, and the card identifiers.
     *
     * @return A string representation of the player's hand.
     */
    public String handToString() {
        return name + "'s hand (" + hand.getNumCards() + " cards): " + hand.setToString();
    }

    /**
     * Returns a string representation of the player's bank.
     * The string includes the player's name, the number of cards in the bank, and the card identifiers.
     *
     * @return A string representation of the player's bank.
     */
    public String bankToString() {
        return name + "'s bank (" + bank.getNumCards() + " cards): " + bank.setToString();
    }

    /**
     * Plays a card from the player's hand.
     * The card is removed from the hand and returned.
     *
     * @return The card played by the player.
     */
    @Override
    public Card play() {
        return hand.removeTopCard();
    }

    /**
     * Returns the player's bank of matched cards.
     *
     * @return The CardSet representing the player's bank.
     */
    public CardSet getBank() {
        return bank;
    }
}
