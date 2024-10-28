/**
 * Represents an abstract Card in the card game. Each card has a name and can be extended
 * to create specific types of cards, such as Pokémon or Trainer cards.
 */
abstract class Card {

    protected String name;

    /**
     * Constructs a Card with no name. Primarily used by subclasses that will
     * set the name later.
     */
    public Card() {
        // Default constructor
    }

    /**
     * Constructs a Card with the specified name.
     *
     * @param name The name of the card.
     */
    public Card(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the card.
     *
     * @return The card's name.
     */
    public String getName() {
        return name;
    }
}