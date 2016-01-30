/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Models.Bishop;
import Models.Game;
import Models.King;
import Models.Knight;
import Models.Pawn;
import Models.Piece;
import Models.Player;
import Models.Queen;
import Models.Rook;
import Models.Spot;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jjdelannoy
 */
public  class  GameController {
    private Game game;
    
    public GameController(){
    
    };
    
    public Game getGame(){
        return this.game;
    };
    
    public void setGame(Game game){
        this.game = game;
    }
    
    public void InitializeGame(boolean isNewGame, int dni1, int dni2){
        if(isNewGame){
            newGame(dni1, dni2);
        };
    }

    private void newGame(int dni1, int dni2) {
        List<Piece> whitePieces = initializeWhitePlayerPieces();
        List<Piece> blackPieces = initializeBlackPlayerPieces();
        Player WhitePlayer = new Player(dni1, whitePieces);
        Player BlackPlayer = new Player(dni2, blackPieces);
        this.game = new Game(WhitePlayer, BlackPlayer);
    }

    private List<Piece> initializeWhitePlayerPieces() {
        List<Piece> pieces = new ArrayList<>();
        for(int i=0; i<8; i++){
                pieces.add(new Pawn(true,i,1));
            }
            pieces.add(new Rook(true, 0, 0));
            pieces.add(new Rook(true, 7, 0));
            pieces.add(new Bishop(true, 2, 0));
            pieces.add(new Bishop(true, 5, 0));
            pieces.add(new Knight(true, 1, 0));
            pieces.add(new Knight(true, 6, 0));
            pieces.add(new Queen(true, 3, 0));
            pieces.add(new King(true, 4, 0));
            return pieces;
    }

    private List<Piece> initializeBlackPlayerPieces() {
        List<Piece> pieces = new ArrayList<>();
        for(int i=0; i<8; i++){
                pieces.add(new Pawn(true,i,6));
            }
            pieces.add(new Rook(true, 0, 7));
            pieces.add(new Rook(true, 7, 7));
            pieces.add(new Bishop(true, 2, 7));
            pieces.add(new Bishop(true, 5, 7));
            pieces.add(new Knight(true, 1, 7));
            pieces.add(new Knight(true, 6, 7));
            pieces.add(new Queen(true, 3, 7));
            pieces.add(new King(true, 4, 7));
            return pieces;
    }
    
    public String[] MakeMovement(Piece piece, int toX, int toY){
        if(!piece.isValid(this.game.getBoard(), piece.getX(), piece.getY(), toX, toY)){
            String[] response = { "1", "Movimiento Ilegal"};
            return response;
        }
        Spot currentSpot = this.game.getBoard().getSpot(piece.getX(), piece.getY());
        currentSpot.releaseSpot();
        Spot futureSpot = this.game.getBoard().getSpot(toX, toY);
        if(futureSpot.occupySpot(piece)){
            String[]  response = { "2", "Juego Terminado"};
            return response;
        }
        piece.setX(toX);
        piece.setY(toY);
        String[]  response = { "3", "OK"};
        return response;
    }
    
    public void saveGame(int dniWhite, int dniBlack){
        this.game.getWhite().setDni(dniWhite);
        this.game.getBlack().setDni(dniBlack);
        this.game.getWhite().setId(Data.Player.savePlayer(this.game.getWhite()));
        this.game.getBlack().setId(Data.Player.savePlayer(this.game.getBlack()));
        this.game.setId(Data.Game.saveGame(this.game));
        for (Piece piece : this.game.getWhite().getPieces()) {
            piece.setId(Data.Piece.savePiece(piece, this.game.getId(), this.game.getWhite().getId()));
            Data.GamePiece.saveGamePiece(this.game, piece.getId());
        }
        for (Piece piece : this.game.getBlack().getPieces()) {
            piece.setId(Data.Piece.savePiece(piece, this.game.getId(), this.game.getBlack().getId()));
            Data.GamePiece.saveGamePiece(this.game, piece.getId());
        }
        
    }
    
        public void saveGame(){
       // this.game.getWhite().setId(Data.Player.savePlayer(this.game.getWhite()));
        this.game.getBlack().setId(Data.Player.savePlayer(this.game.getBlack()));
        this.game.setId(Data.Game.saveGame(this.game));
        for (Piece piece : this.game.getWhite().getPieces()) {
            piece.setId(Data.Piece.savePiece(piece, this.game.getId(), this.game.getWhite().getId()));
            Data.GamePiece.saveGamePiece(this.game, piece.getId());
        }
        for (Piece piece : this.game.getBlack().getPieces()) {
            piece.setId(Data.Piece.savePiece(piece, this.game.getId(), this.game.getBlack().getId()));
            Data.GamePiece.saveGamePiece(this.game, piece.getId());
        }
        
    }
    
}
