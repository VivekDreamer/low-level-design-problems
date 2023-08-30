package com.vivek.ticTacToeLLD;

import com.vivek.ConsoleColorConstants;

public class Main {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        String startGame = game.startGame();
        if(startGame.equalsIgnoreCase("match is draw!!!"))
            System.out.println(ConsoleColorConstants.YELLOW_BOLD + startGame + ConsoleColorConstants.RESET);
        else
            System.out.println(ConsoleColorConstants.GREEN_BOLD+"game winner is : "+game.startGame()+ConsoleColorConstants.RESET);
    }
}
