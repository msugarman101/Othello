/*
 * @authors: Elizabeth Wood & Molly Sugarman
 * @file: Main.java
 * @description: Starts + ends the game
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game newGame = new Game(); // Declaring a new game
        //newGame.playGame(); // Starting the new game

        // the following is elizabeth's testing of board class ... feel free to remove

        Scanner input;
        input = new Scanner(System.in);

        Board board = new Board(); //create a board object
        board.setUp(); //set up the board for playing

        System.out.println("Initial board state:");
        board.printBoard();

        while (true) {
            System.out.println("Color to add (1 or 2): ");
            int color = input.nextInt();
            System.out.println("Enter the row of desired move");
            int row = input.nextInt();
            System.out.println("Enter the column of desired move");
            int col = input.nextInt();
            int add = board.add(row, col, color);
            if (add == -1) {
                System.out.println("Unsuccessful add: invalid move");
            }
            else {
                System.out.println("Successful add! New board state");
                board.printBoard();
            }
        }

    }
}
