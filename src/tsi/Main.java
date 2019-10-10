package tsi;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static ArrayList<GameResult> users = new ArrayList<>();

    public static void main(String[] args) {

        String answer;
        String username;
        float elapsedTimeMillis = 0;

        do {
            username = getUserName();

            boolean userWon = false;
            int myNum = rand.nextInt(100) + 1;

            for (int i = 0; i < 10; i++) {


                int userNum = askInt("\nPlease enter your guess", 1, 100);

                long start = System.currentTimeMillis(); // start counter !!!!!

                if (userNum < myNum) {
                    System.out.println(myNum);
                    System.out.println("Try higher number");

                } else if (userNum == myNum) {

                    long end = System.currentTimeMillis(); // end counter !!!!!

                    GameResult r = new GameResult();
                    r.name = username;
                    r.triesCount = i + 1;
                    r.gameplay = end - start;

                    users.add(r);

                    System.out.println("Yeah! You won!");
                    userWon = true;

                    break;
                } else {
                    System.out.println("Try lower number");


                }
            }

            if (!userWon) System.out.print("\nYou loose");

        } while (askYesNo("\nDo You want to play again? (y/n)"));

        for (GameResult result : users){
            System.out.printf("%s \t\t\t  %d %d\n" , result.name, result.triesCount, result.gameplay/1000);
        }
        System.out.println();
        System.out.println("Goodbye");
    }

    static int askInt(String msg, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                int answer = scan.nextInt();
                if (answer >= min && answer <= max) {
                    return answer;
                }
            } catch (InputMismatchException ex) {
                System.out.println("\nit is not a number, please try again");
                scan.next();
            }
            System.out.printf("Please enter number from %d to %d\n", min, max);
        }
    }

    static boolean askYesNo(String msg) {
        while (true) {
            System.out.print(msg);
            String answer = scan.next();
            boolean isY = answer.equalsIgnoreCase("y");
            boolean isN = answer.equalsIgnoreCase("n");
            if (isY || isN) {
                return isY;
            }
            System.out.printf("Enter 'Y' or 'N'");
        }
    }

    static String getUserName() {
        System.out.println("Enter Your name please");

        String username = scan.next();
        System.out.print("Hello, " + username);
        return username;
    }
}