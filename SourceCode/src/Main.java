/*
 * @authors: Elizabeth Wood & Molly Sugarman
 * @file: Main.java
 * @description: Starts + ends the game
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int color = 1, add = 1; // "COLOR" also tell whos turn it is
        String forfeit = "F";
        Players player[];
        player = new Players[2]; // Declaring the players
        player[0] = new Players(1, "b", 0, 0);
        player[1] = new Players(2, "w", 0, 0);


        // the following is elizabeth's testing of board class ... feel free to remove

        Scanner input;
        input = new Scanner(System.in);


        Board board = new Board(); //create a board object
        board.setUp(); //set up the board for playing

        System.out.println("----- RULES ----- \n• 1 - black \n• 2 - white");
        System.out.println("• Rows - must choose a number from 0 - 7");
        System.out.println("• Cols - must choose a number from 0 - 7");
        System.out.println("• Select 'F' to forfeit game [GAME OVER] \n");


        System.out.println("Starting game... \n");

            System.out.println("Initial board state:");
            board.printBoard();
            System.out.println();

            int playsleft = 60;

            String forfiet = "N";

            while (playsleft > 0 && ! (forfiet.equals("Y"))) {
                if (color == 1) {
                        System.out.print("PLAYER 1's TURN [BLACK]: ");
                }
                if (color == 2) { // PLAYER 2's turn
                    System.out.print("PLAYER 2's TURN [WHITE]: ");
                }
                System.out.print("\n     Enter x-coordinate and y-coordinate [Press 'enter' after each value]: \n     ");
                int row = input.nextInt();  // int row = input.nextInt();
                System.out.print("\n     ");
                int col = input.nextInt();
                col = 7 - col;
                add = board.add(row, col, color);
                if (add == -1) { //note: should prompt same player to move again if unsuccessful
                    System.out.println("          .\n          .\n          .\n     Unsuccessful add: invalid move \n");
                    System.out.println("Out of moves? Type 'Y' to forfiet the game, or 'N' to continue: ");
                    forfiet = input.next();
                }
                else {
                    System.out.println("          .\n          .\n          .\n     Successful add! New board state \n");
                    System.out.println("---UPDATED BOARD---");
                    board.printBoard();
                    // Swapping turns
                    if (color == 1) {
                        color = 2;
                    }
                    else {
                        color = 1;
                    }
                    playsleft--;
                }
            }


            int winner = board.getWinner();
            System.out.println("-- GAME OVER --");
            if (winner == 1) {
                System.out.println("Player 2 [Black] WINS!");
            } else if (winner == 2) {
                System.out.println("Player 1 [White] WINS!");
            } else {
                System.out.println("Tie game!");
            }
            System.out.println("---FLIPS---");
            System.out.println("   BLACK [PLAYER 1]: " + player[0].getFlipCount());
            System.out.println("   WHITE [PLAYER 2]: " + player[1].getFlipCount());
    }
}