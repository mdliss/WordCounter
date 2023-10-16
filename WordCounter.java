///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           M11.12 Zylabs Word Counter Program
// Course:          CS 200, Spring, 2023
//
// Author:          Max Liss-'s-Gravemade
// Email:           lisssgravema@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods for analyzing any inputted text.
 *
 * Author: Max Liss-'s-Gravemade
 */
public class WordCounter {


    /**
     * Adds words from the user's input to an ArrayList until the user presses the newline
     * character.
     *
     * @param wordList the ArrayList of words to add the user's input words to
     * @param scanner  the Scanner object used for user input
     */
    public static void addWords(ArrayList<String> wordList, Scanner scanner) {
        System.out.println("Enter words (press Enter to finish):");
        String inputLine;

        while (!(inputLine = scanner.nextLine()).trim().isEmpty()) {
            String[] words = inputLine.split("\\s+");
            for (String word : words) {
                wordList.add(word);
            }
        }
    }

    /**
     * Finds the most frequently occurring word in the ArrayList. If there is a tie, return the
     * first occurred word in the order based on the ArrayList.
     *
     * @param wordList an ArrayList representing a list of words to search for
     * @return a String representing the most frequently occurring word in the list, or "none" if
     * the list is empty or null
     */
    public static String findMostOccurringWord(ArrayList<String> wordList) {
        if (wordList == null || wordList.isEmpty()) {
            return "none";
        }

        String mostOccurringWord = "";
        int maxCount = 0;

        for (String word : wordList) {
            int wordCount = countOccurrences(wordList, word);
            if (wordCount > maxCount || (wordCount == maxCount && wordList.indexOf(word) < wordList.indexOf(mostOccurringWord))) {
                mostOccurringWord = word;
                maxCount = wordCount;
            }
        }

        return mostOccurringWord;
    }

    /**
     * This method finds the least frequently occurring word from the inputted text. If there is a
     * tie, return the first occurred word in the order based on the inputted text.
     *
     * @param wordList the ArrayList of words to be searched from
     * @return a String representing the least frequently occurring word in the list, or "none" if
     * the list is null or empty.
     */
    public static String findLeastOccurringWord(ArrayList<String> wordList) {
        if (wordList == null || wordList.isEmpty()) {
            return "none";
        }

        String leastOccurringWord = "";
        int minCount = Integer.MAX_VALUE;

        for (String word : wordList) {
            int wordCount = countOccurrences(wordList, word);
            if (wordCount < minCount || (wordCount == minCount && wordList.indexOf(word) < wordList.indexOf(leastOccurringWord))) {
                leastOccurringWord = word;
                minCount = wordCount;
            }
        }

        return leastOccurringWord;
    }

    /**
     * This method calculates what percentage of words in the text are the given word.
     *
     * @param wordList the ArrayList of all words
     * @param word     a specific word to count
     * @return a percentage in the range of [0.0, 100.0]; or -1 if the ArrayList is null or empty.
     */
    public static double calcPercentageOccur(ArrayList<String> wordList, String word) {
        if (wordList == null || wordList.isEmpty()) {
            return -1;
        }

        int totalWords = wordList.size();
        int wordCount = countOccurrences(wordList, word);

        return (double) (wordCount * 100) / totalWords;
    }

    /**
     * This method finds out all the elements in the text that are a certain word, and stores their
     * indices from that list into a new ArrayList, which is then returned.
     *
     * @param wordList   the ArrayList of all words
     * @param wordToFind a specific word to be found in the ArrayList
     * @return an ArrayList of found indices in ascending order; or null if wordList is null
     */
    public static ArrayList<Integer> findSpecificWordIndices(ArrayList<String> wordList,
                                                             String wordToFind) {
        if (wordList == null) {
            return null;
        }

        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < wordList.size(); i++) {
            if (wordToFind.equals(wordList.get(i))) {
                indices.add(i);
            }
        }

        return indices;
    }

    /**
     * Counts the occurrences of a word in the text.
     *
     * @param wordList the ArrayList of words
     * @param word     the word to count
     * @return the number of occurrences of the word
     */
    private static int countOccurrences(ArrayList<String> wordList, String word) {
        int count = 0;
        for (String w : wordList) {
            if (word.equals(w)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Allows the user to input words, and then it generates statistics.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        ArrayList<String> wordList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        addWords(wordList, scanner);

        String mostOccurringWord = findMostOccurringWord(wordList);
        System.out.println("The most frequent word is: " + mostOccurringWord);

        String leastOccurringWord = findLeastOccurringWord(wordList);
        System.out.println("The least frequent word is: " + leastOccurringWord);

        System.out.print("Enter a word to calculate its percentage of occurrences: ");
        String wordToCalculate = scanner.nextLine();
        double successRate = calcPercentageOccur(wordList, wordToCalculate);
        if (successRate == -1) {
            System.out.println("The word list is empty.");
        } else {
            System.out.println("The percentage of '" + wordToCalculate + "' occurrences is: " + successRate + "%");
        }

        System.out.print("Enter a word to find its indices: ");
        String wordToFind = scanner.nextLine();
        ArrayList<Integer> wordIndices = findSpecificWordIndices(wordList, wordToFind);

        if (wordIndices != null && !wordIndices.isEmpty()) {
            System.out.println("Here are the indices where '" + wordToFind + "' is: " + wordIndices);
        } else {
            System.out.println("The word '" + wordToFind + "' was not found in the list.");
        }

        scanner.close();
    }
}