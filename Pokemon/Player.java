import java.util.ArrayList;

/**
 * Represents a player in the card game. Each player has a name, a hand of cards,
 * and an active Pokémon. The player can draw cards, play cards from their hand, 
 * and use Trainer cards.
 */
class Player {

    private String name;
    private ArrayList<Card> hand;
    private Pokemon activePokemon;

    /**
     * Constructs a Player with the specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    /**
     * Returns the name of the player.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's hand of cards.
     *
     * @return An ArrayList of cards in the player's hand.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Returns the player's active Pokémon.
     *
     * @return The player's active Pokémon, or null if none is set.
     */
    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    /**
     * Sets the player's active Pokémon.
     *
     * @param activePokemon The Pokémon to set as active.
     */
    public void setActivePokemon(Pokemon activePokemon) {
        this.activePokemon = activePokemon;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to be drawn into the player's hand.
     */
    public void drawCard(Card card) {
        hand.add(card);
    }

    /**
     * Plays a card from the player's hand by removing it at the specified index.
     *
     * @param index The index of the card in the hand.
     * @return The card that was played.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Card playCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException("Invalid card index.");
        }
        return hand.remove(index);
    }

    /**
     * Checks if the player has an active Pokémon with remaining hit points.
     *
     * @return True if there is an active Pokémon with hit points greater than zero; false otherwise.
     */
    public boolean hasActivePokemon() {
        return activePokemon != null && activePokemon.getHitPoints() > 0;
    }

    /**
     * Checks if there is at least one Pokémon card in the player's hand.
     *
     * @return True if there is a Pokémon card in the hand; false otherwise.
     */
    public boolean hasPokemonInHand() {
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string of the player's hand and active Pokémon.
     *
     * @return A string representing the player's hand and active Pokémon.
     */
    @Override
    public String toString() {    
        StringBuilder handDisplay = new StringBuilder(name + "'s Hand:\n");
        for (Card card : hand) {
            handDisplay.append(card.toString()).append(" | ");
        }
        handDisplay.append("\n");
        handDisplay.append("Active Pokémon: ").append(activePokemon != null ? activePokemon.getName() : "None");
        return handDisplay.toString();
    }

    /**
     * Sets the first Pokémon found in the player's hand as the active Pokémon
     * and removes it from the hand.
     */
    public void drawPokemon() {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card instanceof Pokemon) {
                setActivePokemon((Pokemon) card);
                hand.remove(i);
                break;
            }
        }
    }

    /**
     * Uses a Trainer card from the player's hand at the specified index, if applicable.
     * If the card is a HealTrainer, it applies healing effects.
     * If the card is a DrawTrainer, it allows the player to draw cards from the deck.
     *
     * @param cardIndex The index of the Trainer card in the player's hand.
     * @param game The CardGame instance for accessing the deck.
     */
    public void useTrainerCard(int cardIndex, CardGame game) {
        if (cardIndex >= 0 && cardIndex < hand.size()) {
            Card card = hand.get(cardIndex);
            //Check if card is a trainer
            if (card instanceof HealTrainer) {
                ((HealTrainer) card).use(this);
                hand.remove(cardIndex);
            } else if (card instanceof DrawTrainer) {
                ((DrawTrainer) card).use(this, game.getDeck());
                hand.remove(cardIndex);
            } 
            //If card isn't a trainer, cannot be used as such (this should never occur)
            else {
                System.out.println("Selected card is not a Trainer card.");
            }
        } else {
            System.out.println("Invalid card selection.");
        }
    }
}