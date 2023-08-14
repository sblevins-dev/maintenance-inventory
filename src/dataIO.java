/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jake
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.sql.*;
import java.util.Vector;



public class dataIO {
    private final String DATABASE_NAME = "gb-manufacturing";
     private final String USER_NAME = "admin"; 
    private final String PASSWORD = "hamBurger1212";
    private final String CONNECTION_STRING = "jdbc:mysql://aws-gb-man.ctkxvwo1jmoa.us-east-1.rds.amazonaws.com:3306/" + DATABASE_NAME;
   
    
    
    public void add(Employee emp) throws ClassNotFoundException, SQLException{
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver"); 
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        String strSQL = "INSERT INTO employee (emp_fname, emp_lname, emp_phone, emp_address, emp_code)" +
                "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(strSQL);
        pstmt.setString(1, emp.getFName());
        pstmt.setString(2, emp.getLName());
        pstmt.setString(3, emp.getPhone());
        pstmt.setString(4, emp.getAddress()); 
        pstmt.setInt(5,emp.getEmpCode());

        pstmt.execute();
        
        con.close();
        
    }
    
    public ArrayList<Employee>getList() throws SQLException{
         ArrayList<Employee> empArr = new ArrayList<Employee>();
       
        //connect
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM employee";
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
            Employee emp = new Employee(); 
           
            emp.setEmployeeID(rs.getInt(1));
            emp.setFName(rs.getString(2));
            emp.setLName(rs.getString(3));
            emp.setPhone(rs.getString(4));
            emp.setAddress(rs.getString(5));
            emp.setEmpCode(rs.getInt(6));
            
            empArr.add(emp);
        }
        con.close();
        
        return empArr;
    }
    public void delete(int empID) throws SQLException{
    Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
    
    String sql = "DELETE FROM employee WHERE emp_id = ?";
    PreparedStatement stmt = con.prepareStatement(sql);
    stmt.setInt(1, empID);
    stmt.execute();
    
    con.close();
    }
    
}
