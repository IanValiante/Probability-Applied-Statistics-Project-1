import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class StatsLibraryTestRunner {
    public static void main(String[] args) {
        System.out.println("Running StatsLibrary Tests...\n");
        
        Result result = JUnitCore.runClasses(StatsLibraryTest.class);
        
        // Print summary header
        System.out.println("=== Test Execution Summary ===");
        System.out.println("Total tests run: " + result.getRunCount());
        System.out.println("Tests passed: " + (result.getRunCount() - result.getFailureCount()));
        System.out.println("Tests failed: " + result.getFailureCount());
        System.out.println("Time taken: " + result.getRunTime() + "ms");
        
        // If there are failures, print them in detail
        if (result.getFailureCount() > 0) {
            System.out.println("\n=== Failed Tests Details ===");
            for (Failure failure : result.getFailures()) {
                System.out.println("\nTest failed: " + failure.getTestHeader());
                System.out.println("Error message: " + failure.getMessage());
                System.out.println("Stack trace:");
                System.out.println(failure.getTrace());
            }
        }
        
        // Print final status
        System.out.println("\n=== Final Status ===");
        if (result.wasSuccessful()) {
            System.out.println("ALL TESTS PASSED ✓");
        } else {
            System.out.println("SOME TESTS FAILED ✗");
        }
    }
}