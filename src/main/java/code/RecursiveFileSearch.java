package code;

import java.io.File;

/**
 * RecursiveFileSearch is a utility class that provides functionality
 * to search for a file recursively within a directory and its subdirectories.
 *
 * Usage: java RecursiveFileSearch <directory_path> <file_name>
 * Example: java RecursiveFileSearch "C:/Users/Ushba/Documents" "file.txt"
 *
 * Objective: Demonstrates recursion and file system traversal.
 *
 * Author: Ushba
 * Version: 1.0
 */
public class RecursiveFileSearch {

    /**
     * Recursively searches for a file with the specified name within a directory
     * and its subdirectories.
     *
     * @param directory The directory to start the search from
     * @param fileName  The name of the file to search for
     * @return true if the file is found, false otherwise
     */
    public static boolean searchFile(File directory, String fileName) {
        if (directory == null || !directory.exists()) {
            return false;
        }

        File[] files = directory.listFiles();
        if (files == null) return false;

        for (File file : files) {
            if (file.isDirectory()) {
                // Recurse into subdirectory
                if (searchFile(file, fileName)) {
                    return true;
                }
            } else if (file.getName().equalsIgnoreCase(fileName)) {
                System.out.println("File found: " + file.getAbsolutePath());
                return true;
            }
        }
        return false; // File not found in this directory
    }

    /**
     * Main method to execute the recursive file search.
     *
     * @param args Command-line arguments where:
     *             args[0] = directory path to search
     *             args[1] = file name to search for
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RecursiveFileSearch <directory_path> <file_name>");
            return;
        }

        String dirPath = args[0];
        String fileName = args[1];
        File directory = new File(dirPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Error: The specified directory does not exist or is not a directory.");
            return;
        }

        boolean found = searchFile(directory, fileName);
        if (!found) {
            System.out.println("File not found: " + fileName);
        }
    }
}
