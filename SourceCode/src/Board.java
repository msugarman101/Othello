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
    public Players player[];

    /*
     * Constructor: construct a Board object
     */
    public Board() {
        board = new int[8][8]; //allocate board
        currentWinner = "TIE"; //initialize currentWinner to be a tie

        player = new Players[2]; // Declaring the players
        player[0] = new Players(1, "b", 0, 0);
        player[1] = new Players(2, "w", 0, 0);
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
        board[3][3] = 2; //white
        board[3][4] = 1; //black
        board[4][3] = 1; //black
        board[4][4] = 2; //white
    }


    /*
     * printBoard prints the current state of the board, printing 0 for empty squares, b for black, w for white
     */
    public void printBoard() {
        for (int j = 0; j < 8; j++) { //for each row
            for (int i = 0; i < 8; i++) { //for each column
                if (board[i][j] == 1) { //print 'b' for black squares
                    System.out.print("b ");
                }
                else if (board[i][j] == 2) { //print 'w' for white squares
                    System.out.print("w ");
                }
                else { //otherwise, print 0 for empty
                    System.out.print("_ ");
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
        //attempt to make the move
        boolean successful = attemptMove(row, col, color);
        //return -1 if move was unsuccessful (i.e. invalid add location)
        if (!successful) {
            return -1;
        }
        //move was successful, so update the color at that location
        board[row][col] = color;
        return 0;
    }


    /*
     * this method isn't finished/functional yet, but is intended to flip all of the occupied squares in a row
     * of the opposite color in between your color and the move you just made
     */
    public boolean attemptMove(int row, int col, int color) {
        int oppositeColor;
        boolean adjacents = false; //assume there is not a valid move
        if (color == 1) {
            oppositeColor = 2;
        }
        else {
            oppositeColor = 1;
        }
        if (row == 0 || col == 0 || row == 7 || col == 7) {
            boolean isValid = handleEdge(row, col, color); //handle move attempts at the edge of the board
            if (isValid) {
                adjacents = true;
            }
        }
        else { //not an edge, so check all surrounding spots
            for (int i = row - 1; i < row + 2; i++) {
                for (int j = col - 1; j < col + 2; j++) {
                    //adjacent square of opposite color
                    if (board[i][j] == oppositeColor) {
                        boolean isValid = scanDirection(row, col, i, j, color);
                        if (isValid) {
                            adjacents = true;
                            flipLine(row, col, i, j, color);
                        }

                    }
                }
            }
        }
        return adjacents;
    }


    /*
     * RECURSIVE ALGORITHM: continue searching in one direction. This uses a recurrence function... DIVIDE & CONQUER
     * scan every point in the line from point (row, col) in the direction of (i,j) until reaching color.
     * If color is never reached, return false. Otherwise, flip all the points in between (row, col) and color
     */
    public boolean scanDirection(int row, int col, int i, int j, int color){
        //base case 1: have reached end of board so return false
        if (i < 0 || j < 0 || i > 7 || j > 7) {
            return false;
        }
        //base case 2: have hit color so return true
        else if (board[i][j] == color) {
            return true;
        }
        //base case 3: have hit an empty square so return false
        else if (board[i][j] == 0) {
            return false;
        }
        //if you haven't hit a base case, recursively call function until you do
        else if (i < row) { //search left
            i--;
            if (j < col) { //search left up
                j--;
            }
            else if (j > col) { //search left down
                j++;
            }
            return scanDirection(row, col, i, j, color);
        }
        else if (i > row) { //search right
            i++;
            if (j < col) { //search right up
                j--;
            }
            else if (j > col) { //search right down
                j++;
            }
            return scanDirection(row, col, i, j, color);
        }
        else { //search vertically
            if (j < col) {
                j--; //search up
            }
            else if (j > col) { //search down
                j++;
            }
            return scanDirection(row, col, i, j, color);
        }
    }


    /*
     * flip consecutive colors in a given line until the opposite color is reached
     */
    public void flipLine(int row, int col, int i, int j, int color) {
        //continue flipping in a line until color is reached
        while (board[i][j] != color) {
            flip(i, j);
            System.out.println("\n     Flipping: (" + i + ", " + (7 -j) + ")");
            if (i < row) { //iterate left
                i--;
                if (j < col) { //left up
                    j--;
                }
                else if (j > col) { //left down
                    j++;
                }
            }
            else if (i > row) { //iterate right
                i++;
                if (j < col) { //right up
                    j--;
                }
                else if (j > col) { //right down
                    j++;
                }
            }
            else { //iterate vertically
                if (j < col) {
                    j--; //up
                }
                else if (j > col) { //search down
                    j++;
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
            player[1].addFlipCount(); // [PLAYER 2] White claims the black item

        }
        else if (board[row][col] == 2) {
            board[row][col] = 1;
            player[0].addFlipCount(); // [PLAYER 1] black claims the white item
        }
    }


    /*
     * given a row, col, and color, checks which edge the spot is and scans appropriate directions according to edge location
     * if scanDirection returns true, it will then call flipLine in the same direction and set madeFlip to true
     * returns true if at least 1 flip was ever made (false otherwise)
     */
    public boolean handleEdge (int row, int col, int color) {
        boolean isValid;
        boolean madeFlip = false;
        int oppositeColor;
        if (color == 1) {
            oppositeColor = 2;
        }
        else {
            oppositeColor = 1;
        }
        if (col == 0) { //top edge of board
            if (row == 0) { //top left corner case
               if (board[0][1] == oppositeColor) {
                   isValid = scanDirection(row, col, 0, 1, color);
                   if (isValid) {
                       flipLine(row, col, 0, 1, color);
                       madeFlip = true;
                   }
               }
               if (board[1][1] == oppositeColor) {
                   isValid = scanDirection(row, col, 1, 1, color);
                   if (isValid) {
                       flipLine(row, col, 1, 1, color);
                       madeFlip = true;
                   }
               }
                if (board[1][0] == oppositeColor) {
                    isValid = scanDirection(row, col, 1, 0, color);
                    if (isValid) {
                        flipLine(row, col, 1, 0, color);
                        madeFlip = true;
                    }
                }
            }
            else if (row == 7) { //top right corner case
                if (board[6][0] == oppositeColor) {
                    isValid = scanDirection(row, col, 6, 0, color);
                    if (isValid) {
                        flipLine(row, col, 6, 0, color);
                        madeFlip = true;
                    }
                }
                if (board[6][1] == oppositeColor) {
                    isValid = scanDirection(row, col, 6, 1, color);
                    if (isValid) {
                        flipLine(row, col, 6, 1, color);
                        madeFlip = true;
                    }
                }
                if (board[7][1] == oppositeColor) {
                    isValid = scanDirection(row, col, 7, 1, color);
                    if (isValid) {
                        flipLine(row, col, 7, 1, color);
                        madeFlip = true;
                    }
                }
            }
            else { //anything else on the top row
                for (int i = row - 1; i < row + 2; i++) {
                    for (int j = col; j < col + 2; j++) {
                        if (board[i][j] == oppositeColor) {
                            isValid = scanDirection(row, col, i, j, color);
                            if (isValid) {
                                flipLine(row, col, i, j, color);
                                madeFlip = true;
                            }

                        }
                    }
                }
            }
        }
        else if (col == 7) { //bottom edge of board
            if (row == 0) { //bottom left corner case
                if (board[0][6] == oppositeColor) {
                    isValid = scanDirection(row, col, 0, 6, color);
                    if (isValid) {
                        flipLine(row, col, 0, 6, color);
                        madeFlip = true;
                    }
                }
                if (board[1][6] == oppositeColor) {
                    isValid = scanDirection(row, col, 1, 6, color);
                    if (isValid) {
                        flipLine(row, col, 1, 6, color);
                        madeFlip = true;
                    }
                }
                if (board[1][7] == oppositeColor) {
                    isValid = scanDirection(row, col, 1, 7, color);
                    if (isValid) {
                        flipLine(row, col, 1, 7, color);
                        madeFlip = true;
                    }
                }
            }
            else if (row == 7) { //bottom right corner case
                if (board[6][7] == oppositeColor) {
                    isValid = scanDirection(row, col, 6, 7, color);
                    if (isValid) {
                        flipLine(row, col, 6, 7, color);
                        madeFlip = true;
                    }
                }
                if (board[6][6] == oppositeColor) {
                    isValid = scanDirection(row, col, 6, 6, color);
                    if (isValid) {
                        flipLine(row, col, 6, 6, color);
                        madeFlip = true;
                    }
                }
                if (board[7][6] == oppositeColor) {
                    isValid = scanDirection(row, col, 7, 6, color);
                    if (isValid) {
                        flipLine(row, col, 7, 6, color);
                        madeFlip = true;
                    }
                }
            }
            else {
                for (int i = row - 1; i < row + 2; i++) {
                    for (int j = col - 1; j < col + 1; j++) {
                        if (board[i][j] == oppositeColor) {
                            isValid = scanDirection(row, col, i, j, color);
                            if (isValid) {
                                flipLine(row, col, i, j, color);
                                madeFlip = true;
                            }

                        }
                    }
                }
            }
        }
        else if (row == 0) { //left edge (corner cases already considered
            for (int i = row ; i < row + 2; i++) {
                for (int j = col - 1; j < col + 2; j++) {
                    if (board[i][j] == oppositeColor) {
                        isValid = scanDirection(row, col, i, j, color);
                        if (isValid) {
                            flipLine(row, col, i, j, color);
                            madeFlip = true;
                        }

                    }
                }
            }
        }
        else { //right edge
            for (int i = row - 1 ; i < row + 1; i++) {
                for (int j = col - 1; j < col + 2; j++) {
                    if (board[i][j] == oppositeColor) {
                        isValid = scanDirection(row, col, i, j, color);
                        if (isValid) {
                            flipLine(row, col, i, j, color);
                            madeFlip = true;
                        }

                    }
                }
            }
        }
        return madeFlip;
    }


    /*
     * loops through board and determines winner
     * returns 1 if black wins, 2 if white wins, and 0 if tie
     */
    public int getWinner() {
        int winner;
        int numBlacks = 0;
        int numWhites = 0;
        for (int j = 0; j < 8; j++) { //for each row
            for (int i = 0; i < 8; i++) { //for each column
                if (board[i][j] == 1) { //if square is black, increment count number of blacks
                    numBlacks++;
                }
                else if (board[i][j] == 2) { //if square is white, increment count number of whites
                    numWhites++;
                }
            }
        }
        if (numBlacks > numWhites) {
            winner = 1;
        }
        else if (numWhites > numBlacks) {
            winner = 2;
        }
        else {
            winner = 0; //indicates a tie
        }
        return winner;
    }
}
