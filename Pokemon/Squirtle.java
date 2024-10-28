/**
 * Represents a Squirtle Pokémon card in the card game. Squirtle is a specific Pokémon 
 * with predefined attributes, including its name, hit points, and moves.
 */
class Squirtle extends Pokemon {
	/**
     * Constructs a Squirtle Pokémon card with the name "Squirtle", 50 hit points,
     * and two moves: "Water Gun" and "Bubble".
     */
    public Squirtle() {
        super("Squirtle", 50, new Move[]{
            new Move("Water Gun", 20),   // Move 1: Water Gun
            new Move("Bubble", 10)       // Move 2: Bubble
        });
    }
}