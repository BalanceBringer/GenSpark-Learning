import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {


    @Test
    void printGameBoard() {
        Hangman hangy = new Hangman();
        char[][] board = {{'+', '-', '-', '-', '-', '+'}, {'|', ' ', ' ', ' ', ' ', ' '}, {'|', ' ', ' ', ' ', ' ', ' '}, {'|', ' ', ' ', ' ', ' ', ' '}, {'|', ' ', ' ', ' ', ' ', ' '}, {'=', '=', '=', '=', '='}};
        int wrong = 3;
        hangy.printGameBoard(board,wrong);

    }

    @Test
    void randomWord() {
        Hangman hangy = new Hangman();
        System.out.println(hangy.randomWord("C:/GenSpark/Projects/Week 2 Project 5/words.txt"));
    }

    @Test
    void wordState() {
        Hangman hangy = new Hangman();
        String word = "example";
        ArrayList<Character> playerGuesses = new ArrayList<>();
        playerGuesses.add('e');
        ArrayList<Character> wrongGuesses = new ArrayList<>();
        hangy.wordState(word, playerGuesses,wrongGuesses);

    }

}