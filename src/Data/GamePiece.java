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
public class GamePiece {
    
    public static List<Models.Game> getAllByGameId(int gameId){
        ResultSet rs=null;
        PreparedStatement stmt=null;
        List<Models.Game> games = new ArrayList<>();
        
        try {
            
            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                        "select id, idgame, idplayer1, idplayer2, turn, idpiece from game_piece;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Models.Player player1 = Player.getPlayerById(rs.getInt("id"));
                Models.Player player2 = Player.getPlayerById(rs.getInt("id"));
                
                Models.Game game = new Models.Game(rs.getInt("id"), player1, player2);
                games.add(game); 
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
        
        return games;
    };
    
    public static void saveGamePiece(Models.Game game, int pieceId){
        
        ResultSet rs=null;
        PreparedStatement stmt=null;

        try {

            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                            "insert into game_piece(idgame, idplayer1, idplayer2, idpiece) values (?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS
                       );
            stmt.setInt(1, game.getId());
            stmt.setInt(2, game.getWhite().getId());
            stmt.setInt(3, game.getBlack().getId());
            stmt.setInt(5, pieceId);
            stmt.execute();

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
    };
}
