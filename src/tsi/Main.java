package tsi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static ArrayList<GameResult> LeaderBoard = new ArrayList<>();

    public Main() throws FileNotFoundException {
    }


    public static void main(String[] args) {

        String answer;
        String username;
        float elapsedTimeMillis = 0;
        loadResults();

        do {
            username = getUserName();

            boolean userWon = false;
            int myNum = rand.nextInt(100) + 1;

            long start = System.currentTimeMillis(); // START counter !!!!!

            for (int i = 0; i < 10; i++) {


                int userNum = askInt("\nPlease enter your guess", 1, 100);



                if (userNum < myNum) {
                    System.out.println(myNum);
                    System.out.println("Try higher number");

                } else if (userNum == myNum) {

                    long end = System.currentTimeMillis(); // END counter !!!!!

                    GameResult r = new GameResult(); // r = obj reference
                    r.setName(username);
                    r.setTriesCount(i + 1);
                    r.setGameplay(end - start);

                    LeaderBoard.add(r);

                    System.out.println("Yeah! You won!");
                    userWon = true;

                    break;
                } else {
                    System.out.println("Try lower number");
                }
            }

            if (!userWon) System.out.print("\nYou loose");

        } while (askYesNo("\nDo You want to play again? (y/n)"));


        showResults2();
        save();

        System.out.println();
        System.out.println("Goodbye");
    }

    private static void loadResults() {
        File file = new File("leader_board.txt");
        try(Scanner in = new Scanner(file)){
            while(in.hasNext()){

                GameResult r = new GameResult();

                String name = in.next();
                int tries = in.nextInt();
                long time = in.nextLong();

                r.setName(name);
                r.setTriesCount(tries);
                r.setGameplay(time);

                LeaderBoard.add(r);

            }
        }catch (IOException e){
            System.out.println("Cannot read leader board");
        }
    }

    private static void save() {
        File file = new File("leader_board.txt");
        try(PrintWriter out = new PrintWriter(file)) {
            for (GameResult result : LeaderBoard) {
            out.printf("%s %d %d\n",
                    result.getName(),
                    result.getTriesCount(),
                    result.getGameplay());
        }
        }catch(IOException e){

        }
    }


//    private static void showResults() {
//        LeaderBoard.sort(Comparator.comparingInt(GameResult::getTriesCount).thenComparing(GameResult::getGameplay));
//
//        for (GameResult result : LeaderBoard) {
//            System.out.printf("%s \t\t\t  %d %d\n",
//                    result.getName(),
//                    result.getTriesCount(),
//                    result.getGameplay() / 1000);
//        }
//    }




    private static void showResults2() {
        LeaderBoard.stream()
                .sorted(Comparator
                        .comparingInt(GameResult::getTriesCount)
                        .thenComparing(GameResult::getGameplay))
                .limit(5)
                .forEach(r -> System.out.printf("%s \t\t\t  %d %d\n",
                        r.getName(),
                        r.getTriesCount(),
                        r.getGameplay() / 1000));

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