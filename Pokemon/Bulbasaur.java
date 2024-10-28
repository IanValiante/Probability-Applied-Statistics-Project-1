/**
 * Represents a Bulbasaur Pokémon card in the card game. Bulbasaur is a specific Pokémon 
 * with predefined attributes, including its name, hit points, and moves.
 */
class Bulbasaur extends Pokemon {

    /**
     * Constructs a Bulbasaur Pokémon card with the name "Bulbasaur", 50 hit points,
     * and two moves: "Vine Whip" and "Tackle".
     */
    public Bulbasaur() {
        super("Bulbasaur", 50, new Move[]{
            new Move("Vine Whip", 20),   // Move 1: Vine Whip
            new Move("Tackle", 10)       // Move 2: Tackle
        });
    }
}