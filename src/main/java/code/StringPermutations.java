package code;

import java.util.*;

/**
 * StringPermutations generates all permutations of a given string.
 * Supports both recursive and iterative approaches.
 * Users can choose to include or exclude duplicate permutations.
 * Interactive input is supported via the main method.
 *
 * Features & Specs:
 * 1. Recursive permutation generation.
 * 2. Iterative permutation generation using Heap's algorithm.
 * 3. Option to include or exclude duplicate permutations.
 * 4. Error handling for null or empty strings.
 * 5. Interactive main program to demonstrate both approaches.
 * 6. Counts total permutations for each approach.
 * 7. Modular design with helper methods for recursion and swapping.
 * 8. Time complexity consideration: Recursive approach is O(n!), iterative approach also O(n!)
 *    for generating all permutations of a string of length n.
 *
 * Author: Ushba
 * Version: 1.2
 */
public class StringPermutations {

    /**
     * Recursively generates all permutations of a string.
     *
     * @param str the input string (cannot be null)
     * @param allowDuplicates true to include duplicate permutations, false to exclude
     * @return List of permutations
     * @throws IllegalArgumentException if input string is null
     */
    public static List<String> generatePermutationsRecursive(String str, boolean allowDuplicates) {
        if (str == null) throw new IllegalArgumentException("Input string cannot be null");
        if (str.isEmpty()) return Collections.singletonList(""); // edge case for empty string

        Set<String> resultSet = new LinkedHashSet<>();
        permuteRecursive("", str, resultSet);

        if (allowDuplicates) {
            return new ArrayList<>(resultSet);
        } else {
            return new ArrayList<>(new LinkedHashSet<>(resultSet)); // removes duplicates
        }
    }

    /**
     * Helper method for recursive permutation generation.
     *
     * @param prefix current prefix built
     * @param remaining remaining characters
     * @param resultSet set to store permutations
     */
    private static void permuteRecursive(String prefix, String remaining, Set<String> resultSet) {
        int n = remaining.length();
        if (n == 0) {
            resultSet.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permuteRecursive(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1),
                        resultSet);
            }
        }
    }

    /**
     * Generates all permutations of a string iteratively using Heap's algorithm.
     *
     * @param str the input string (cannot be null)
     * @param allowDuplicates true to include duplicate permutations, false to exclude
     * @return List of permutations
     * @throws IllegalArgumentException if input string is null
     */
    public static List<String> generatePermutationsIterative(String str, boolean allowDuplicates) {
        if (str == null) throw new IllegalArgumentException("Input string cannot be null");
        if (str.isEmpty()) return Collections.singletonList("");

        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<>();
        int n = chars.length;
        int[] c = new int[n]; // control array for Heap's algorithm

        result.add(new String(chars));
        int i = 0;

        while (i < n) {
            if (c[i] < i) {
                if (i % 2 == 0) swap(chars, 0, i);
                else swap(chars, c[i], i);

                result.add(new String(chars));
                c[i]++;
                i = 0;
            } else {
                c[i] = 0;
                i++;
            }
        }

        if (!allowDuplicates) {
            return new ArrayList<>(new LinkedHashSet<>(result));
        }
        return result;
    }

    /**
     * Helper method to swap two characters in a char array.
     *
     * @param arr character array
     * @param i index i
     * @param j index j
     */
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Interactive main method to demonstrate recursive and iterative string permutations.
     *
     * Features:
     * - Prompts user for input string
     * - Prompts user to allow or disallow duplicate permutations
     * - Prints all recursive permutations and their count
     * - Prints all iterative permutations and their count
     * - Handles empty string input
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the string to permute: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Error: Input string cannot be empty.");
            return;
        }

        System.out.print("Allow duplicate permutations? (true/false): ");
        boolean allowDuplicates = Boolean.parseBoolean(scanner.nextLine().trim());

        // Recursive permutations
        System.out.println("\n--- Recursive Permutations ---");
        List<String> recursiveResult = generatePermutationsRecursive(input, allowDuplicates);
        recursiveResult.forEach(System.out::println);
        System.out.println("Total recursive permutations: " + recursiveResult.size());

        // Iterative permutations
        System.out.println("\n--- Iterative Permutations ---");
        List<String> iterativeResult = generatePermutationsIterative(input, allowDuplicates);
        iterativeResult.forEach(System.out::println);
        System.out.println("Total iterative permutations: " + iterativeResult.size());
    }
}
