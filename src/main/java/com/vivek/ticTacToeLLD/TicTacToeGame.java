package com.vivek.ticTacToeLLD;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.vivek.ConsoleColorConstants;
import com.vivek.ticTacToeLLD.model.Board;
import com.vivek.ticTacToeLLD.model.Pair;
import com.vivek.ticTacToeLLD.model.PieceType;
import com.vivek.ticTacToeLLD.model.Player;
import com.vivek.ticTacToeLLD.model.PlayingPieceO;
import com.vivek.ticTacToeLLD.model.PlayingPieceX;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;
    public TicTacToeGame() {
        initializeGame();
    }
    private void initializeGame() {
        //here we are creating only two player...
        //however you can add more than two players
        System.out.println("Let's play Tic Tac Toe!");
        players = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int size;
        do{
            System.out.print("Enter the grid size in which you want to play : ");
            size = scanner.nextInt();
            if (size <= 1) {
                System.out.println(ConsoleColorConstants.RED_BOLD+"Please enter a board size greater than 1."+ConsoleColorConstants.RESET);
            }
        }while(size <= 1);
        
        System.out.println();
        System.out.print("Player 1, what is your name? ");
        String player1Name = scanner.next();
        System.out.println();
        System.out.print("Player 2, what is your name? ");
        String player2Name = scanner.next();
        System.out.println();
        PlayingPieceX crossPiece = new PlayingPieceX();
        PlayingPieceO zeroPiece  = new PlayingPieceO();
        Player player1 = new Player(player1Name, crossPiece);
        Player player2 = new Player(player2Name, zeroPiece);
        players.add(player1);
        players.add(player2);
        //initializing board 
        gameBoard = new Board(size);
    }
    
    public String startGame(){
        boolean noWinnerFound = true;
        while(noWinnerFound){
            //get the player whose turn is and also put the player in the list back
            Player playerTurn = players.removeFirst();
            System.out.println("Board: ");
            //get the free space from the board
            gameBoard.printBoard();
            List<Pair> freeSpaces = gameBoard.getFreeCells();
            if(freeSpaces.isEmpty()){
                noWinnerFound = false;
                continue;
            }

            //reading user input
            Scanner sc = new Scanner(System.in);
            System.out.println(playerTurn.getName()+"'s Turn ("+playerTurn.getPlayingPiece().pieceType+")");
            int inputRow;
            int inputCol;
            try
            {
                System.out.print("Enter a row number :" );
                inputRow = sc.nextInt();
                if(!isValidInput(inputRow, gameBoard.size-1)){
                    System.out.println(ConsoleColorConstants.RED+"please enter row number from 0 to "+(gameBoard.size-1)+ConsoleColorConstants.RESET);
                    players.addFirst(playerTurn);
                    continue;
                }
                System.out.print("Enter a column number :" );
                inputCol = sc.nextInt();
                if(!isValidInput(inputCol, gameBoard.size-1)){
                    System.out.println(ConsoleColorConstants.RED+"please enter column number from 0 to "+(gameBoard.size-1)+ConsoleColorConstants.RESET);
                    players.addFirst(playerTurn);
                    continue;
                }
            }catch(Exception e){
                System.out.println(ConsoleColorConstants.RED+"please, provide the integer only"+ConsoleColorConstants.RESET);
                players.addFirst(playerTurn);
                continue;
            }
            //placing thee piece
            boolean isPieceAddedSuccessfully = gameBoard.addPiece(inputRow, inputCol, playerTurn.playingPiece);
            if(!isPieceAddedSuccessfully){
                //player cannot insert the piece into this cell, player has to choose another cell
                System.out.println(ConsoleColorConstants.RED+"this position is already filled, try again..."+ConsoleColorConstants.RESET);
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);
            boolean winner = isWinner(inputRow,inputCol,playerTurn.playingPiece.pieceType);
            if(winner){
                gameBoard.printBoard();
                return playerTurn.name;
            }
        }
        return "match is draw!!!";
    }
    private boolean isWinner(int row, int col, PieceType pieceType) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //checking row first
        for(int i = 0; i < gameBoard.size; i++){
            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) rowMatch = false;
        }

        //checking col second
        for(int i = 0; i < gameBoard.size; i++){
            if(gameBoard.board[i][col] == null || gameBoard.board[i][col].pieceType != pieceType) colMatch = false;
        }

        //checking diagonal now
        for(int i = 0 ,j = 0; i < gameBoard.size; i++,j++){
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) diagonalMatch = false;
        }

        //checking anti-diagonal 
        for(int i = 0 ,j = gameBoard.size-1; i < gameBoard.size; i++,j--){
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) antiDiagonalMatch = false;
        }
        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;
    }

    private boolean isValidInput(int x, int size){
        return x <= size ? true : false;
    }
}
