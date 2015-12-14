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
public class Piece {
        
    public static int savePiece(Models.Piece piece){
        
        ResultSet rs=null;
        PreparedStatement stmt=null;
        int pieceId = 0;
        try {

            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                            "insert into piece(type, posx, posy) values (?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS
                       );
            stmt.setInt(1, piece.getType().getValue());
            stmt.setInt(2, piece.getX());
            stmt.setInt(3, piece.getY());
            stmt.executeUpdate();
            ResultSet rsId = stmt.getGeneratedKeys();
            rsId.next();
            pieceId = rsId.getInt(1);

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
        return pieceId;
    };

  
}
