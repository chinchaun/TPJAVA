/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author jjdelannoy
 */
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Extend HttpServlet class
@WebServlet("/game")
public class Game extends HttpServlet {
private Logic.GameController gameCtrl;
private Gson gson;

  public void init() throws ServletException
  {
      // Do required initialization
      this.gameCtrl = new Logic.GameController();
      this.gson = new Gson();
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      Boolean isNewGame = Boolean.valueOf( request.getParameter("isNewGame").toString());
      
      if(isNewGame){
          gameCtrl.InitializeGame(true, Integer.parseInt(request.getParameter("id1")) ,  Integer.parseInt(request.getParameter("id2")));
      }
      else{
         Integer gameId = Integer.parseInt(request.getParameter("gameId"));
         gameCtrl.setGame(Data.Game.getById(gameId));
          try {
              gameCtrl.getGame().getWhite().setPieces(Data.Piece.getByGameIdAndPlayerid(gameId, gameCtrl.getGame().getWhite().getId()));
              gameCtrl.getGame().getBlack().setPieces(Data.Piece.getByGameIdAndPlayerid(gameId, gameCtrl.getGame().getBlack().getId()));
          } catch (SQLException ex) {
              Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
         
      };
      
     
     response.setContentType("application/json");
                response.setHeader("Cache-Control", "nocache");
        	response.setCharacterEncoding("utf-8");
                String json = this.gson.toJson(this.gameCtrl.getGame());	
                response.getWriter().write(json.toString());
  }
  
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {  
     StringBuffer sb = new StringBuffer();
 
    try 
    {
      BufferedReader reader = request.getReader();
      String line = null;
      while ((line = reader.readLine()) != null)
      {
        sb.append(line);
      }
    } catch (Exception e) { e.printStackTrace(); }
 
    JSONParser parser = new JSONParser();
    JSONObject joUser = null;
    try
    {
      joUser = (JSONObject) parser.parse(sb.toString());
      Gson gsonObj = new Gson();
      this.gameCtrl.setGame(gsonObj.fromJson(joUser.get("game").toString(), Models.Game.class));
    } catch (ParseException e) { 
        e.printStackTrace(); 
    }
    
    this.gameCtrl.saveGame();
    
    response.setStatus(200);
    response.getWriter().write("Game Saved!");
 
  }
  
  public void destroy()
  {
      // do nothing.
  }
}

