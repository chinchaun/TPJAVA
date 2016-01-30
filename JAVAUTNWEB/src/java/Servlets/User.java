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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Extend HttpServlet class
@WebServlet("/user")
public class User extends HttpServlet {
private Models.Player player;
private Gson gson;

  public void init() throws ServletException
  {
      // Do required initialization
      this.player = new Models.Player();
      this.gson = new Gson();
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {

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
    String method = "";
    try
    {
      joUser = (JSONObject) parser.parse(sb.toString());
      Gson gsonObj = new Gson();
      method = (String) joUser.get("method");
      player = gsonObj.fromJson(joUser.get("user").toString(), Models.Player.class);
    } catch (ParseException e) { 
        e.printStackTrace(); 
    }
    
    int status = 404;
    String responseMsg = "";
    
    if(method.equalsIgnoreCase("login")){
        Models.Player dbplayer =  Data.Player.getPlayerByDni(player.getDni());
        if(!dbplayer.getPassword().isEmpty() && dbplayer.getPassword().equalsIgnoreCase(dbplayer.getPassword()) ) {
            dbplayer.setStoredGames(Data.Game.getByUserId(dbplayer.getId()));
            String json = this.gson.toJson(dbplayer);
            status = 200;
            responseMsg = json;
        }
        else{
            status = 401;
            responseMsg = "user or password wrong";
        };
    }
    else{
        int idplayer = Data.Player.signUp(player);
        status = 201;
        responseMsg = String.valueOf(idplayer);
    };
    
    response.setStatus(status);
    response.getWriter().write(responseMsg);
 
  }
  
  public void destroy()
  {
      // do nothing.
  }
}

