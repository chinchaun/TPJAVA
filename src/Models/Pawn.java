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
public class Pawn extends Piece{
    public Pawn(boolean available, int x, int y) {
        super(available, x, y);
         this.setType(PieceEnum.PAWN);
         
    }

    
    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        if(super.isValid(board, fromX, fromY, toX, toY) == false)
            return false;
        boolean valid = false;
        /*
        int xN = Math.abs(toX) - Math.abs(fromX);
        int xB = Math.abs(fromX) - Math.abs(toX);
        int yR = fromY - toY;
        int xD = fromX - toX;
        int yD = fromY - toY;
        if ((((xN==1)||(xN==2)||(xB==1)||(xB==2))&&(yR == 0))||((Math.abs(xD)==1)&&(Math.abs(yD)==1))){
            
            valid = true;
            
        }
        */
        int yMovement = Math.abs(fromY) - Math.abs(toY);
        int xMovement = Math.abs(fromX) - Math.abs(toX);
        int totalMovement = Math.abs(xMovement) + Math.abs(yMovement);
        if(totalMovement < 2)
        {
         valid = true;
        }
        
        return valid;
    }
}