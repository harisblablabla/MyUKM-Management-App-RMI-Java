/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import config.koneksi;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import object.ILoginAdmin;

/**
 *
 * @author Ryandika
 */
public class LoginAdmin extends UnicastRemoteObject implements ILoginAdmin {
    private String Username, Password;
    private koneksi obj_koneksi = new koneksi();
    
   
    public LoginAdmin()throws Exception
    {
        super();
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
    
    public String getPassword() {
        return Password;
    }
    
    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public int doLogin()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select count(*) as jumlah from Person.Admin where Username=? and Password=?";
            //pake prepare statment karena ada parameter
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, getUsername());
            ps.setString(2, getPassword());
            
            ResultSet r = ps.executeQuery();
            int jumlah = 0;
            if(r.next())
            {
                jumlah = r.getInt("jumlah");
            }
            return jumlah;          
        } 
        catch (Exception e) 
        {
        }
        return 0;
    }
    
    public String[] caridataAdmin()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select Username from Person.Admin where Username=? AND Password=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, Username);
            ps.setString(2, Password);
            
            ResultSet r = ps.executeQuery();
            String[] data = new String[1];
            if(r.next())
            {
                data[0]= r.getString("Username");
            }
            else
            {
               data[0]= "kosong";
            }
            return data;          
        } 
        catch (Exception e) 
        {
            
        }
        return null;
    }
}
