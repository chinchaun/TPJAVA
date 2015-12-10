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
public class Rook extends Piece{

    public Rook(boolean available, int x, int y) {
        super(available, x, y);
        // TODO Auto-generated constructor stub
    }


    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        if(super.isValid(board, fromX, fromY, toX, toY) == false)
            return false;
        if(toX == fromX)
            return true;
        if(toY == fromY)
            return true;
        return false;
    }
}