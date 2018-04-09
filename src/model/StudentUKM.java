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
import object.IStudentUKM;

/**
 *
 * @author Haris
 */
public class StudentUKM extends UnicastRemoteObject implements IStudentUKM {
    private String Uname,UKMName;
    private koneksi obj_koneksi = new koneksi();
    
    public StudentUKM()throws Exception
    {
        super();
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String Uname) {
        this.Uname = Uname;
    }

    public String getUKMName() {
        return UKMName;
    }

    public void setUKMName(String UKMName) {
        this.UKMName = UKMName;
    }
    
    public koneksi getObj_koneksi() {
        return obj_koneksi;
    }

    public void setObj_koneksi(koneksi obj_koneksi) {
        this.obj_koneksi = obj_koneksi;
    }
    
    public int doInsertpending()
    {
        int i = 0 ;
        try
        {
            obj_koneksi.openConnection();
            String str = "insert into Person.PendingList(Uname,UKMName) values(?,?)";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, Uname);
            pr.setString(2, UKMName);
            i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public int doInsert()
    {
        int i = 0 ;
        try
        {
            obj_koneksi.openConnection();
            String str = "insert into Person.StudentUKM(Uname,UKMName) values(?,?)";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, Uname);
            pr.setString(2, UKMName);
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
         String str = "delete Person.PendingList where Uname = ? AND UKMName = ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setString(1, Uname);
         pr.setString(2, UKMName);
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
             String str = "select Uname,UKMName from Person.PendingList";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setUname(rs.getString(1));
                 this.setUKMName(rs.getString(2));
                 data.add(this.getUname());
                 data.add(this.getUKMName());
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
         return data;
    }
    
    public int dobidubidu()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select count(*) as jumlah from Person.PendingList where Uname=? and UKMName=?";
            //pake prepare statment karena ada parameter
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, getUname());
            ps.setString(2, getUKMName());
            
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
    
    public String[] cekakses(){
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select Uname from Person.PendingList where Uname=? AND UKMName=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, Uname);
            ps.setString(2, UKMName);
            
            ResultSet r = ps.executeQuery();
            String[] data = new String[1];
            if(r.next())
            {
                data[0]= r.getString("Uname");
            }
            else
            {
               data[0]= "Null";
            }
            return data;          
        } 
        catch (Exception e) 
        {
        }
        return null;
    }
    
    public ArrayList getRecord()
    {
        ArrayList data = new ArrayList();
        try
        {
            obj_koneksi.openConnection();
            String str = "select * from Person.StudentUKM where Uname = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, Uname);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setUname(rs.getString(1));
                 this.setUKMName(rs.getString(2));
                 data.add(this.getUname());
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
