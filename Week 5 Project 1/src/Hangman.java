import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hangman {

    public static ArrayList<String> correctG = new ArrayList<>();
    public static ArrayList<String> wrongG = new ArrayList<>();
    public static int numWrong;



    public static void main(String[] args) throws IOException {
        boolean game = true;
        Scanner scynyrd = new Scanner(System.in);
        String word = "checker";
        correctG.clear();
        wrongG.clear();
        numWrong = 0;





            System.out.println("Welcome to Hangman 2.");
            System.out.println("Guess a letter and see if you are correct!");
            System.out.println("If you guess wrong, you endanger Jerry.");
            System.out.println("If you guess correctly, you spare him... for now.");
            System.out.println("Are you ready? Y or N for yes and no.");
            String resp = scynyrd.nextLine();

            if(!resp.equals("Y") &&  !resp.equals("y")){
                System.out.println("Oh okay.");
                System.out.println("You have been disconnected front the server");
                System.exit(0);
            } else {
                System.out.println("Good. Let us begin.");
            }
            System.out.println("The word has " + word.length() + " letters.");
        do{
            System.out.println("Guess a letter.");
            String guess = scynyrd.nextLine();
            boolean guessed = checkGuess(guess, word);
            if(guessed){
                System.out.println("Your guess was correct!");
                System.out.println("Current correct guesses: ");
                System.out.println(correctG);
                System.out.println("Current wrong guesses: ");
                System.out.println(wrongG);
                if(correctG.size() >= word.length()){
                    System.out.println("You win!");
                    System.out.println("Play again?");
                    if(!scynyrd.nextLine().equals("Y") && !scynyrd.nextLine().equals("y")) {
                        game = false;
                    }
                }
            } else{
                System.out.println("Oh no, friend, that was not correct.");
                System.out.println("Look at your buddy now.");
                //show hangman
                drawHangman();
                System.out.println("Current correct guesses: ");
                System.out.println(correctG);
                System.out.println("Current wrong guesses: ");
                System.out.println(wrongG);
                if(wrongG.size() >= 7){
                    System.out.println("Terrible news. Your friend... has gone for a swing.");
                    System.out.println("Play again?");
                    if(!scynyrd.nextLine().equals("Y") && !scynyrd.nextLine().equals("y")) {
                        game = false;
                    }
                }
            }
            if(game){
                if(wrongG.size() >= 7 || correctG.size() >= word.length()){
                    System.out.println("Here we go again! We won't count that last time for you, do better ;)");
                    correctG.clear();
                    wrongG.clear();
                    numWrong = 0;
                }
            }
        } while(game);

        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            System.out.println("What is your name? For the record.");
            myWriter.write(scynyrd.nextLine() + " " + numWrong);
            System.out.println("Your score is: " + numWrong + " guesses.");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        highScore(numWrong,word);
    }

    public static boolean checkGuess(String guess, String word){


        if(word.contains(guess)){
            word.chars().forEach((k)-> {
                if(guess.contains(Character.valueOf((char)k).toString())){
                    correctG.add(guess);
                }
            });
        } else{
            wrongG.add(guess);
            numWrong++;
        }
        return word.contains(guess);
    }
    public static void drawHangman() throws IOException {
        MyFrame frame = new MyFrame(numWrong);
        frame.repaint();
    }
    public static void highScore(int numWrong, String word) {
        int highScore;
        //read file into stream, try-with-resources
        List<Integer> scores = new ArrayList<>();
        try {
            scores = Files.readAllLines(Paths.get("filename.txt")).stream()
                    .map(x -> Integer.valueOf(x.substring(x.indexOf(" ") + 1)))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong checking the leaderboards!");
        }

        if (scores.isEmpty()){
            highScore = 999;
        } else{
            highScore = scores.get(scores.size() - 1);
        }

        if(numWrong > highScore){
            System.out.println("Congrats! You saved your friend the fastest with " + numWrong + " wrong guesses!");
        } else if( numWrong == highScore){
            System.out.println("Congrats! You saved your friend! You tied for fastest with " + numWrong + " wrong guesses!");
        } else{
            System.out.println("Booooo! You call that saving your friend? You had " + numWrong + " wrong guesses! I am disappointed.");
        }


    }
}
