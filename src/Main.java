import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /**
     *TODO Player tracker
     *TODO Generate the map
     *TODO Place O or X
     *TODO Check who is wining
     */
    public static char[][] boarddefault = {
        {'1','2','3'},
        {'4','5','6'},
        {'7','8','9'}
    };
    public static char[][] boardPlay = new char[boarddefault.length][];
    public static char currentPlayer = 'X';

    public static void main(String[] args) {
        //Need to setup my play board with some values, might as well use the default.
        for (int i = 0; i < boarddefault.length; i++) {
            boardPlay[i] = Arrays.copyOf(boarddefault[i], boarddefault[i].length);
        }

        Scanner input = new Scanner(System.in);
        boolean winnerFound = false;
        System.out.println("WELCOME TO TIKTAKTOE");
        while(!winnerFound){
            makeBoard();
            gameRound(input);
            switchPlayer();
            //winnerFound = checkIfWinner();
        }
    }
    public static boolean checkIfWinner(){
        return true;
    }
    public static void makeBoard() {
        //Header of my board.
        System.out.printf("%sCURRENT PLAY   %sSELECTION BOARD%s%n",YELLOW,RED,RESET);
        System.out.printf("+---+---+---+  +---+---+---+%n");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");

            // Print elements from the first array (boardPlay) this is what the player changes.
            for (int j = 0; j < 3; j++) {
                System.out.printf("%s%c%s%s", YELLOW, boardPlay[i][j], RESET, " | ");
            }

            // Print a separator
            System.out.print(" | ");

            // Print elements from the second array (boarddefault) this is to reference what number belongs where.
            for (int j = 0; j < 3; j++) {
                System.out.printf("%s%c%s%s", RED, boarddefault[i][j], RESET, " | ");
            }
            //Footer of my board.
            System.out.printf("%n+---+---+---+  +---+---+---+%n");
        }

    }
    public static void gameRound (Scanner input){
        System.out.printf("%sYour turn player %s%c%s%n", GREEN, YELLOW, currentPlayer, RESET);
        System.out.print("Please input your desired square: ");
        int number = checkInput(input, 1, 9);

        switch (number){
            case 1:
                updateBoard(0,0);
                break;
            case 2:
                updateBoard(0,1);
                break;
            case 3:
                updateBoard(0,2);
                break;
            case 4:
                updateBoard(1,0);
                break;
            case 5:
                updateBoard(1,1);
                break;
            case 6:
                updateBoard(1,2);
                break;
            case 7:
                updateBoard(2,0);
                break;
            case 8:
                updateBoard(2,1);
                break;
            case 9:
                updateBoard(2,2);
                break;
        }
    }
    public static void updateBoard (int row, int column){
        if (boardPlay[row][column] == 'X' || boardPlay[row][column] == 'O'){
            System.out.printf("%s%S%s%n",RED,"Already taken, please try again.",RESET);
        } else {
            boardPlay[row][column] = currentPlayer;
        }

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
    public static void switchPlayer (){
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
}