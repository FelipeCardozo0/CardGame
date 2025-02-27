public class CardTable implements Table<Card, CardPlayer> {
    private CardSet[] places;
    private int currentPlace;

    public CardTable() {
        places = new CardSet[NUMBER_OF_PLACES];
        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            places[i] = new CardSet(1);
        }
        currentPlace = 0;
    }

    @Override
    public int[] getPlaces() {
        int[] identifiers = new int[NUMBER_OF_PLACES];
        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            Card topCard = places[i].getTopCard();
            identifiers[i] = (topCard != null) ? topCard.identifier : -1;
        }
        return identifiers;
    }

    @Override
    public void takeTurn(CardPlayer player) {
        Card playedCard = player.play();
        if (playedCard == null) return;

        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            if (i != currentPlace) {
                Card topCard = places[i].getTopCard();
                if (topCard != null && topCard.getRank() == playedCard.getRank()) {
                    player.getHand().addCard(topCard);
                    player.getHand().addCard(playedCard);
                    player.setPoints(player.getPoints() + 1);
                    places[i].removeTopCard();
                    player.setTurn(false);
                    return;
                }
            }
        }
        places[currentPlace].addCard(playedCard);
        currentPlace = (currentPlace + 1) % NUMBER_OF_PLACES;
        player.setTurn(false);
    }
}
