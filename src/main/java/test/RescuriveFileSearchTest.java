package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

import code.RecursiveFileSearch;
import org.junit.jupiter.api.Test;

class RecursiveFileSearchTest {

    @Test
    void testFileExistsInRoot() {
        File dir = new File("testDir"); // create this test folder manually or in setup
        String fileName = "rootFile.txt"; // place a test file in the root
        assertTrue(RecursiveFileSearch.searchFile(dir, fileName));
    }

    @Test
    void testFileExistsInSubdirectory() {
        File dir = new File("testDir"); // directory with subfolder
        String fileName = "subFile.txt"; // file inside a subdirectory
        assertTrue(RecursiveFileSearch.searchFile(dir, fileName));
    }

    @Test
    void testFileDoesNotExist() {
        File dir = new File("testDir");
        String fileName = "notExist.txt";
        assertFalse(RecursiveFileSearch.searchFile(dir, fileName));
    }

    @Test
    void testDirectoryDoesNotExist() {
        File dir = new File("nonExistentDir");
        String fileName = "anyFile.txt";
        assertFalse(RecursiveFileSearch.searchFile(dir, fileName));
    }

    @Test
    void testEmptyDirectory() {
        File dir = new File("emptyDir"); // empty folder
        String fileName = "file.txt";
        assertFalse(RecursiveFileSearch.searchFile(dir, fileName));
    }

    @Test
    void testNullDirectory() {
        File dir = null;
        String fileName = "file.txt";
        assertFalse(RecursiveFileSearch.searchFile(dir, fileName));
    }
}
