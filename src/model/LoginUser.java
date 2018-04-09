/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import config.koneksi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import object.ILoginUser;

/**
 *
 * @author Ryandika
 */
public class LoginUser extends UnicastRemoteObject implements ILoginUser{
    
    private int ID;
    private String StudentName, Gender, Address, PhoneNumber, Faculty, Major, Batch, Uname, Pword, UKMName;
    private koneksi obj_koneksi = new koneksi();
    
   
    public LoginUser()throws Exception
    {
        super();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }
    
    public String getGender() {
        return Gender;
    }
    
    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    
    public String getAddress() {
        return Address;
    }
    
    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
    
    public String getFaculty() {
        return Faculty;
    }
    
    public void setFaculty(String Faculty) {
        this.Faculty = Faculty;
    }
    
    public String getMajor() {
        return Major;
    }
    
    public void setMajor(String Major) {
        this.Major = Major;
    }
    
    public String getBatch() {
        return Batch;
    }
    
    public void setBatch(String Batch) {
        this.Batch = Batch;
    }
    
    public String getUname() {
        return Uname;
    }
    
    public void setUname(String Uname) {
        this.Uname = Uname;
    }
    
    public String getPword() {
        return Pword;
    }
    
    public void setPword(String Pword) {
        this.Pword = Pword;
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
    
    public int doLogin()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select count(*) as jumlah from Person.Student where Uname=? and Pword=?";
            //pake prepare statment karena ada parameter
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, getUname());
            ps.setString(2, getPword());
            
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
            String sq = "select Uname from Person.Student where Uname=? AND Pword=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, Uname);
            ps.setString(2, Pword);
            
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
    
    public int doInsert()
    {
        int i = 0 ;
        try
        {
            obj_koneksi.openConnection();
            String str = "insert into Person.Student(StudentName,Gender,Address,PhoneNumber,Faculty,Major,Batch,Uname,Pword) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, StudentName);
            pr.setString(2, Gender);
            pr.setString(3, Address);
            pr.setString(4, PhoneNumber);
            pr.setString(5, Faculty);
            pr.setString(6, Major);
            pr.setString(7, Batch);
            pr.setString(8, Uname);
            pr.setString(9, Pword);
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
            String str = "update Person.Student set StudentName = ?," +
                    "Gender = ?, " +
                    "Address = ?, " +
                    "PhoneNumber = ?, " +
                    "Faculty = ?, " +
                    "Major = ?, " +
                    "Batch = ?, " +
                    "Uname = ?, " +
                    "Pword = ?, " +
                    "where ID = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, StudentName);
            pr.setString(2, Gender);
            pr.setString(3, Address);
            pr.setString(4, PhoneNumber);
            pr.setString(5, Faculty);
            pr.setString(6, Major);
            pr.setString(7, Batch);
            pr.setString(8, Uname);
            pr.setString(9, Pword);
            pr.setInt(10, ID);
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
         String str = "delete Person.Student where ID= ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setInt(1, ID);
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
             String str = "select ID,StudentName,Gender,Address,PhoneNumber,Faculty,Major,Batch,Uname,Pword from Person.Student";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setID(rs.getInt(1));
                 this.setStudentName(rs.getString(2));
                 this.setGender(rs.getString(3));
                 this.setAddress(rs.getString(4));
                 this.setPhoneNumber(rs.getString(5));
                 this.setFaculty(rs.getString(6));
                 this.setMajor(rs.getString(7));
                 this.setBatch(rs.getString(8));
                 this.setUname(rs.getString(9));
                 this.setPword(rs.getString(10));
                 data.add(this.getID());
                 data.add(this.getStudentName());
                 data.add(this.getGender());
                 data.add(this.getAddress());
                 data.add(this.getPhoneNumber());
                 data.add(this.getFaculty());
                 data.add(this.getMajor());
                 data.add(this.getBatch());
                 data.add(this.getUname());
                 data.add(this.getPword());
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
         return data;
    }
    
    public ArrayList displayviewstudent()
    {
         ArrayList data = new ArrayList();
         try
         {
             obj_koneksi.openConnection();
             Statement stmt = obj_koneksi.con.createStatement();
             String str = "select StudentName,Faculty,Major,Batch,UKMName from vwstudentukm";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setStudentName(rs.getString(1));
                 this.setFaculty(rs.getString(2));
                 this.setMajor(rs.getString(3));
                 this.setBatch(rs.getString(4));
                 this.setUKMName(rs.getString(5));
                 data.add(this.getStudentName());
                 data.add(this.getFaculty());
                 data.add(this.getMajor());
                 data.add(this.getBatch());
                 data.add(this.getUKMName());
                 
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
         return data;
    }
    
    public ArrayList getRecord(String search)
    {
        ArrayList data = new ArrayList();
        try
        {
            obj_koneksi.openConnection();
            String str = "select * from vwstudentukm where StudentName like ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, "%"+search+"%");
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setStudentName(rs.getString(1));
                 this.setFaculty(rs.getString(2));
                 this.setMajor(rs.getString(3));
                 this.setBatch(rs.getString(4));
                 this.setUKMName(rs.getString(5));
                 data.add(this.getStudentName());
                 data.add(this.getFaculty());
                 data.add(this.getMajor());
                 data.add(this.getBatch());
                 data.add(this.getUKMName());          }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
    public String[] caridataAdmin()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select Uname from Person.Student where Uname=? AND Pword=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, Uname);
            ps.setString(2, Pword);
            
            ResultSet r = ps.executeQuery();
            String[] data = new String[2];
            if(r.next())
            {
                data[0]= r.getString("Uname");
                data[1]= r.getString("Pword");
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
