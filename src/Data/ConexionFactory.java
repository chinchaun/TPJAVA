/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jjdelannoy
 */
public class ConexionFactory {
    private String dbDriver="com.mysql.jdbc.Driver";
    private String host="localhost";
    private String port="3306";
    private String user="julian";
    private String pass="1234";
    private String db="ajedrez";
    private Connection conn;
    private static ConexionFactory instancia;
	
    private ConexionFactory(){
        try {
                Class.forName(dbDriver);
                conn=null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static ConexionFactory getInstancia(){
        if (instancia==null){
                instancia = new ConexionFactory();
        }
        return instancia;
    }
	
    public Connection getConn(){
        try {
            if(conn==null || conn.isClosed()){
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+pass);
            }
        } 
        catch (SQLException e) {
                e.printStackTrace();
        }
        return conn;
    }
		
    public void releaseConn(){
        try {
	conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
