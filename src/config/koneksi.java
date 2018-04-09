/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Haris
 */
public class koneksi {
    
    public Connection con;
       
      
       public void openConnection() {
           try
           {
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               String url = "jdbc:sqlserver://RYANDIKA-PC\\SQLEXPRESS;databaseName=ManagementUKM";
               con = DriverManager.getConnection(url,"sa","jakartaraya");
           }
            catch(Exception ex)
            {
            ex.printStackTrace();
            }        
       }
}
