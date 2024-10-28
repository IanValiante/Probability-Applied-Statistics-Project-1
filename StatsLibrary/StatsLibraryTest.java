import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatsLibraryTest {
    private StatsLibrary stats;
    private ArrayList<Integer> testList;
    
    @Before
    public void setUp() {
        stats = new StatsLibrary();
        testList = new ArrayList<>();
    }
    
    @Test
    public void testFindMean() {
        // Test empty list
        assertEquals("Mean of empty list should be 0", 0.0, stats.findMean(testList), 0.001);
        
        // Test single element
        testList.add(5);
        assertEquals("Mean of [5] should be 5", 5.0, stats.findMean(testList), 0.001);
        
        // Test multiple elements
        testList.addAll(Arrays.asList(10, 15, 20, 25));
        assertEquals("Mean of [5,10,15,20,25] should be 15", 15.0, stats.findMean(testList), 0.001);
    }
    
    @Test
    public void testFindMeanNull() {
        try {
            stats.findMean(null);
            fail("Should have thrown NullPointerException");
        } catch (NullPointerException e) {
            // Test passed
            assertTrue(true);
        }
    }
    
    @Test
    public void testFindMedian() {
        // Test empty list
        assertEquals("Median of empty list should be 0", 0.0, stats.findMedian(testList), 0.001);
        
        // Test odd number of elements
        testList.addAll(Arrays.asList(1, 3, 2));
        assertEquals("Median of [1,3,2] should be 2", 2.0, stats.findMedian(testList), 0.001);
        
        // Test even number of elements
        testList.add(4);
        assertEquals("Median of [1,2,3,4] should be 2.5", 2.5, stats.findMedian(testList), 0.001);
    }
    
    @Test
    public void testFindMode() {
        // Test single element
        testList.add(1);
        assertEquals("Mode of [1] should be 1", 1, stats.findMode(testList));
        
        // Test multiple elements with clear mode
        testList.addAll(Arrays.asList(2, 2, 3, 4));
        assertEquals("Mode of [1,2,2,3,4] should be 2", 2, stats.findMode(testList));
    }
    
    @Test
    public void testFindModeEmpty() {
        try {
            stats.findMode(new ArrayList<>());
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Test passed
            assertTrue(true);
        }
    }
    
    @Test
    public void testVariance() {
        // Test list with no variance
        testList.addAll(Arrays.asList(2, 2, 2));
        assertEquals("Variance of [2,2,2] should be 0", 0.0, stats.findVariance(testList), 0.001);
        
        // Test normal case
        testList.clear();
        testList.addAll(Arrays.asList(1, 2, 3, 4, 5));
        assertEquals("Variance of [1,2,3,4,5] should be 2.5", 2.5, stats.findVariance(testList), 0.001);
    }
    
    @Test
    public void testStandardDeviation() {
        // Test list with no variation
        testList.addAll(Arrays.asList(2, 2, 2));
        assertEquals("StdDev of [2,2,2] should be 0", 0.0, stats.standardDeviation(testList), 0.001);
        
        // Test normal case
        testList.clear();
        testList.addAll(Arrays.asList(2, 4, 4, 4, 5, 5, 7, 9));
        assertEquals("StdDev should be calculated correctly", 2.13, stats.standardDeviation(testList), 0.01);
    }
    
    @Test
    public void testCombinationSolver() {
        assertEquals("C(5,3) should be 10", 10.0, stats.combinationSolver(5, 3), 0.001);
        assertEquals("C(10,5) should be 252", 252.0, stats.combinationSolver(10, 5), 0.001);
    }
    
    @Test
    public void testBinDist() {
        assertEquals("P(X=3) in Bin(5,0.5) should be 0.3125", 
                    0.3125, stats.binDist(5, 3, 0.5), 0.001);
    }
    
    @Test
    public void testGeometricDist() {
        assertEquals("P(X=2) with p=0.5 should be 0.125", 
                    0.125, stats.geometricDist(0.5, 2), 0.001);
    }
    
    @Test
    public void testSetOperations() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(2, 3, 4);
        
        // Test union
        List<Integer> union = StatsLibrary.union(list1, list2);
        assertTrue(union.containsAll(Arrays.asList(1, 2, 3, 4)));
        assertEquals(4, union.size());
        
        // Test intersection
        List<Integer> intersection = StatsLibrary.intersection(list1, list2);
        assertTrue(intersection.containsAll(Arrays.asList(2, 3)));
        assertEquals(2, intersection.size());
        
        // Test difference
        List<Integer> difference = StatsLibrary.difference(list1, list2);
        assertTrue(difference.contains(1));
        assertEquals(1, difference.size());
    }
    
    @Test
    public void testProbabilityCalculations() {
        List<Integer> sampleSpace = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> eventA = Arrays.asList(2, 4, 6); // Even numbers
        List<Integer> eventB = Arrays.asList(4, 5, 6); // Numbers greater than 3
        
        assertEquals("P(A) should be 0.5", 
                    0.5, StatsLibrary.calculateProbability(sampleSpace, eventA), 0.001);
                    
        assertEquals("P(A|B) should be 0.667", 
                    0.667, StatsLibrary.calculateConditionalProbability(sampleSpace, eventA, eventB), 0.001);
    }
    
    @Test
    public void testPermutations() {
        int[] input = {1, 2, 3};
        List<List<Integer>> permutations = stats.findPermutations(input);
        assertEquals("Number of permutations should be 6", 6, permutations.size());
        
        // Check if all permutations are unique
        for (int i = 0; i < permutations.size(); i++) {
            for (int j = i + 1; j < permutations.size(); j++) {
                assertFalse("Permutations should be unique", 
                           permutations.get(i).equals(permutations.get(j)));
            }
        }
    }
}