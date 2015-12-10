/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jjdelannoy
 */
public static class Player {
    public static Models.Player getPlayerById(int dni){
        ResultSet rs=null;
        PreparedStatement stmt=null;
        Models.Player player = null;     

        try {
            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                        "select dni from player where dni = ?");
            stmt.setInt(1, dni);
            rs = stmt.executeQuery();
            if(rs !=null && rs.next()){
                player = new Models.Player(rs.getInt("dni"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
                try {
                        if(rs!=null)rs.close();
                        if(stmt!=null) stmt.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                ConexionFactory.getInstancia().releaseConn();
        }
        return player;
    }
}
