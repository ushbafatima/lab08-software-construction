package code;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * RecursiveFileSearch is a utility class that provides functionality
 * to search for one or multiple files recursively within a directory
 * and its subdirectories. Additional features include counting occurrences
 * and case-sensitive/case-insensitive search.
 *
 * Usage: java RecursiveFileSearch <directory_path> <file1,file2,...> [case-sensitive|case-insensitive]
 * Example: java RecursiveFileSearch "C:/Users/Ushba/Documents" "file.txt,example.txt" case-insensitive
 *
 * Author: Ushba
 * Version: 2.0
 */
public class RecursiveFileSearch {

    /**
     * Recursively searches for a single file within a directory and its subdirectories.
     *
     * @param directory The directory to start the search from
     * @param fileName  The name of the file to search for
     * @param caseSensitive Whether the search is case-sensitive
     * @return The number of occurrences of the file found
     */
    public static int searchFile(File directory, String fileName, boolean caseSensitive) {
        int count = 0;

        if (directory == null || !directory.exists()) {
            return 0;
        }

        File[] files = directory.listFiles();
        if (files == null) return 0;

        for (File file : files) {
            if (file.isDirectory()) {
                count += searchFile(file, fileName, caseSensitive);
            } else {
                if (caseSensitive && file.getName().equals(fileName) ||
                        !caseSensitive && file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Searches for multiple files and prints their occurrence counts.
     *
     * @param directory The directory to start the search from
     * @param fileNames List of file names to search for
     * @param caseSensitive Whether the search is case-sensitive
     */
    public static void searchMultipleFiles(File directory, List<String> fileNames, boolean caseSensitive) {
        for (String fileName : fileNames) {
            int occurrences = searchFile(directory, fileName, caseSensitive);
            System.out.println("File \"" + fileName + "\" found " + occurrences + " time(s).");
        }
    }

    /**
     * Main method to execute the enhanced recursive file search.
     *
     * @param args Command-line arguments where:
     *             args[0] = directory path
     *             args[1] = comma-separated file names
     *             args[2] (optional) = "case-sensitive" or "case-insensitive" (default insensitive)
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java RecursiveFileSearch <directory_path> <file1,file2,...> [case-sensitive|case-insensitive]");
            return;
        }

        String dirPath = args[0];
        String[] filesArray = args[1].split(",");
        List<String> fileNames = new ArrayList<>();
        for (String f : filesArray) fileNames.add(f.trim());

        boolean caseSensitive = args.length >= 3 && args[2].equalsIgnoreCase("case-sensitive");

        File directory = new File(dirPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Error: The specified directory does not exist or is not a directory.");
            return;
        }

        searchMultipleFiles(directory, fileNames, caseSensitive);
    }
}
