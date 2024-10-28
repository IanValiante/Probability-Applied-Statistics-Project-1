/**
 * Main class to demonstrate the Monty Hall simulation.
 * This class runs a simulation to calculate the probability of winning a prize
 * with three doors.
 */
public class Main {
	/**
     * The main method that runs the Monty Hall simulation.
     * Creates an instance of Game class and runs the simulation with 1000 iterations.
     *
     * @param args Command line arguments (not used)
     * @throws Exception If an error occurs during execution
     */
    public static void main(String[] args) throws Exception{
        Game game = new Game();
        double[] winPercs = game.calcWinPerc(10000);
        System.out.println("Win percentage from staying: " + winPercs[0] + "%");
        System.out.println("Win percentage from switching: " + winPercs[1] + "%");
    }
}
