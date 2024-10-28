import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsLibrary {
    /**
     * Calculates the mean (average) of a list of integers.
     * @param a an ArrayList of integers.
     * @return the mean of the list as a double.
     * @throws NullPointerException if the provided list is null.
     */
    public double findMean(ArrayList<Integer> a) {
        if (a == null) {
            throw new NullPointerException("Input list cannot be null.");
        }

        if (a.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;  // Changed to double to prevent integer overflow
        for (int singleNum : a) {
            sum += singleNum;
        }
        
        return sum / a.size();
    }

    /**
     * Finds the median of a list of integers.
     * If the list has an odd number of elements, the median is the middle element.
     * If the list has an even number of elements, the median is the average of the two middle elements.
     * @param a an ArrayList of integers
     * @return the median of the list as a double. 
     * @throws NullPointerException if the provided list is null.
     */
    public double findMedian(ArrayList<Integer> a) {
        if (a == null) {
            throw new NullPointerException("Input list cannot be null.");
        }
        
        if (a.isEmpty()) {
            return 0.0;
        }

        ArrayList<Integer> sorted = new ArrayList<>(a);  // Create a copy to avoid modifying original
        Collections.sort(sorted);
        int n = sorted.size();

        if (n % 2 != 0) {  // odd length
            return sorted.get(n / 2);
        } else {  // even length
            double midR = sorted.get(n / 2);
            double midL = sorted.get(n / 2 - 1);
            return (midR + midL) / 2.0;  // Use floating-point division
        }
    }

    /**
     * Calculates the mode of an array of integers.
     * If multiple values have the same highest frequency, returns the smallest one.
     * @param a ArrayList of integers to find mode of
     * @return The most frequent value in the array
     * @throws IllegalArgumentException if the array is empty or null
     */
    public int findMode(ArrayList<Integer> a) {
        if (a == null || a.isEmpty()) {
            throw new IllegalArgumentException("Array cannot be empty or null");
        }

        Map<Integer, Integer> frequencies = new HashMap<>();
        
        for (int value : a) {
            frequencies.merge(value, 1, Integer::sum);
        }
        
        int maxFrequency = 0;
        int mode = a.get(0);
        
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() > maxFrequency || 
                (entry.getValue() == maxFrequency && entry.getKey() < mode)) {
                maxFrequency = entry.getValue();
                mode = entry.getKey();
            }
        }
        
        return mode;
    }

    /**
     * Calculates the variance of a list of integers using population variance formula.
     * @param a an ArrayList of integers.
     * @return the variance of the list as a double.
     * @throws NullPointerException if the provided list is null.
     */
    public double findVariance(ArrayList<Integer> a) {
        if (a == null) {
            throw new NullPointerException("Input list cannot be null.");
        }
        
        if (a.size() < 2) {
            return 0.0;
        }

        double mean = findMean(a);
        double sumSquaredDeviations = 0.0;

        for (int singleNum : a) {
            double deviation = singleNum - mean;
            sumSquaredDeviations += deviation * deviation;
        }

        return sumSquaredDeviations / (a.size() - 1);  // Using n-1 for sample variance
    }

    /**
     * Calculates the standard deviation of an array of integers.
     * Uses the sample standard deviation formula: σ = √[Σ(x - μ)² / (n-1)]
     * 
     * @param a ArrayList of integers 
     * @return The standard deviation
     * @throws IllegalArgumentException if the array is empty or has only one element
     */
    public double standardDeviation(ArrayList<Integer> a) {
        if (a == null || a.size() < 2) {
            throw new IllegalArgumentException("Array must contain at least two values");
        }

        return Math.sqrt(findVariance(a));
    }

    /**
     * Calculates combinations (n choose r), representing the number of ways to select r items 
     * from a set of n items where order doesn't matter. This is also known as the binomial
     * coefficient.
     * 
     * The formula used is: C(n,r) = n! / (r! * (n-r)!)
     *
     * @param n The size of the set (must be non-negative)
     * @param r The size of the subset to choose (must be between 0 and n inclusive)
     * @return The number of combinations
     * @throws IllegalArgumentException if n < 0 or if r is not between 0 and n inclusive
     */
    public double combinationSolver(int n, int r) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        if (r < 0 || r > n) {
            throw new IllegalArgumentException("r must be between 0 and n");
        }
        
        BigInteger nFact = factorial(n);
        BigInteger rFact = factorial(r);
        BigInteger nMinusRFact = factorial(n - r);
        
        return nFact.divide(rFact.multiply(nMinusRFact)).doubleValue();
    }

    /**
     * Generates all possible permutations of the given array of integers.
     * @param a The input array of integers to permute
     * @return A list containing all possible permutations of the input array
     * @throws NullPointerException if the input array is null
     */
    public List<List<Integer>> findPermutations(int[] a) {
        if (a == null) {
            throw new NullPointerException("Input array cannot be null");
        }
        
        List<List<Integer>> result = new ArrayList<>();
        if (a.length == 0) {
            return result;
        }
        
        // Create a copy of the input array to avoid modifying it
        int[] workingArray = a.clone();
        
        // Init first permutation
        List<Integer> firstPerm = new ArrayList<>();
        for (int num : workingArray) {
            firstPerm.add(num);
        }
        result.add(firstPerm);
        
        // Generate permutations using Heap's algorithm
        int[] indexes = new int[workingArray.length];
        int i = 0;
        while (i < workingArray.length) {
            if (indexes[i] < i) {
                int swapIndex = (i % 2 == 0) ? 0 : indexes[i];
                int tmp = workingArray[swapIndex];
                workingArray[swapIndex] = workingArray[i];
                workingArray[i] = tmp;
                
                List<Integer> newPerm = new ArrayList<>();
                for (int num : workingArray) {
                    newPerm.add(num);
                }
                result.add(newPerm);
                
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }
        
        return result;
    }

    /**
     * Calculates the factorial of a non-negative integer.
     * Factorial is the product of all positive integers less than or equal to n.
     * @param n The number to calculate factorial for (must be non-negative)
     * @return The factorial of n as a BigInteger
     * @throws IllegalArgumentException if n is negative
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }

        BigInteger nBig = BigInteger.valueOf(n);
        BigInteger result = BigInteger.ONE;
        
        for (BigInteger i = BigInteger.ONE; i.compareTo(nBig) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        
        return result;
    }

    /**
     * Calculates the probability mass function for a binomial distribution.
     * The binomial distribution models the probability of achieving exactly y successes
     * in n trials, where each trial has a probability p of success.
     *
     * @param n The total number of trials (must be non-negative)
     * @param y The number of successes (must be between 0 and n inclusive)
     * @param p The probability of success on each trial (must be between 0 and 1)
     * @return The probability of exactly y successes in n trials
     * @throws IllegalArgumentException if any parameters are outside their valid ranges
     */
    public double binDist(int n, int y, double p) {
        if (n < 0 || y < 0 || y > n) {
            throw new IllegalArgumentException("Invalid n or y values");
        }
        if (p < 0 || p > 1) {
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        }
        
        return combinationSolver(n,y) * Math.pow(p, y) * Math.pow(1-p, n-y);
    }

    /**
     * Calculates the geometric distribution probability mass function.
     * The geometric distribution models the number of failures before the first success
     * in a sequence of independent Bernoulli trials.
     * 
     * @param p probability of success on each trial (0 < p <= 1)
     * @param k number of failures before success (k >= 0)
     * @return probability of exactly k failures before first success
     * @throws IllegalArgumentException if p is not in (0,1] or k < 0
     */
    public double geometricDist(double p, int k) {
        if (p <= 0 || p > 1) {
            throw new IllegalArgumentException("Probability p must be in range (0,1]");
        }
        if (k < 0) {
            throw new IllegalArgumentException("Number of failures k must be non-negative");
        }

        // Check for potential numerical underflow
        if (k > -Math.log(Double.MIN_VALUE) / -Math.log1p(-p)) {
            return 0.0;
        }

        return p * Math.pow(1 - p, k);
    }

    /**
     * Returns the union of two lists. 
     * @param s1 the first list, must not be null
     * @param s2 the second list, must not be null
     * @return a new list containing the union of s1 and s2
     * @throws NullPointerException if either s1 or s2 is null
     */
    public static <T> List<T> union(List<T> s1, List<T> s2) {
        if (s1 == null || s2 == null) {
            throw new NullPointerException("Input lists cannot be null.");
        }
        
        List<T> result = new ArrayList<>(s1);
        for (T item : s2) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Returns the intersection of two lists.
     * @param s1 the first list, must not be null
     * @param s2 the second list, must not be null
     * @return a new list containing the intersection of s1 and s2
     * @throws NullPointerException if either s1 or s2 is null
     */
    public static <T> List<T> intersection(List<T> s1, List<T> s2) {
        if (s1 == null || s2 == null) {
            throw new NullPointerException("Input lists cannot be null.");
        }
        
        List<T> result = new ArrayList<>();
        for (T item : s1) {
            if (s2.contains(item) && !result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Returns the difference of two lists (s1 - s2). 
     * @param s1 the first list, must not be null
     * @param s2 the second list, must not be null
     * @return a new list containing the difference of s1 and s2
     * @throws NullPointerException if either s1 or s2 is null
     */
    public static <T> List<T> difference(List<T> s1, List<T> s2) {
        if (s1 == null || s2 == null) {
            throw new NullPointerException("Input lists cannot be null.");
        }
        
        List<T> result = new ArrayList<>();
        for (T item : s1) {
            if (!s2.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Calculates the probability of an event using the sample-point method.
     * The probability is calculated as the ratio of the number of favorable outcomes
     * to the total number of possible outcomes.
     * P = (Number of favorable outcomes) / (Total number of possible outcomes)
     * @param sampleSpace the list of all possible outcomes, must not be null
     * @param event the list of favorable outcomes for the event, must not be null
     * @return the probability of the event as a double 0 <= P <= 1
     * @throws NullPointerException if either sampleSpace or event is null
     */
    public static <T> double calculateProbability(List<T> sampleSpace, List<T> event) {
        if (sampleSpace == null || event == null) {
            throw new NullPointerException("Input lists cannot be null.");
        }
        
        if (sampleSpace.isEmpty()) {
            return 0;
        }

        // Calculate the number of favorable outcomes that are in the sample space
        List<T> favorableOutcomes = intersection(sampleSpace, event);

        return (double) favorableOutcomes.size() / sampleSpace.size();
    }

	    /**
	     * Calculates the conditional probability of event A given event B.
	     * The conditional probability P(A|B) is calculated as:
	     * P(A|B) = P(A ∩ B) / P(B)
	     * @param sampleSpace the list of all possible outcomes, must not be null
	     * @param eventA the list representing event A, must not be null
	     * @param eventB the list representing event B, must not be null
	     * @return the conditional probability of A given B as a double, or 0 if B has zero probability
	     * @throws NullPointerException if any of the input lists are null
	     */
	    public static <T> double calculateConditionalProbability(List<T> sampleSpace, List<T> eventA, List<T> eventB) {
	        if (sampleSpace == null || eventA == null || eventB == null) {
	            throw new NullPointerException("Input lists cannot be null.");
	        }

	        if (sampleSpace.isEmpty() || eventB.isEmpty()) {
	            return 0;  // Probability is 0 if sample space or event B is empty
	        }

	        // Calculate P(B)
	        List<T> eventBInSampleSpace = intersection(sampleSpace, eventB);
	        double pB = (double) eventBInSampleSpace.size() / sampleSpace.size();

	        if (pB == 0.0) {
	            return 0; // Return 0 if P(B) is 0 to avoid division by zero
	        }

	        // Calculate P(A ∩ B) using intersection of eventBInSampleSpace with eventA
	        List<T> eventAandB = intersection(eventBInSampleSpace, eventA);
	        double pAandB = (double) eventAandB.size() / sampleSpace.size();

	        // Calculate conditional probability P(A|B)
	        return pAandB / pB;
	    }
	}

