/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author jonathan
 */
public class connect {
    Connection conn= null;
    public static Connection connect(){
      try{
          Class.forName("org.sqlite.JDBC");
         Connection conn = DriverManager.getConnection("jdbc:sqlite:TimeTables");
          
          return conn;
      }
      catch (Exception e){
          JOptionPane.showMessageDialog(null, e);
          return null;
	  
      }
    
    }    
}
