/*
 * @authors: Elizabeth Wood & Molly Sugarman
 * @file: Players.java
 * @description: All things pertaining to the player, such as their color + position
 */

import java.util.*;

public class Players {
    int playerNumber, playerCount;
    String playerColor;

    Scanner input = new Scanner(System.in);

    // Default constructor
    Players() {
        playerCount= 0;
    }

    // Constructor
    Players(int playerNum,  String playerCol, int playerCounter) {
        playerNumber= playerNum;
        playerColor= playerCol;
        playerCount = playerCounter;
    }

    public String getPlayerColor(){
        return playerColor;
    }


    public void addPlayerCount(){
        playerCount += 1;
    }

    public int getPlayerCount(){
        return playerCount;
    }



}

class Computer extends  Players{
    Random rand = new Random();
    int randomRow, randomCol;

    // Constructor
    Computer(int playerNumber, String playerColor, int playerC){
        super(playerNumber, playerColor, playerC);
    }

    // Randomly selecting row
    public int selectRow(){
        randomRow = rand.nextInt(8);
        return randomRow;
    }

    // Randomly selecting column
    public int selectCol(){
        randomCol = rand.nextInt(8);
        return randomCol;
    }

}

class Human extends Players{
    int selectedRow, selectedCol;

    // Constructor
    Human(int playerNumber, String playerColor, int playerC){
        super(playerNumber, playerColor, playerC);
    }

    // Prompt user to select a row and set selectedRow accordingly
    public int selectRow(){
        System.out.println("Select a row: ");
        selectedRow = input.nextInt();
        return selectedRow;
    }

    // Prompt user to select a column and set selectedCol accordingly
    public int selectCol(){
        System.out.println("Select a column: ");
        selectedCol = input.nextInt();
        return selectedCol;
    }
}
