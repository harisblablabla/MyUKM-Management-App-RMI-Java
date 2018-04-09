/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.server.UnicastRemoteObject;
import config.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import object.ICoach2;

/**
 *
 * @author Haris
 */
public class Coach extends UnicastRemoteObject implements ICoach2 {
    private int CoachID;
    private String CoachName,CoachGender,CoachAddress,CoachPhoneNumber;
    private koneksi obj_koneksi = new koneksi();
    
    public Coach()throws Exception
    {
        super();
    }

    public int getCoachID() {
        return CoachID;
    }

    public void setCoachID(int CoachID) {
        this.CoachID = CoachID;
    }

    public String getCoachName() {
        return CoachName;
    }

    public void setCoachName(String CoachName) {
        this.CoachName = CoachName;
    }

    public String getCoachGender() {
        return CoachGender;
    }

    public void setCoachGender(String CoachGender) {
        this.CoachGender = CoachGender;
    }
    
    public String getCoachAddress() {
        return CoachAddress;
    }

    public void setCoachAddress(String CoachAddress) {
        this.CoachAddress = CoachAddress;
    }
    
    public String getCoachPhoneNumber() {
        return CoachPhoneNumber;
    }

    public void setCoachPhoneNumber(String CoachPhoneNumber) {
        this.CoachPhoneNumber = CoachPhoneNumber;
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
            String str = "insert into Person.Coach(CoachName,CoachGender,CoachAddress,CoachPhoneNumber) values(?,?,?,?)";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, CoachName);
            pr.setString(2, CoachGender);
            pr.setString(3, CoachAddress);
            pr.setString(4, CoachPhoneNumber);
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
            String str = "update Person.Coach set CoachName = ?," +
                    "CoachGender = ?, " +
                    "CoachAddress = ?, " +
                    "CoachPhoneNumber = ? " +
                    "where CoachID = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, CoachName);
            pr.setString(2, CoachGender);
            pr.setString(3, CoachAddress);
            pr.setString(4, CoachPhoneNumber);
            pr.setInt(5, CoachID);
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
         String str = "delete Person.Coach where CoachID= ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setInt(1, CoachID);
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
             String str = "select CoachID,CoachName,CoachGender,CoachAddress,CoachPhoneNumber from Person.Coach";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setCoachID(rs.getInt(1));
                 this.setCoachName(rs.getString(2));
                 this.setCoachGender(rs.getString(3));
                 this.setCoachAddress(rs.getString(4));
                 this.setCoachPhoneNumber(rs.getString(5));
                 data.add(this.getCoachID());
                 data.add(this.getCoachName());
                 data.add(this.getCoachGender());
                 data.add(this.getCoachAddress());
                 data.add(this.getCoachPhoneNumber());
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
            String str = "select * from Person.Coach where CoachID = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setInt(1, CoachID);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                this.setCoachID(rs.getInt(1));
                this.setCoachName(rs.getString(2));
                this.setCoachGender(rs.getString(3));
                this.setCoachAddress(rs.getString(4));
                this.setCoachPhoneNumber(rs.getString(5));
                data.add(this.getCoachID());
                data.add(this.getCoachName());
                data.add(this.getCoachGender());
                data.add(this.getCoachAddress());
                data.add(this.getCoachPhoneNumber());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
}
