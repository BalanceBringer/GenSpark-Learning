import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {



    private static int wrong;

    public static void main(String[] args){
        //initializing variables and the pretty game graphics
        boolean keepPlaying = true;
        Scanner userInput = new Scanner(System.in);

        while(keepPlaying) {
            wrong = 0;
            String cpuWord = randomWord("C:/GenSpark/Projects/Week 2 Project 5/words.txt");
            ArrayList<Character> compWordArr = new ArrayList<Character>();
            ArrayList<Character> playerGuesses = new ArrayList<Character>();
            ArrayList<Character> wrongGuesses = new ArrayList<Character>();

            char[][] board = {{'+', '-', '-', '-', '-', '+'}, {'|', ' ', ' ', ' ', ' ', ' '}, {'|', ' ', ' ', ' ', ' ', ' '}, {'|', ' ', ' ', ' ', ' ', ' '}, {'|', ' ', ' ', ' ', ' ', ' '}, {'=', '=', '=', '=', '='}};
            try{
                printGameBoard(board, wrong);
            } catch (Exception uhOh){
                System.out.println("Your game board had some issues");
                break;
            }
            wordState(cpuWord, playerGuesses, wrongGuesses);


            while (true) {
                playingTheGame(userInput, playerGuesses, wrongGuesses, cpuWord);
                printGameBoard(board, wrong);
                System.out.println("Missed Letters: " + wrongGuesses);
                if (wordState(cpuWord, playerGuesses, wrongGuesses)) {
                    System.out.println("You won! the word was " + cpuWord);
                    break;
                } else if (wrong >= 6) {
                    System.out.println("You lost bozo!");
                    break;
                }

            }
            System.out.println("Would you like to play again? (Y or N :):");
            char response = userInput.nextLine().charAt(0);
            if(response == 'Y'){
                keepPlaying = true;
            }
            else{
                keepPlaying = false;
            }
        }
    }


    //Prints the game board
    public static void printGameBoard(char board[][], int wrong){

        switch(wrong) {
            case 1:
                board[1][3] = 'O';
                break;

            case 2:
                board[1][3] = 'O';
                board[2][3] = '|';
                break;

            case 3:
                board[1][3] = 'O';
                board[2][3] = '|';
                board[2][2] = '/';
                break;

            case 4:
                board[1][3] = 'O';
                board[2][3] = '|';
                board[2][2] = '/';
                board[2][4] = '\\';
                break;

            case 5:
                board[1][3] = 'O';
                board[2][3] = '|';
                board[2][2] = '/';
                board[2][4] = '\\';
                board[3][3] = '|';
                break;

            case 6:
                board[1][3] = 'O';
                board[2][3] = '|';
                board[2][2] = '/';
                board[2][4] = '\\';
                board[3][3] = '|';
                board[4][3] = '^';
                break;
        }
        for(char[] row : board){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static String randomWord(String dictionary) {

        List<String> wordList = new ArrayList<>();
        Random randy = new Random();

        Scanner scan = null;
        try {
            scan = new Scanner(new File(dictionary));
        } catch (FileNotFoundException uhOh) {
            System.out.println("There is no file");
        }

        while (scan.hasNext()){
            wordList.add(scan.nextLine());
        }

        String word = wordList.get(randy.nextInt(wordList.size()));

            return word;
    }

    public static boolean wordState(String word, ArrayList<Character> playerGuesses, ArrayList<Character> wrongGuesses){

        int correct = 0;

        for(int i=0;i < word.length();i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correct++;

            }
            else {
                System.out.print('-');

            }


        }



        System.out.println();



        return (correct == word.length());
    }

    public static void playingTheGame(Scanner userInput, ArrayList<Character> playerGuesses, ArrayList<Character> wrongGuesses, String cpuWord){
        System.out.println("Please guess a letter:");

        String letterGuessed = userInput.nextLine();
        CharSequence letter = letterGuessed.toString();

        if(!cpuWord.contains(letter)){
            wrong++;
            wrongGuesses.add(letterGuessed.charAt(0));
        }

        try {
            playerGuesses.add(letterGuessed.charAt(0));
        } catch(Exception rip){
            System.out.println("Uh oh something went wrong! You're new answer is c");
            playerGuesses.add('c');
        }





    }

}
