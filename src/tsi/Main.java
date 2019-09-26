package tsi;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        String answer;

        do {
            boolean userWon = false;

            for (int i = 0; i < 10; i++) {
                int myNum = rand.nextInt(100) + 1;
//                    System.out.println(myNum);
                System.out.println("enter number");
                int userNum = scan.nextInt();

                if (userNum < myNum) {
                    System.out.println("Try higher number");
                } else if (userNum == myNum) {
                    System.out.println("Congratulations!!!!! Your number is correct");
                    userWon = true;
                    break;
                } else {
                    System.out.println("Try lower number");
                }
            }

            if (!userWon) System.out.print("\nLooser");
            System.out.println("\nDo you want to continue?\n1: yes\n2: no");
            answer = scan.next();

        } while (answer.equals("1"));
    }


}



