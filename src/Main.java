import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static char[][] boardDefault = {
        {'1','2','3'},
        {'4','5','6'},
        {'7','8','9'}
    };
    public static char[][] boardPlay = new char[boardDefault.length][];
    public static char currentPlayer = 'X';

    public static void main(String[] args) {
        //Need to setup my play board with some values, might as well use the default.
        for (int i = 0; i < boardDefault.length; i++) {
            boardPlay[i] = Arrays.copyOf(boardDefault[i], boardDefault[i].length);
        }
        boolean winnerFound = false;
        System.out.println("WELCOME TO TIKTAKTOE");
        while(!winnerFound){
            switchPlayer();
            makeBoard();
            gameRound();
            winnerFound = checkIfWinner();
        }
    }

    /**
     * Winner Numbers from the Array:
     * [0][0], [0][1], [0][2]
     * [1][0], [1][1], [1][2]
     * [2][0], [2][1], [2][2]
     *
     * [0][0], [1][0], [2][0]
     * [0][1], [1][1], [2][1]
     * [0][2], [1][2], [2][2]
     *
     * [0][0], [1][1], [2][2]
     * [2][0], [1][1], [0][2]
     * @return
     */
    public static boolean checkIfWinner(){
        for (int i = 0; i < 3; i++) {
            if (boardPlay[i][0] == currentPlayer && boardPlay[i][1] == currentPlayer && boardPlay[i][2] == currentPlayer){
                printWinner();
                return true;
            }
            if (boardPlay[0][i] == currentPlayer && boardPlay[1][i] == currentPlayer && boardPlay[2][i] == currentPlayer){
                printWinner();
                return true;
            }
        }
        if (boardPlay[0][0] == currentPlayer && boardPlay[1][1] == currentPlayer && boardPlay[2][2] == currentPlayer){
            printWinner();
            return true;
        }
        if (boardPlay[2][0] == currentPlayer && boardPlay[1][1] == currentPlayer && boardPlay[0][2] == currentPlayer){
            printWinner();
            return true;
        }
        return false;
    }
    public static void printWinner() {
        makeBoard();
        System.out.printf("%s%S%s", YELLOW, "WINNER WINNER CHICKEN DINNER", RESET);
        System.out.printf("%s%n%S%s%s%s%n%n", YELLOW, "WINNER PLAYER: ", RED, currentPlayer, RESET);
        System.out.printf("%s","Play again? %n0 = No, 1 = Yes:");
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
                System.out.printf("%s%c%s%s", RED, boardDefault[i][j], RESET, " | ");
            }
            //Footer of my board.
            System.out.printf("%n+---+---+---+  +---+---+---+%n");
        }

    }
    public static void gameRound (){
        System.out.printf("%sYour turn player %s%c%s%n", GREEN, YELLOW, currentPlayer, RESET);
        System.out.print("Please input your desired square: ");
        int number = checkInput(1, 9);

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
    public static int checkInput(int min, int max) {
        Scanner input = new Scanner(System.in);
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