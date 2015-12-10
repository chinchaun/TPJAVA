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
public static class Game {
    public static int[] getAll(){
        ResultSet rs=null;
        PreparedStatement stmt=null;
        int[] games;
        
        try {
            
            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                        "select id, idplayer1, idplayer2 from games;");
            
            rs = stmt.executeQuery();
            
            if(rs !=null && rs.next()){
                games.
                p = new Partida();
                p.setNroPartida(rs.getInt("nro_partida"));
                p.setTurno(rs.getInt("turno"));
                
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
                
                FactoryConexion.getInstancia().releaseConn();
                
        }
        
        return p;
    };
}
