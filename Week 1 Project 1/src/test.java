import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Project1Test {

    @Test
    void Project1Test() {

        String choiceStr = "5";
        int choice = 0;
        boolean success = true;
        try {
            choice = Integer.parseInt(choiceStr);

        } catch (NumberFormatException var5) {
            System.out.println("Something Broke");
            success = false;
        }
        if (choice == 1) {
            System.out.println("Everything is okay");
            success = true;
            if (choice == 2) {
                System.out.println("Everything is okay");
                success = true;
            }
            if (choice != 1 && choice != 2) {
                System.out.println("That was too big a number");
                success = false;
            }
        }
        assertTrue(success);
    }
}