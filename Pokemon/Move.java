/**
 * Represents a move that a Pokémon can perform in the card game. Each move has a name
 * and a specified damage amount.
 */
class Move {

    private String name;
    private int damage;

    /**
     * Constructs a Move with the specified name and damage.
     *
     * @param name   The name of the move.
     * @param damage The damage dealt by the move.
     */
    public Move(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    /**
     * Returns the name of the move.
     *
     * @return The name of the move.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the damage dealt by the move.
     *
     * @return The damage amount.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns a string representation of the move, including its name and damage.
     *
     * @return A string representing the move and its damage.
     */
    @Override
    public String toString() {
        return name + " (Damage: " + damage + ")";
    }
}