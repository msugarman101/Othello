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
        int color = 1, add = 1; // "COLOR" also tell whos turn it is
        Players player[];
            player = new Players[2]; // Declaring the players
            player[0] = new Computer(1, "b", 0);
            player[1] = new Human(2, "w", 0);


        // the following is elizabeth's testing of board class ... feel free to remove

        Scanner input;
        input = new Scanner(System.in);

        Board board = new Board(); //create a board object
        board.setUp(); //set up the board for playing

        System.out.println("----- RULES ----- \n• 1 - black \n• 2 - white");
        System.out.println("• Rows - must choose a number from 0 - 7");
        System.out.println("• Cols - must choose a number from 0 - 7 \n");

        System.out.println("Starting game... \n");


        System.out.println("Initial board state:");
        board.printBoard();
        System.out.println();

        int playsleft = 60;

        while (playsleft > 0) { // PLAYER 1's turn
            if(color == 1){
                System.out.print("PLAYER 1's TURN [BLACK]: ");
            }
            if(color == 2){ // PLAYER 2's turn
                System.out.print("PLAYER 2's TURN [WHITE]: ");
            }
            System.out.print("\n     Enter the row of desired move [BETWEEN 0 AND 7]: ");
            int row = input.nextInt();
            System.out.print("\n     Enter the column of desired move [BETWEEN 0 AND 7]: ");
            int col = input.nextInt();
            col = 7 - col;
            add = board.add(row, col, color);
            if (add == -1) { //note: should prompt same player to move again if unsuccessful
                System.out.println("          .\n          .\n          .\n     Unsuccessful add: invalid move \n");
            }
            else {
                System.out.println("          .\n          .\n          .\n     Successful add! New board state \n");
                //player[color].addPlayerCount();

                //System.out.println("     Player " + color +" has now flipped a total of " + player[color].getPlayerCount() + " pieces \n ");
                // right now player count only flips 1 piece; need to figure out that if more than 1 piece is flipped how to tell how many pieces were flipped


                System.out.println("---UPDATED BOARD---");
                board.printBoard();
                // Swapping turns
                if(color == 1){
                    color = 2;
                } else{
                    color = 1;
                }
                playsleft --;
            }
            System.out.println();

        }

        //calculateWinner method here
    }
}







/*
hile (true) {
            System.out.print("Pick your player: please select Player 1 [White] or Player 2 [Black]): ");
            int color = input.nextInt();
            System.out.println("\n     YOU HAVE SELECTED PLAYER " + color);
            System.out.print("\n     Enter the row of desired move [BETWEEN 1 AND 6]: ");
            int row = input.nextInt();
            System.out.print("\n     Enter the column of desired move [BETWEEN 1 AND 6]: ");
            int col = input.nextInt();
            System.out.println();
            int add = board.add(row, col, color);
            if (add == -1) {
                System.out.println("  -->Unsuccessful add: invalid move \n");
            }
            else {
                System.out.println("  -->Successful add! New board state \n");
                board.printBoard();
            }
            System.out.println();
        }

    }
}

 */