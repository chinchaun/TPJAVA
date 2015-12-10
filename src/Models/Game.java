/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author jjdelannoy
 */
public class Game {
    private Board board;
    private Player white;
    private Player black;
    public Game(Player white, Player black) {
        super();
        this.white = white;
        this.black = black;
        initializeBoardGivenPlayers();
    }

    public void setColorWhite(Player player) {
        this.white = player;
    }

    public void setColorBlack(Player player) {
        this.black = player;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWhite() {
        return white;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public Player getBlack() {
        return black;
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    private void initializeBoardGivenPlayers() {
        this.board = new Board();
        for(int i=0; i<black.getPieces().size(); i++){
            board.getSpot(black.getPieces().get(i).getX(), black.getPieces().get(i).getY()).occupySpot(black.getPieces().get(i));
        }
        for(int i=0; i<white.getPieces().size(); i++){
            board.getSpot(white.getPieces().get(i).getX(), white.getPieces().get(i).getY()).occupySpot(white.getPieces().get(i));
        }
    }

}