import java.util.ArrayList;

/**
 * Represents a Draw Trainer card in the card game. A Draw Trainer allows the player
 * to draw a specified number of cards from the deck when used.
 */
class DrawTrainer extends Trainer {

    private int cardsToDraw;

    /**
     * Constructs a Draw Trainer card with the specified name and number of cards to draw.
     *
     * @param name        The name of the Draw Trainer card.
     * @param cardsToDraw The number of cards this trainer allows the player to draw.
     */
    public DrawTrainer(String name, int cardsToDraw) {
        super(name);
        this.cardsToDraw = cardsToDraw;
    }

    /**
     * Uses the Draw Trainer card to draw cards from the deck and add them to the player's hand.
     * If the deck does not have enough cards, it prints a message and does not draw any cards.
     *
     * @param player The player who uses the card.
     * @param deck   The deck from which cards will be drawn.
     */
    public void use(Player player, ArrayList<Card> deck) {
        if (deck.size() < cardsToDraw) {
            System.out.println("Not enough cards in the deck to draw!");
            return;
        }

        for (int i = 0; i < cardsToDraw; i++) {
            Card drawnCard = deck.remove(0); // Draw from the top of the deck
            player.drawCard(drawnCard);
            System.out.println(player.getName() + " uses " + this + ". " +
                    drawnCard + " has been drawn.");
        }
    }

    /**
     * Returns a string representation of the Draw Trainer card, including its name
     * and the number of cards it allows the player to draw.
     *
     * @return A string representing the Draw Trainer card.
     */
    @Override
    public String toString() {
        return "Draw Trainer: " + getName() + " (Draws " + cardsToDraw + ")";
    }
}