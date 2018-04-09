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
import object.ISchedule;

/**
 *
 * @author Haris
 */
public class Schedule extends UnicastRemoteObject implements ISchedule {
    private String ScheduleID,Day,StartTime,EndTime,UKMName,Description,CoachName,PlaceName;
    int UKMPlaceID;
    private koneksi obj_koneksi = new koneksi();
    
    public Schedule()throws Exception
    {
        super();
    }

    public String getScheduleID() {
        return ScheduleID;
    }

    public void setScheduleID(String ScheduleID) {
        this.ScheduleID = ScheduleID;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }
    
      public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }
    
    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }
    
     public int getUKMPlaceID() {
        return UKMPlaceID;
    }

    public void setUKMPlaceID(int UKMPlaceID) {
        this.UKMPlaceID = UKMPlaceID;
    }
    
    public String getUKMName() {
        return UKMName;
    }

    public void setUKMName(String UKMName) {
        this.UKMName = UKMName;
    }
    
    public String getCoachName() {
        return CoachName;
    }

    public void setCoachName(String CoachName) {
        this.CoachName = CoachName;
    }
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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
            String str = "exec pcd_schedule ?,?,?,?,?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, Day);
            pr.setString(2, StartTime);
            pr.setString(3, EndTime);
            pr.setInt(4, UKMPlaceID);
            pr.setString(5, UKMName);
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
            String str = "exec pcd_updschedule ?,?,?,?,?,?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, ScheduleID);
            pr.setString(2, Day);
            pr.setString(3, StartTime);
            pr.setString(4, EndTime);
            pr.setInt(5, UKMPlaceID);
            pr.setString(6, UKMName);
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
         String str = "exec pcdDELSchedule ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setString(1, ScheduleID);
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
             String str = "select ScheduleID,Day,StartTime,EndTime,UKMPlaceID,UKMName from UKM.Schedule";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setScheduleID(rs.getString(1));
                 this.setDay(rs.getString(2));
                 this.setStartTime(rs.getString(3));
                 this.setEndTime(rs.getString(4));
                 this.setUKMPlaceID(rs.getInt(5));
                 this.setUKMName(rs.getString(6));
                 data.add(this.getScheduleID());
                 data.add(this.getDay());
                 data.add(this.getStartTime());
                 data.add(this.getEndTime());
                 data.add(this.getUKMPlaceID());
                 data.add(this.getUKMName());
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
         return data;
    }
    
    public ArrayList displayviewschedule()
    {
         ArrayList data = new ArrayList();
         try
         {
             obj_koneksi.openConnection();
             Statement stmt = obj_koneksi.con.createStatement();
             String str = "select UKMName,Description,CoachName,Day,StartTime,EndTime,PlaceName from vwukmdetail";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setUKMName(rs.getString(1));
                 this.setDescription(rs.getString(2));
                 this.setCoachName(rs.getString(3));
                 this.setDay(rs.getString(4));
                 this.setStartTime(rs.getString(5));
                 this.setEndTime(rs.getString(6));
                 this.setPlaceName(rs.getString(7));
                 data.add(this.getUKMName());
                 data.add(this.getDescription());
                 data.add(this.getCoachName());
                 data.add(this.getDay());
                 data.add(this.getStartTime());
                 data.add(this.getEndTime());
                 data.add(this.getPlaceName());
                 
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
         return data;
    }
    
    
//    
    public ArrayList getRecord()
    {
        ArrayList data = new ArrayList();
        try
        {
            obj_koneksi.openConnection();
            String str = "select * from UKM.Schedule where ScheduleID = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, ScheduleID);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setScheduleID(rs.getString(1));
                 this.setDay(rs.getString(2));
                 this.setStartTime(rs.getString(3));
                 this.setEndTime(rs.getString(4));
                 this.setUKMPlaceID(rs.getInt(5));
                 this.setUKMName(rs.getString(6));
                 data.add(this.getScheduleID());
                 data.add(this.getDay());
                 data.add(this.getStartTime());
                 data.add(this.getEndTime());
                 data.add(this.getUKMPlaceID());
                 data.add(this.getUKMName());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
