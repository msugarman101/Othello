/*
 * @authors: Elizabeth Wood & Molly Sugarman
 * @file: Players.java
 * @description: All things pertaining to the player, such as their color + position
 */

import java.util.*;

public class Players {
    int playerNumber, playerCount, playerFlipCount;
    String playerColor;

    Scanner input = new Scanner(System.in);

    // Default constructor
    Players() {
        playerCount = 0;
        playerFlipCount = 0;
    }

    // Constructor
    Players(int playerNum, String playerCol, int playerCounter, int playerFlipC) {
        playerNumber = playerNum;
        playerColor = playerCol;
        playerCount = playerCounter;
        playerFlipCount = playerFlipC;
    }

    public String getPlayerColor() {
        return playerColor;
    }


    public void addPlayerCount() {
        playerCount += 1;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void addFlipCount() {
        playerFlipCount += 1;
    }

    public int getFlipCount() {
        return playerFlipCount;
    }
}

