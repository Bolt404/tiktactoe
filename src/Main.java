import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    /**
     *TODO Player tracker
     *TODO Generate the map
     *TODO Place O or X
     *TODO Check who is wining
     * @param args
     */
    public static char[][] board = {
        {'1','2','3'},
        {'4','5','6'},
        {'7','8','9'}
    };
    public static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("WELCOME TO TIKTAKTOE");
        while(true){
            makeBoard();
            gameRound(input);
        }
    }
    public static void makeBoard() {
        System.out.printf("+---+---+---+%n");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| ");
            for (int j = 0; j < 3; j++) {
                System.out.printf("%s%c%s%s",YELLOW, board[i][j], RESET, " | ");
            }
            System.out.printf("%n+---+---+---+%n");
        }
    }
    public static void gameRound (Scanner input){
        System.out.printf("%sYour turn player %s%c%s%n", GREEN, YELLOW, currentPlayer, RESET);
        System.out.printf("Please input your desired square: ");
        int number = checkInput(input, 1, 9);
    }
    public static int checkInput(Scanner input, int min, int max) {

        while (true) {
            if (input.hasNextInt()) {
                int number = input.nextInt();
                input.nextLine();
                if (number >= min && number <= max) {
                    return number; // Godt input, return integer.
                } else {
                    System.out.printf("%s%S [%d, %d].%s%n", RED, "Invalid input. Please enter a number within the range", min, max, RESET);
                }
            } else {
                System.out.printf("%s%S [%d, %d].%s%n", RED, "Invalid input. Please enter a number within the range", min, max, RESET);
                input.next();
            }
        }
    }
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
}