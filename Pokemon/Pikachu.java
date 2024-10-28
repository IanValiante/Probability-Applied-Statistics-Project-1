/**
 * Represents a Pikachu Pokémon card in the card game. Pikachu is a specific Pokémon 
 * with predefined attributes, including its name, hit points, and moves.
 */

    
class Pikachu extends Pokemon {
	/**
     * Constructs a Pikachu Pokémon card with the name "Pikachu", 60 hit points,
     * and two moves: "Thunder Shock" and "Quick Attack".
     */
    public Pikachu() {
        super("Pikachu", 60, new Move[]{
            new Move("Thunder Shock", 20), //Move 1: Thunder Shock
            new Move("Quick Attack", 10) //Move 2: Quick Attack
        });
    }
}
