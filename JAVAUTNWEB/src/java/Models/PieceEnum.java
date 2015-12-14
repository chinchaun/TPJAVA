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
public enum PieceEnum {
    //Types
    //1 Pawn
    //2 BISHOPS
    //3 ROOKS
    //4 Knight
    //5 Queen
    //6 King
    PAWN(1), BISHOP(2), ROOK(3), KNIGHT(4), QUEEN(5), KING(6);
        private final int  value;
        
        private PieceEnum(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
}
