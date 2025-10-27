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

class RecursiveFileSearchTest {

    @TempDir
    Path tempDir; // JUnit provides a temporary directory

    @Test
    void testSingleFileExistsInRoot() throws IOException {
        Path file = Files.createFile(tempDir.resolve("rootFile.txt"));
        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "rootFile.txt", true);
        assertEquals(1, count);
    }

    @Test
    void testSingleFileExistsInSubdirectory() throws IOException {
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Path file = Files.createFile(subDir.resolve("subFile.txt"));
        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "subFile.txt", true);
        assertEquals(1, count);
    }

    @Test
    void testMultipleFilesSearch() throws IOException {
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(subDir.resolve("file2.txt"));
        Files.createFile(subDir.resolve("file3.txt"));

        List<String> fileNames = Arrays.asList("file1.txt", "file2.txt", "file3.txt", "fileNotExist.txt");

        for (String fileName : fileNames) {
            int count = RecursiveFileSearch.searchFile(tempDir.toFile(), fileName, true);
            if (fileName.equals("fileNotExist.txt")) {
                assertEquals(0, count);
            } else {
                assertEquals(1, count);
            }
        }
    }

    @Test
    void testCaseInsensitiveSearch() throws IOException {
        Files.createFile(tempDir.resolve("Example.txt"));
        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "example.txt", false); // case-insensitive
        assertEquals(1, count);
    }

    @Test
    void testCaseSensitiveSearch() throws IOException {
        Files.createFile(tempDir.resolve("Example.txt"));
        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "example.txt", true); // case-sensitive
        assertEquals(0, count);
    }

    @Test
    void testMultipleOccurrences() throws IOException {
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(tempDir.resolve("dup.txt"));
        Files.createFile(subDir.resolve("dup.txt"));

        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "dup.txt", true);
        assertEquals(2, count); // Should count both occurrences
    }

    @Test
    void testEmptyDirectory() {
        int count = RecursiveFileSearch.searchFile(tempDir.toFile(), "file.txt", true);
        assertEquals(0, count);
    }

    @Test
    void testNullDirectory() {
        int count = RecursiveFileSearch.searchFile(null, "file.txt", true);
        assertEquals(0, count);
    }
}
