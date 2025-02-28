/**
 * A concrete class representing the table in the card game.
 * This class implements the Table<Card, CardPlayer> interface and manages the cards on the table and player turns.
 */
public class CardTable implements Table<Card, CardPlayer> {

    // Fields
    private CardSet[] places;    // Array to hold the cards in each place on the table
    private int current_place;   // The index of the current place where the next card will be placed

    /**
     * Constructor to initialize the CardTable.
     * The table starts with empty places and the current place set to 0.
     */
    public CardTable() {
        places = new CardSet[NUMBER_OF_PLACES];
        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            places[i] = new CardSet();
        }
        current_place = 0;
    }

    /**
     * Returns the identifiers of the top cards in each place on the table.
     *
     * @return An array of integers representing the identifiers of the top cards in each place.
     */
    @Override
    public int[] getPlaces() {
        int[] placeIdentifiers = new int[NUMBER_OF_PLACES];
        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            Card topCard = places[i].getTopCard();
            placeIdentifiers[i] = (topCard != null) ? topCard.identifier : -1;
        }
        return placeIdentifiers;
    }

    /**
     * Takes a turn for the given player.
     * The player plays a card, and the card is added to the current place on the table.
     * If the card matches the rank of any other card on the table, both cards are added to the player's bank,
     * and the player earns a point.
     *
     * @param player The player taking the turn.
     */
    @Override
    public void takeTurn(CardPlayer player) {
        // Get the card played by the player
        Card playedCard = player.play();
        if (playedCard == null) {
            return; // No card to play
        }

        // Add the played card to the current place on the table
        places[current_place].addCard(playedCard);

        // Check for matches in other places
        boolean matched = false;
        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            if (i != current_place) {
                Card topCard = places[i].getTopCard();
                if (topCard != null && topCard.getRank() == playedCard.getRank()) {
                    // Match found: add both cards to the player's bank and increment points
                    player.getBank().addCard(topCard);
                    player.getBank().addCard(playedCard);
                    player.setPoints(player.getPoints() + 1);

                    // Remove the matched card from the table
                    places[i].removeTopCard();
                    matched = true;
                    break;
                }
            }
        }

        // If no match was found, leave the card on the table
        if (!matched) {
            // Move to the next place for the next turn
            current_place = (current_place + 1) % NUMBER_OF_PLACES;
        } else {
            // Remove the played card from the current place (since it was matched)
            places[current_place].removeTopCard();
        }

        // End the player's turn
        player.setTurn(false);
    }

    /**
     * Returns the player's bank of matched cards.
     * This method is used internally to add matched cards to the player's bank.
     *
     * @return The CardSet representing the player's bank.
     */
    private CardSet getBank(CardPlayer player) {
        return player.getBank();
    }
}
