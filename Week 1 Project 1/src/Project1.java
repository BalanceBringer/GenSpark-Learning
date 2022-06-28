//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class Project1 {
    public Project1() {
    }

    public static void main(String[] args) {
        Scanner playerResponse = new Scanner(System.in);
        int choice = 0;
        System.out.println("In front of you, there are two caves.");
        System.out.println("One has a hungry dragon that will eat you on sight.");
        System.out.println("The other has a friendly dragon that will share its wealth.");
        System.out.println("Which do you choose?(1 or 2)");
        String choiceStr = playerResponse.nextLine();
        System.out.println(choiceStr);

        try {
            choice = Integer.parseInt(choiceStr);
        } catch (NumberFormatException var5) {
            System.out.println("That is not an option. C'mon man. Please. I worked hard on this.");
        }
        if (choice == 1) {
            System.out.println("Uh oh! That was the hungry boi! You gotten eaten immediately!");
        }

        if (choice == 2) {
            System.out.println("The dragon shared its hoard! Maybe the real treasure were the friends we made along the way!");
        }
        if(choice != 1 && choice != 2) {
            System.out.println("Why? That wasn't an option and you know it!");
        }

        System.out.println("That's the game!");
        System.exit(0);
    }
}
