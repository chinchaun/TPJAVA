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
public class Piece {
        
    public static int savePiece(Models.Piece piece, int gameId, int playerId){
        
        ResultSet rs=null;
        PreparedStatement stmt=null;
        int pieceId = 0;
        try {

            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                            "insert into piece(type, posx, posy, gameid, playerid) values (?, ?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS
                       );
            stmt.setInt(1, piece.getType().getValue());
            stmt.setInt(2, piece.getX());
            stmt.setInt(3, piece.getY());
            stmt.setInt(4, gameId);
            stmt.setInt(5, playerId);
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

    
    public static List<Models.Piece> getByGameIdAndPlayerid(int gameId, int playerId) throws SQLException{
        ResultSet rs=null;
        PreparedStatement stmt=null;
        List<Models.Piece> pieces = new ArrayList<>();
        
        try {
            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                        "SELECT * FROM piece where gameid = ? AND playerid = ?;");
            stmt.setInt(1, gameId);
            stmt.setInt(2, playerId);
            rs = stmt.executeQuery();
            while(rs.next()){
                int pieceType = rs.getInt("type");
                int posX = rs.getInt("posx");
                int posY = rs.getInt("posy");
                switch(pieceType){
                        case(1):
                            Models.Pawn piece = new Models.Pawn(true, posX, posY);
                            pieces.add(piece);
                            break;
                        case(2):
                            Models.Bishop piece2 = new Models.Bishop(true, posX, posY);
                            pieces.add(piece2);
                            break;
                        case(3):
                            Models.Rook piece3 = new Models.Rook(true, posX, posY);
                            pieces.add(piece3);
                            break;  
                        case(4):
                            Models.Knight piece4 = new Models.Knight(true, posX, posY);
                            pieces.add(piece4);
                            break;  
                        case(5):
                            Models.Queen piece5 = new Models.Queen(true, posX, posY);
                            pieces.add(piece5);
                            break;
                        case(6):
                            Models.King piece6 = new Models.King(true, posX, posY);
                            pieces.add(piece6);
                            break;  
                }
                
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
        return pieces;
    };

  
}
