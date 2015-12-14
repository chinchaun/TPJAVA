/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jjdelannoy
 */
public  class Player {
    
    public static Models.Player getPlayerById(int id){
        ResultSet rs=null;
        PreparedStatement stmt=null;
        Models.Player player = null;     

        try {
            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                        "select id, dni from player where id = ?");
            stmt.setInt(1, id);
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
    
    public static int savePlayer(Models.Player player){
        
        ResultSet rs=null;
        PreparedStatement stmt=null;
        int idplayer = 0;

        try {

            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                            "insert into player(dni) values (?)",PreparedStatement.RETURN_GENERATED_KEYS
                       );
            stmt.setInt(1, player.getDni());
            stmt.executeUpdate();
            ResultSet rsId = stmt.getGeneratedKeys();
            rsId.next();
            idplayer = rsId.getInt(1);
            


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{

            try {
                if(rs!=null ) rs.close();
                if(stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ConexionFactory.getInstancia().releaseConn();
            }
         return idplayer;
    };
    
   
    
}
