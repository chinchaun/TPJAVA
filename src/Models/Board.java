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
public class Board {
    private final Spot[][] spots = new Spot[8][8];

    public Board() {
        super();
        for(int i=0; i<spots.length; i++){
            for(int j=0; j<spots.length; j++){
                this.spots[i][j] = new Spot(i, j);
            }
        }
    }

    public Spot getSpot(int x, int y) {
        return spots[x][y];
    }

}
