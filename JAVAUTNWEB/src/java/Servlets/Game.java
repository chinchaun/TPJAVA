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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

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
     gameCtrl.InitializeGame(true, Integer.parseInt(request.getParameter("id1")) ,  Integer.parseInt(request.getParameter("id2")));
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
     Object game = this.gson.fromJson(request.getParameter("board"), Models.Game.class);
     //this.gameCtrl.setGame(game);
     //this.gameCtrl.saveGame(this.gameCtrl.getGame().getWhite().getDni(), this.gameCtrl.getGame().getBlack().getDni());
     
  }
  
  public void destroy()
  {
      // do nothing.
  }
}

