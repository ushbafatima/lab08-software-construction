package test;

import code.StringPermutations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for StringPermutations.
 * Covers:
 * - Normal strings
 * - Strings with duplicates
 * - Empty string
 * - Single character
 * - Null input
 * - Recursive and iterative consistency
 * - Duplicate handling
 */
public class StringPermutationsTest {

    @Test
    void testNormalRecursive() {
        List<String> result = StringPermutations.generatePermutationsRecursive("abc", true);
        assertEquals(6, result.size(), "Recursive permutations count should be 6");
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("cba"));
    }

    @Test
    void testNormalIterative() {
        List<String> result = StringPermutations.generatePermutationsIterative("abc", true);
        assertEquals(6, result.size(), "Iterative permutations count should be 6");
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("cba"));
    }

    @Test
    void testDuplicateHandlingRecursive() {
        List<String> withDup = StringPermutations.generatePermutationsRecursive("aab", true);
        List<String> noDup = StringPermutations.generatePermutationsRecursive("aab", false);
        assertTrue(withDup.size() > noDup.size(), "Duplicates allowed should produce more permutations");
    }

    @Test
    void testDuplicateHandlingIterative() {
        List<String> withDup = StringPermutations.generatePermutationsIterative("aab", true);
        List<String> noDup = StringPermutations.generatePermutationsIterative("aab", false);
        assertTrue(withDup.size() > noDup.size(), "Duplicates allowed should produce more permutations in iterative");
    }

    @Test
    void testEmptyString() {
        List<String> recursive = StringPermutations.generatePermutationsRecursive("", true);
        List<String> iterative = StringPermutations.generatePermutationsIterative("", true);

        assertEquals(1, recursive.size());
        assertEquals("", recursive.get(0));

        assertEquals(1, iterative.size());
        assertEquals("", iterative.get(0));
    }

    @Test
    void testSingleCharacter() {
        List<String> recursive = StringPermutations.generatePermutationsRecursive("x", true);
        List<String> iterative = StringPermutations.generatePermutationsIterative("x", true);

        assertEquals(1, recursive.size());
        assertEquals("x", recursive.get(0));

        assertEquals(1, iterative.size());
        assertEquals("x", iterative.get(0));
    }

    @Test
    void testNullInputRecursive() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StringPermutations.generatePermutationsRecursive(null, true));
        assertEquals("Input string cannot be null", ex.getMessage());
    }

    @Test
    void testNullInputIterative() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StringPermutations.generatePermutationsIterative(null, true));
        assertEquals("Input string cannot be null", ex.getMessage());
    }

    @Test
    void testRecursiveIterativeConsistency() {
        List<String> rec = StringPermutations.generatePermutationsRecursive("abc", true);
        List<String> ite = StringPermutations.generatePermutationsIterative("abc", true);

        assertEquals(rec.size(), ite.size());
        assertTrue(rec.containsAll(ite));
    }
}
