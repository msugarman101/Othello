/*
 * @authors: Elizabeth Wood & Molly Sugarman
 * @file: Game.java
 * @description: Facilitates the rules of the game/overview
 */

import java.nio.Buffer;
import java.util.*;
import java.util.Scanner;
import java.lang.String;
import java.io.*;

public class Game {



    public static void playGame() {
        Players player[];

        // Setting up the scanner for input
        Scanner input;
        input = new Scanner(System.in);

        Board board = new Board(); //create a board object
        board.setUp(); //set up the board for playing

        // Creating players
        player = new Players[2]; // Declaring the players
            player[0] = new Computer(1, "B", 0);
            player[1] = new Human(2, "W", 0);

        int selectedRow, selectedCol;


        /*
         * Initial interaction using scanner: gets inut from user as to whether they should start the game or not.
         */
        String YorN = "";
        System.out.println("Welcome to Othello! \nYou will be playing: Human vs. Computer. \nPress Y to continue and N to exit game:");
        YorN = input.next();
        if(!YorN.equalsIgnoreCase("Y") || !YorN.equalsIgnoreCase("N")){ //XZ
            System.out.println("NOT YES OR NOT NO");
            while(!YorN.equalsIgnoreCase("Y") || !YorN.equalsIgnoreCase("N")){
                System.out.println("You have not entered a correct input. Please input Y to start game and N to stop the game: ");
                YorN = input.next();

                if(YorN.equalsIgnoreCase("Y") || YorN.equalsIgnoreCase("N")){
                    break;
                }
            }
        }
        else if(YorN.equalsIgnoreCase("N")){ // N
            System.out.println("NO");
            System.out.println("----ENDING GAME----");
        }
        else{ // Y
            System.out.println("YES");
        }




        int currentPlayer = 1;
        String currentPlayerColor = "B";

        //the following code is all just testing existing aspects of Board.java (remove later)
        System.out.println("----Printing the board----");
        board.printBoard();

       // while(!endGame()) {
            for(int playerNum=0; playerNum<2; playerNum++){
                currentPlayerColor = player[playerNum].getPlayerColor();

                // If the player is not able to make a next move, the game is over.
                if(!board.validMovesLeft(currentPlayerColor)){
                    break;
                }

                // check if move is legal

                // make the move
                    int add = board.add(3, 3, 1);
                    System.out.println("first add attempt: " + add);

                    add = board.add(1, 1, 2);
                    System.out.println("second add attempt: " + add);

                    add = board.add(3, 2, 2);
                    System.out.println("third add attempt: " + add);

                    System.out.println(" ");
                    System.out.println("Updated board: ");
                    board.printBoard();

                // update the counts




            }

        }
    }


//}