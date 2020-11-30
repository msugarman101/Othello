/*
 * @authors: Elizabeth Wood & Molly Sugarman
 * @file: Board.java
 * @description: maintains state of the board and allows for changes to current board state
 */

import java.util.*;

public class Board {
    // Attributes: every Board object has a physical 8x8 board as well as a current winner
    private int board[][];
    private String currentWinner;



    /*
     * Constructor: construct a Board object
     */
    public Board() {
        board = new int[8][8]; //allocate board
        currentWinner = "TIE"; //initialize currentWinner to be a tie
    }


    /*
     * setUp sets up the initial board state. Sets all squares to be either empty or black/white, appropriately
     */
    public void setUp() {
        //0 represents an empty square, 1 represents white, 2 represents black
        //first, initialize the whole board to have empty squares
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = 0;
            }
        }
        //next, set up the four inner most squares appropriately
        board[3][3] = 1; //white
        board[3][4] = 2; //black
        board[4][3] = 2; //black
        board[4][4] = 1; //white
    }


    /*
     * printBoard prints the current state of the board, printing 0 for empty squares, b for black, w for white
     */
    public void printBoard() {
        for (int i = 0; i < 8; i++) { //for each row
            for (int j = 0; j < 8; j++) { //for each column
                if (board[i][j] == 1) { //print 'w' for white squares
                    System.out.print("w ");
                }
                else if (board[i][j] == 2) { //print 'b' for black squares
                    System.out.print("b ");
                }
                else { //otherwise, print 0 for empty
                    System.out.print("0 ");
                }
            }
            System.out.println(); //print a new line in between rows
        }
    }


    /*
     * add takes a row, a column, and an int (1 or 2 for white or black) and adds the in to the specified row and col of the board
     * returns 0 for a successful add, -1 if the move is invalid (i.e. adding to an existing location or a disconnected square)
     */
    public int add(int row, int col, int color) {
        //make sure the specified square isn't already occupied
        if (board[row][col] != 0) {
            return -1;

        }
        //make sure the intended add isn't to a disconnected square
        boolean adjacents = checkAdjacents(row, col);
        if (adjacents == false) {
            return -1;
        }
        //the intended add is valid, so set the board at the given location to the given color
        board[col][row] = color;
        return 0;
    }


    /*
     * private method to check all squares that are adjacent to the specified location
     * if no adjacent squares are found to be occupied, return false; otherwise, return true
     */
    private boolean checkAdjacents(int row, int col) {
        boolean adjacent = false; //assume there are no adjacent occupied squares until one is found
        //make sure the specified square is adjacent to one that's already occupied (all surrounding squares can't be 0)
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (board[i][j] != 0) {
                    adjacent = true;
                }
            }
        }
        return adjacent;
    }


    /*
     * this method isn't finished/functional yet, but is intended to flip all of the occupied squares in a row
     * of the opposite color in between your color and the move you just made
     */
    public void flipInsides(int row, int col, int color) {
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                //adjacent square of opposite color
                while (board[i][j] != 0 && board[i][j] != color) {
                    flip(i, j);

                }
            }
        }

    }

    /*
     * private method flip is called by flipInsides. It simply switches (flips over) an occupied square from one color
     * to the other.
     */
    private void flip(int row, int col) {
        if (board[row][col] == 1) {
            board[row][col] = 2;
        }
        else if (board[row][col] == 2) {
            board[row][col] = 1;
        }
    }

}
