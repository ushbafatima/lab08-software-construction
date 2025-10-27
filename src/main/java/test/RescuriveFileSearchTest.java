package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import code.RecursiveFileSearch;

/**
 * Unit tests for RecursiveFileSearch.
 * Covers:
 * - Single file in root
 * - Single file in subdirectory
 * - Multiple files
 * - Case-sensitive and insensitive search
 * - Multiple occurrences
 * - Empty and null directory
 */
class RecursiveFileSearchTest {

    @TempDir
    Path tempDir; // Temporary directory provided by JUnit

    @Test
    void testSingleFileInRoot() throws IOException {
        Files.createFile(tempDir.resolve("rootFile.txt"));
        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "rootFile.txt", true);
        assertEquals(1, count);
    }

    @Test
    void testSingleFileInSubdirectory() throws IOException {
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(subDir.resolve("subFile.txt"));
        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "subFile.txt", true);
        assertEquals(1, count);
    }

    @Test
    void testMultipleFilesSearch() throws IOException {
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(subDir.resolve("file2.txt"));
        Files.createFile(subDir.resolve("file3.txt"));

        List<String> files = Arrays.asList("file1.txt", "file2.txt", "file3.txt", "fileNotExist.txt");

        for (String f : files) {
            int count = RecursiveFileSearch.searchFile(tempDir.toFile(), f, true);
            assertEquals(f.equals("fileNotExist.txt") ? 0 : 1, count);
        }
    }

    @Test
    void testCaseSensitiveSearch() throws IOException {
        Files.createFile(tempDir.resolve("Example.txt"));
        int countSensitive = RecursiveFileSearch.searchFile(tempDir.toFile(), "example.txt", true);
        int countInsensitive = RecursiveFileSearch.searchFile(tempDir.toFile(), "example.txt", false);

        assertEquals(0, countSensitive, "Case-sensitive should not match different case");
        assertEquals(1, countInsensitive, "Case-insensitive should match");
    }

    @Test
    void testMultipleOccurrences() throws IOException {
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(tempDir.resolve("dup.txt"));
        Files.createFile(subDir.resolve("dup.txt"));

        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "dup.txt", true);
        assertEquals(2, count, "Should count all occurrences");
    }

    @Test
    void testEmptyAndNullDirectory() {
        assertEquals(0, RecursiveFileSearch.searchFile(tempDir.toFile(), "file.txt", true));
        assertEquals(0, RecursiveFileSearch.searchFile(null, "file.txt", true));
    }
}
