/**
 * Represents a Charmander Pokémon card in the card game. Charmander is a specific Pokémon 
 * with predefined attributes, including its name, hit points, and moves.
 */
class Charmander extends Pokemon {

    /**
     * Constructs a Charmander Pokémon card with the name "Charmander", 50 hit points,
     * and two moves: "Ember" and "Scratch".
     */
    public Charmander() {
        super("Charmander", 50, new Move[]{
            new Move("Ember", 25),   // Move 1: Ember
            new Move("Scratch", 10)  // Move 2: Scratch
        });
    }
}