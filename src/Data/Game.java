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
public class Game {
    
    public static List<Models.Game> getAll(){
        ResultSet rs=null;
        PreparedStatement stmt=null;
        List<Models.Game> games = new ArrayList<>();
        
        try {
            
            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                    "SELECT a.id, b.dni as 'bdni', c.dni as 'cdni' , a.idplayer1, a.idplayer2 FROM game a INNER JOIN player b ON a.idplayer1 = b.id INNER JOIN player c ON a.idplayer2 = c.id;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Models.Player player1 = new Models.Player(rs.getInt("bdni"));
                player1.setId(rs.getInt("idplayer1"));
                Models.Player player2 = new Models.Player(rs.getInt("cdni"));
                player2.setId(rs.getInt("idplayer2"));
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
    
    public static int saveGame(Models.Game  game){
        
        ResultSet rs=null;
        PreparedStatement stmt=null;
        int idgame = 0;

        try {

            stmt = ConexionFactory.getInstancia().getConn().prepareStatement(
                            "insert into game(idplayer1, idplayer2, turn) values (?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS
                       );
            stmt.setInt(1, game.getWhite().getId());
            stmt.setInt(2, game.getBlack().getId());
            stmt.setInt(3, game.getTurn());
            stmt.executeUpdate();
            ResultSet rsId = stmt.getGeneratedKeys();
            rsId.next();
            idgame = rsId.getInt(1);


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
         return idgame;
    };
    
   
}
