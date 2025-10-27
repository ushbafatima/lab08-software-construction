package test;

import code.StringPermutations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StringPermutationsTest {

    @Test
    public void testRecursivePermutationsNormal() {
        String input = "abc";
        List<String> result = StringPermutations.generatePermutationsRecursive(input, true);
        assertEquals(6, result.size(), "Recursive permutation count for 'abc' should be 6");
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("acb"));
        assertTrue(result.contains("bac"));
        assertTrue(result.contains("bca"));
        assertTrue(result.contains("cab"));
        assertTrue(result.contains("cba"));
    }

    @Test
    public void testIterativePermutationsNormal() {
        String input = "abc";
        List<String> result = StringPermutations.generatePermutationsIterative(input, true);
        assertEquals(6, result.size(), "Iterative permutation count for 'abc' should be 6");
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("acb"));
        assertTrue(result.contains("bac"));
        assertTrue(result.contains("bca"));
        assertTrue(result.contains("cab"));
        assertTrue(result.contains("cba"));
    }

    @Test
    public void testAllowDuplicates() {
        String input = "aab";
        List<String> resultAllowDup = StringPermutations.generatePermutationsRecursive(input, true);
        List<String> resultNoDup = StringPermutations.generatePermutationsRecursive(input, false);

        assertTrue(resultAllowDup.size() > resultNoDup.size(),
                "Duplicates allowed should produce more permutations");
    }

    @Test
    public void testEmptyString() {
        String input = "";
        List<String> resultRecursive = StringPermutations.generatePermutationsRecursive(input, true);
        List<String> resultIterative = StringPermutations.generatePermutationsIterative(input, true);

        assertEquals(1, resultRecursive.size(), "Empty string should return list of size 1 (empty string) in recursive");
        assertEquals("", resultRecursive.get(0), "Empty string result should be '' in recursive");

        assertEquals(1, resultIterative.size(), "Empty string should return list of size 1 (empty string) in iterative");
        assertEquals("", resultIterative.get(0), "Empty string result should be '' in iterative");
    }

    @Test
    public void testSingleCharacter() {
        String input = "x";
        List<String> resultRecursive = StringPermutations.generatePermutationsRecursive(input, true);
        List<String> resultIterative = StringPermutations.generatePermutationsIterative(input, true);

        assertEquals(1, resultRecursive.size(), "Single character string should have 1 permutation recursive");
        assertEquals("x", resultRecursive.get(0));

        assertEquals(1, resultIterative.size(), "Single character string should have 1 permutation iterative");
        assertEquals("x", resultIterative.get(0));
    }

    @Test
    public void testNullInputRecursive() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringPermutations.generatePermutationsRecursive(null, true);
        });
        assertEquals("Input string cannot be null", exception.getMessage());
    }

    @Test
    public void testNullInputIterative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringPermutations.generatePermutationsIterative(null, true);
        });
        assertEquals("Input string cannot be null", exception.getMessage());
    }

    @Test
    public void testRecursiveAndIterativeConsistency() {
        String input = "abc";
        List<String> recursiveResult = StringPermutations.generatePermutationsRecursive(input, true);
        List<String> iterativeResult = StringPermutations.generatePermutationsIterative(input, true);

        assertEquals(recursiveResult.size(), iterativeResult.size(), "Recursive and iterative results should match in size");
        assertTrue(recursiveResult.containsAll(iterativeResult), "Recursive result should contain all iterative permutations");
    }

    @Test
    public void testDuplicatesHandlingIterative() {
        String input = "aab";
        List<String> allowDup = StringPermutations.generatePermutationsIterative(input, true);
        List<String> noDup = StringPermutations.generatePermutationsIterative(input, false);

        assertTrue(allowDup.size() > noDup.size(), "Duplicates allowed should produce more permutations in iterative");
    }
}
