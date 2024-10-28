/**
 * The Trainer class represents a trainer card in the game. Trainer cards are used to provide
 * various effects during gameplay, such as healing Pokémon or drawing extra cards.
 * This class extends the Card class and includes a name attribute.
 */
public class Trainer extends Card {
    private String name;

    /**
     * Constructs a Trainer card with the specified name.
     *
     * @param name the name of the Trainer card
     */
    public Trainer(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the Trainer card.
     *
     * @return the name of the Trainer card
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the Trainer card.
     * This includes the name of the card.
     *
     * @return a string representation of the Trainer card
     */
    @Override
    public String toString() {
        return "Trainer: " + name;
    }
}