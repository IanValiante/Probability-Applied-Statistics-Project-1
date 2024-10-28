import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a deck of cards in the card game. The deck contains Pokémon, Energy, and Trainer cards.
 * It supports shuffling and drawing cards.
 */
class Deck {

    private List<Card> cards;

    /**
     * Constructs a Deck and initializes it with a predefined set of Pokémon, Energy, and Trainer cards.
     */
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    /**
     * Initializes the deck with a fixed set of cards: 4 Pokémon cards, 15 Energy cards, and 41 Trainer cards.
     * The Trainer cards alternate between Heal and Draw Trainers.
     */
    private void initializeDeck() {
        // Add Pokémon cards
        cards.add(new Pikachu());
        cards.add(new Charmander());
        cards.add(new Bulbasaur());
        cards.add(new Squirtle());

        // Add Energy cards
        for (int i = 0; i < 15; i++) {
            cards.add(new Energy("Basic Energy " + (i + 1)));
        }

        // Add Trainer cards
        for (int i = 0; i < 41; i++) { // 41 Trainer cards to reach 60 total cards
            if (i % 2 == 0) {
                cards.add(new HealTrainer("Heal Trainer " + (i + 1), 20)); // Heal Trainer cards
            } else {
                cards.add(new DrawTrainer("Draw Trainer " + (i + 1), 1)); // Draw Trainer cards
            }
        }
    }

    /**
     * Shuffles the deck by randomly swapping the positions of cards.
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int j = rand.nextInt(cards.size());
            // Swap cards
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    /**
     * Draws a card from the top of the deck. If the deck is empty, returns null.
     *
     * @return The card drawn from the top of the deck, or null if the deck is empty.
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1); // Draw from the top of the deck
    }

    /**
     * Returns the list of cards currently in the deck.
     *
     * @return A list of cards in the deck.
     */
    public List<Card> getCards() {
        return cards;
    }
}