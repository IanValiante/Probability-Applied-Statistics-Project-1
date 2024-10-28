import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The CardGame class simulates a card game involving Pokémon cards, Trainer cards, and Energy cards.
 * It manages the game flow, including drawing hands, checking for Pokémon in hand, and handling turns.
 */
public class CardGame {
    private ArrayList<Card> deck;
    private Random rand;

    /**
     * Constructs a new CardGame instance with an empty deck and a random number generator.
     */
    public CardGame() {
        deck = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Retrieves the current deck of cards.
     *
     * @return the deck of cards
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Draws a hand of 7 cards from the deck for the specified player.
     * Cards are randomly selected from the deck and removed from it.
     *
     * @param player the player who will receive the drawn cards
     */
    public void drawHand(Player player) {
        for (int i = 0; i < 7; i++) {
            if (!deck.isEmpty()) {
                int cardToTakeIndex = rand.nextInt(deck.size());
                player.drawCard(deck.get(cardToTakeIndex));
                deck.remove(cardToTakeIndex);
            }
        }
    }

    /**
     * Checks if the specified player has any Pokémon in their hand.
     *
     * @param player the player to check
     * @return true if the player has Pokémon in hand; false otherwise
     */
    public boolean checkHand(Player player) {
        return player.hasPokemonInHand(); 
    }

    /**
     * Simulates a specified number of runs to calculate the percentage of reshuffles
     * needed to ensure a player has at least one Pokémon in their hand.
     *
     * @param runs The number of simulations to run
     * @param numOfPokemon The number of Pokémon to fill the deck with
     * @return the percentage of runs in which the player had at least one Pokémon in hand
     */
    public double howManyReshufflesPerc(int runs, int numOfPokemon) {
        boolean hasPokemon = false;
        int count = 0;
        for (int i = 0; i < runs; i++) {
            fillDeck(numOfPokemon);
            Player tempPlayer = new Player("Test");
            drawHand(tempPlayer);
            if (checkHand(tempPlayer)) {
                hasPokemon = true;
                count++;
            }
            deck.clear();
        }
        return (double) count / (double) runs * 100;
    }

    /**
     * Runs a Monte Carlo simulation for a range of Pokémon counts to see how many reshuffles
     * are required to ensure a player has Pokémon in their hand.
     *
     * @param runs the number of simulations to run for each Pokémon count
     */
    public void monteCarlo(int runs) {
        for (int j = 1; j <= 60; j++) {
            System.out.println("With " + j + " Pokémon in deck: " + howManyReshufflesPerc(runs, j));
        }
    }

    /**
     * Fills the deck with a specified number of Pokémon and additional Energy and Trainer cards.
     *
     * @param howManyPokemon the number of Pokémon to add to the deck
     */
    public void fillDeck(int howManyPokemon) {
        // Add Pokémon to the deck
        for (int j = 0; j < howManyPokemon; j++) {
            int pick = rand.nextInt(4); // Assuming 4 different Pokémon classes
            switch (pick) {
                case 0:
                    deck.add(new Bulbasaur());
                    break;
                case 1:
                    deck.add(new Squirtle());
                    break;
                case 2:
                    deck.add(new Charmander());
                    break;
                case 3:
                    deck.add(new Pikachu());
                    break;
            }
        }

        // Fill the rest of the deck with Energy and Trainer cards
        for (int i = 0; i < 60 - howManyPokemon; i++) {
            int pick = rand.nextInt(4); // 3 options: Energy, DrawTrainer, HealTrainer
            switch (pick) {
                case 0:
                    deck.add(new Energy("Energy Card " + (i + 1)));
                    break;
                case 1:
                    deck.add(new DrawTrainer("Draw Trainer " + (i + 1), 1));
                    break;
                case 2:
                    deck.add(new HealTrainer("Heal Trainer " + (i + 1), 20));
                    break;
            }
        }
    }

    /**
     * Starts the game, prompting for player names and managing the game loop.
     * Handles player turns until the game is over.
     */
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        
        // Fill the deck before starting the game
        System.out.print("Enter amount of pokemon in deck: ");
        int monAmt = scanner.nextInt();
        scanner.nextLine();
        fillDeck(monAmt); // fill with amt of pokemon

        // Ensure that the deck has enough cards
        if (deck.isEmpty()) {
            System.out.println("The deck is empty! Cannot start the game.");
            scanner.close();
            return;
        }

        System.out.print("Enter Player 1 name: ");
        Player player1 = new Player(scanner.nextLine());
        drawHand(player1);
        player1.drawPokemon(); // Set the first Pokémon as active

        System.out.print("Enter Player 2 name: ");
        Player player2 = new Player(scanner.nextLine());
        drawHand(player2);
        player2.drawPokemon(); // Set the first Pokémon as active

        // Game Loop
        boolean gameOngoing = true;
        while (gameOngoing) {
            // Player 1 turn
            playerTurn(player1, player2, scanner);
            if (!player2.hasActivePokemon()) {
                System.out.println(player2.getName() + " has no Pokémon left!");
                gameOngoing = false;
                break;
            }

            // Player 2 turn
            playerTurn(player2, player1, scanner);
            if (!player1.hasActivePokemon()) {
                System.out.println(player1.getName() + " has no Pokémon left!");
                gameOngoing = false;
                break;
            }
        }
        scanner.close();
        System.out.println("Game Over!");
    }

    /**
     * Handles the actions of the current player's turn, including selecting a move for the active Pokémon
     * or using a Trainer card. Checks if the opponent's Pokémon has fainted after the turn.
     *
     * @param currentPlayer the player whose turn it is
     * @param opponent      the opponent player
     * @param scanner       the Scanner object for user input
     */
    private void playerTurn(Player currentPlayer, Player opponent, Scanner scanner) {
        System.out.println(currentPlayer.getName() + "'s turn.");
        System.out.println(currentPlayer.toString()); // Display player hand and active Pokémon

        System.out.println("Select an action:");
        System.out.println("0: Use Pokémon Move");
        System.out.println("1: Use Trainer Card");

        int actionChoice = scanner.nextInt();
        if (actionChoice == 0) {
            if (currentPlayer.hasActivePokemon()) {
                System.out.println("Select a move for " + currentPlayer.getActivePokemon().getName() + ":");
                Move[] moves = currentPlayer.getActivePokemon().getMoves();
                for (int i = 0; i < moves.length; i++) {
                    System.out.println(i + ": " + moves[i]);
                }
                int moveIndex = scanner.nextInt();
                if (moveIndex >= 0 && moveIndex < moves.length) {
                    Move selectedMove = moves[moveIndex];
                    opponent.getActivePokemon().takeDamage(selectedMove.getDamage());
                    System.out.println(currentPlayer.getName() + " uses " + selectedMove.getName() +
                            " dealing " + selectedMove.getDamage() + " damage to " +
                            opponent.getActivePokemon().getName() + ".");
                } else {
                    System.out.println("Invalid move selection.");
                }
            } else {
                System.out.println(currentPlayer.getName() + " has no active Pokémon left!");
            }
        } else if (actionChoice == 1) {
            System.out.println("Select a Trainer card to use:");
            for (int i = 0; i < currentPlayer.getHand().size(); i++) {
                Card card = currentPlayer.getHand().get(i);
                if (card instanceof Trainer) {
                    System.out.println(i + ": " + card);
                }
            }
            int trainerIndex = scanner.nextInt();
            currentPlayer.useTrainerCard(trainerIndex, this);
        } else {
            System.out.println("Invalid action selection.");
        }

        // Check for active Pokémon after the turn
        if (opponent.getActivePokemon().getHitPoints() <= 0) {
            System.out.println(opponent.getActivePokemon().getName() + " has fainted!");
            opponent.setActivePokemon(null); // Remove fainted Pokémon
            if (opponent.hasPokemonInHand()) {
                opponent.drawPokemon(); // Draw a new Pokémon
            }
        }
    }
}