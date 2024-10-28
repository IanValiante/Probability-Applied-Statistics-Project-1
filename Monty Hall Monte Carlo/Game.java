import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Simulates the Monty Hall problem, a probability puzzle based on a game show scenario
 * where a contestant must choose between three doors, one containing a prize.
 */
public class Game {
    /** Random number generator for door selection */
    private final Random rand = new Random();
    
    /**
     * Simulates multiple iterations of the Monty Hall game with a specified strategy.
     * 
     * @param iterations The number of games to simulate
     * @param isSwitching Whether to use the door-switching strategy (true) or stay with initial choice (false)
     * @return The number of games won using the specified strategy
     */
    public int playGame(int iterations, boolean isSwitching) {
        int wins = 0;
        for (int games = 0; games < iterations; games++) {
            ArrayList<Integer> doors = new ArrayList<>();
            doors.add(0);  // Empty door
            doors.add(0);  // Empty door
            doors.add(1);  // Door with prize
            Collections.shuffle(doors);  // Randomize prize location
            
            int initialGuess = rand.nextInt(3);
            
            if (isSwitching) {
                int revealedDoor = 0;
                // Find a door to reveal (must be empty and not the initial guess)
                while (revealedDoor == initialGuess || doors.get(revealedDoor) == 1) {
                    revealedDoor = rand.nextInt(3);
                }
                // Calculate the remaining door to switch to
                int switchedGuess = 3 - initialGuess - revealedDoor;
                if (doors.get(switchedGuess) == 1) {
                    wins++;
                }
            } else {
                if (doors.get(initialGuess) == 1) {
                    wins++;
                }
            }
        }
        return wins;
    }
    
    /**
     * Calculates the win percentages for both staying with the initial door choice
     * and switching doors strategies.
     * 
     * @param iterations The number of games to simulate for each strategy
     * @return An array containing two doubles: [stayWinPercentage, switchWinPercentage]
     */
    public double[] calcWinPerc(int iterations) {
        int stayWins = playGame(iterations, false);
        int switchWins = playGame(iterations, true);
        
        System.out.println("Switch Wins: " + switchWins);
        System.out.println("Stay Wins: " + stayWins);
        
        double stayWinPerc = (double) stayWins / iterations * 100;
        double switchWinPerc = (double) switchWins / iterations * 100;
        
        return new double[] { stayWinPerc, switchWinPerc };
    }
}