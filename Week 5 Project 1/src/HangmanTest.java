import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    @org.junit.jupiter.api.Test
    void checkGuess() {
        Hangman steve = new Hangman();
        Scanner scan = new Scanner(System.in);
        boolean res = steve.checkGuess("f","four");
        assertEquals(true,res);
    }

    @org.junit.jupiter.api.Test
    void drawHangman() throws IOException {
        Hangman steve = new Hangman();
        steve.drawHangman();
    }

    @org.junit.jupiter.api.Test
    void highScore() {
        Hangman steve = new Hangman();
        steve.highScore(3,"four");
    }
}