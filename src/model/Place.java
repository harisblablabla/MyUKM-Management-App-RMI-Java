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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import object.IPlace;

/**
 *
 * @author Haris
 */
public class Place extends UnicastRemoteObject implements IPlace {
    private int UKMPlaceID;
    private String PlaceName;
    private koneksi obj_koneksi = new koneksi();
    
    public Place()throws Exception
    {
        super();
    }

    public int getUKMPlaceID() {
        return UKMPlaceID;
    }

    public void setUKMPlaceID(int UKMPlaceID) {
        this.UKMPlaceID = UKMPlaceID;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String PlaceName) {
        this.PlaceName = PlaceName;
    }
    
    public koneksi getObj_koneksi() {
        return obj_koneksi;
    }

    public void setObj_koneksi(koneksi obj_koneksi) {
        this.obj_koneksi = obj_koneksi;
    }
    
    public int doInsert()
    {
        int i = 0 ;
        try
        {
            obj_koneksi.openConnection();
            String str = "insert into UKM.UKMPlace(PlaceName) values(?)";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, PlaceName);
            i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public int doUpdate()
    {
        int i = 0 ;
        try
        {
            obj_koneksi.openConnection();
            String str = "update UKM.UKMPlace set PlaceName = ? where UKMPlaceID = ? ";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, PlaceName);
            pr.setInt(2, UKMPlaceID);
            i = pr.executeUpdate();          
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public int doDelete()
    {
        int i = 0 ;
        try
        {
         obj_koneksi.openConnection();
         String str = "delete UKM.UKMPlace where UKMPlaceID = ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setInt(1, UKMPlaceID);
         i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }

    public ArrayList display()
    {
         ArrayList data = new ArrayList();
         try
         {
             obj_koneksi.openConnection();
             Statement stmt = obj_koneksi.con.createStatement();
             String str = "select UKMPlaceID,PlaceName from UKM.UKMPlace";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setUKMPlaceID(rs.getInt(1));
                 this.setPlaceName(rs.getString(2));
                 data.add(this.getUKMPlaceID());
                 data.add(this.getPlaceName());
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
         return data;
    }
    
    public ArrayList getRecord()
    {
        ArrayList data = new ArrayList();
        try
        {
            obj_koneksi.openConnection();
            String str = "select * from UKM.UKMPlace where UKMPlaceID = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setInt(1, UKMPlaceID);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                this.setUKMPlaceID(rs.getInt(1));
                this.setPlaceName(rs.getString(2));
                data.add(this.getUKMPlaceID());
                data.add(this.getPlaceName());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
}
