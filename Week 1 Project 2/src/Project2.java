import java.util.Scanner;

public class Project2 {

    public static void main(String[] args) {
        Scanner playerResponse = new Scanner(System.in); //Creates a Scanner object to read player input from the console


        boolean play = true;

        while(play){
        System.out.println("Hello! What is your name, my friend?");
        String playerName = "";
        try {
            playerName = playerResponse.nextLine();
        } catch(Exception uhOh1){
            System.out.println("Uh oh! Something went wrong!");
            play = false;
        }

        System.out.println("Well, " + playerName + ", I am thinking of a number between 1 and 20.");
        System.out.println("If you can guess it in 6 tries, you win!");
        System.out.println("Take a guess!");

        int ranNum = (int )(Math.random() * 20 + 1);



        for(int i = 0; i<6; i++) {

            String choiceStr = playerResponse.nextLine();
            int choice = 0;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException var5) {
                System.out.println("That is not a number. C'mon man. Please. I worked hard on this.");
            }

            if (ranNum == choice) {
                System.out.println("You guess right! The answer was " + choice + "!");
                i = 6;
            } else if (choice > ranNum) {
                System.out.println("You were wrong! :( The number is lower!");
            } else if (choice < ranNum) {
                System.out.println("You were wrong! :( The number is higher!");
            } else {
                System.out.println("Uh oh! You lost!");
            }



        }
            System.out.println("Would you like to play again?(Y or N)");

            char ans = 'a';
            try {
                ans = playerResponse.nextLine().charAt(0);
            } catch(Exception uhOh2){
                System.out.println("Oh no! Something went wrong!");
                play = false;
            }

            if(ans == 'Y'){
                play = true;
            }
            else{
                play = false;
            }

        }

    }
}
