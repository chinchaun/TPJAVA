/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jjdelannoy
 */
public class Player {
    public final int PAWNS = 8;
    public final int BISHOPS = 2;
    public final int ROOKS = 2;
    public int dni;

    private List<Piece> pieces = new ArrayList<>();

    public Player(int dni){
        super();
        this.dni = dni;
    }
    
    public Player(int dni, List<Piece> pieces) {
        super();
        this.pieces = pieces;
        this.dni = dni;
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }
    
    public void setPieces(List<Piece> pieces){
        this.pieces = pieces;
    }
    
    public List<Piece> getPiecesAviables(){
        List<Piece> aviablePieces = new ArrayList<>();
        for (Piece piece : this.pieces) {
            if(piece.isAvailable()){
                aviablePieces.add(piece);
            }
        }
        return aviablePieces;
    };
    
    public int getDni(){
        return this.dni;
    }

    
}