import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Project2Test {

    @Test
    void CorrectUserInputType() {
        String choiceStr = "5";
        boolean success;
        int choice = 0;
        try {
            choice = Integer.parseInt(choiceStr);
            success = true;
        } catch (NumberFormatException var5) {
            System.out.println("That is not a number. C'mon man. Please. I worked hard on this.");
            success = false;
        }

        assertTrue(success);
    }
}