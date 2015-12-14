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
public class Spot {
    int x;
    int y;
    Piece piece;

    public Spot(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        piece = null;
    }

    public Boolean occupySpot(Piece piece){
        Boolean response = false;
        if(this.piece != null){
            this.piece.setAvailable(false);
            if(this.piece instanceof King){
                response = true;
            }
        }
        this.piece = piece;
        return response;
    }

    public boolean isOccupied() {
        if(piece != null){
            return true;
        };
        return false;
    }

    public Piece releaseSpot() {
        Piece releasedPiece = this.piece;
        this.piece = null;
        return releasedPiece;
    }

}
