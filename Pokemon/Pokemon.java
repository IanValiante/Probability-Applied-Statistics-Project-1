/**
 * Represents a Pokémon card in the card game. A Pokémon card has a name, hit points,
 * and a set of moves it can perform. The card can take damage, heal, and has a textual representation.
 */
class Pokemon extends Card {

    private int hitPoints;
    private Move[] moves;

    /**
     * Constructs a Pokémon card with the specified name, hit points, and moves.
     *
     * @param name      The name of the Pokémon.
     * @param hitPoints The initial hit points of the Pokémon.
     * @param moves     An array of moves the Pokémon can perform.
     */
    public Pokemon(String name, int hitPoints, Move[] moves) {
        super(name);
        this.hitPoints = hitPoints;
        this.moves = moves;
    }

    /**
     * Returns the current hp of the Pokémon.
     *
     * @return The current hit points.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Reduces the Pokémon's hit points by a specified damage amount. Hit points cannot
     * go below zero.
     *
     * @param damage The amount of damage to apply.
     */
    public void takeDamage(int damage) {
        this.hitPoints -= damage;
        if (hitPoints < 0) hitPoints = 0; // Prevent negative HP
    }

    /**
     * Returns the moves available to this Pokémon.
     *
     * @return An array of Move objects representing the Pokémon's moves.
     */
    public Move[] getMoves() {
        return moves;
    }

    /**
     * Heals the Pokémon by a specified amount, increasing its hit points.
     *
     * @param healingAmount The amount of hit points to add.
     */
    public void heal(int healingAmount) {
        this.hitPoints += healingAmount;
    }

    /**
     * Returns a string representation of the Pokémon card, including its name and current hit points.
     *
     * @return A string representing the Pokémon and its hit points.
     */
    @Override
    public String toString() {
        return name + " (HP: " + hitPoints + ")";
    }
}