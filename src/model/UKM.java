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
import object.IUKM;

/**
 *
 * @author Haris
 */
public class UKM extends UnicastRemoteObject implements IUKM {
    private int UKMID,CoachID;
    private String UKMName,Description;
    private koneksi obj_koneksi = new koneksi();
    
    public UKM()throws Exception
    {
        super();
    }

    public int getUKMID() {
        return UKMID;
    }

    public void setUKMID(int UKMID) {
        this.UKMID = UKMID;
    }
    
    public String getUKMName() {
        return UKMName;
    }

    public void setUKMName(String UKMName) {
        this.UKMName = UKMName;
    }
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public int getCoachID() {
        return CoachID;
    }

    public void setCoachID(int CoachID) {
        this.CoachID = CoachID;
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
            String str = "insert into UKM.UKM(UKMName,Description,CoachID) values(?,?,?)";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, UKMName);
            pr.setString(2, Description);
            pr.setInt(3, CoachID);
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
            String str = "update UKM.UKM set UKMName = ?," +
                    "Description = ?, " +
                    "CoachID = ? " +
                    "where UKMID = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, UKMName);
            pr.setString(2, Description);
            pr.setInt(3, CoachID);
            pr.setInt(4, UKMID);
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
         String str = "delete UKM.UKM where UKMID= ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setInt(1, UKMID);
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
             String str = "select UKMID,UKMName,Description,CoachID from UKM.UKM";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setUKMID(rs.getInt(1));
                 this.setUKMName(rs.getString(2));
                 this.setDescription(rs.getString(3));
                 this.setCoachID(rs.getInt(4));
                 data.add(this.getUKMID());
                 data.add(this.getUKMName());
                 data.add(this.getDescription());
                 data.add(this.getCoachID());
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
         return data;
    }
    
    public ArrayList displayinschedule()
    {
         ArrayList data = new ArrayList();
         try
         {
             obj_koneksi.openConnection();
             Statement stmt = obj_koneksi.con.createStatement();
             String str = "select UKMID,UKMName from UKM.UKM";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setUKMID(rs.getInt(1));
                 this.setUKMName(rs.getString(2));
                 data.add(this.getUKMID());
                 data.add(this.getUKMName());
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
            String str = "select * from UKM.UKM where UKMID = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setInt(1, UKMID);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setUKMID(rs.getInt(1));
                 this.setUKMName(rs.getString(2));
                 this.setDescription(rs.getString(3));
                 this.setCoachID(rs.getInt(4));
                 data.add(this.getUKMID());
                 data.add(this.getUKMName());
                 data.add(this.getDescription());
                 data.add(this.getCoachID());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
