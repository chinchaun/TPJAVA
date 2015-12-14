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

// Extend HttpServlet class
@WebServlet("/hello")
public class Hello extends HttpServlet {
 
  private String message;

  public void init() throws ServletException
  {
      // Do required initialization
      message = "Hello World";
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
     response.setContentType("application/json");
                response.setHeader("Cache-Control", "nocache");
        	response.setCharacterEncoding("utf-8");

                JSONObject json = new JSONObject();

      try {
          // put some value pairs into the JSON object as into a Map.
          json.put("status", 200);
            json.put("msg", "OK");

                // put a "map" 
                JSONObject map = new JSONObject();
                map.put("key1", "val1");
                map.put("key2", "val2");
                json.put("map", map);
		
                // put an "array"
                JSONArray arr = new JSONArray();
                arr.put(5);
                arr.put(3);
                arr.put(1);
                json.put("arr", arr);

      } catch (JSONException ex) {
          Logger.getLogger(Hello.class.getName()).log(Level.SEVERE, null, ex);
      }
              
                // finally output the json string		
                response.getWriter().write(json.toString());
  }
  
  public void destroy()
  {
      // do nothing.
  }
}

