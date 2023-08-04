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


public class dataIO {
    private final String DATABASE_NAME = "gb-manufacturing";
    private final String CONNECTION_STRING = "jdbc:mysql://aws-gb-man.ctkxvwo1jmoa.us-east-1.rds.amazonaws.com:3306/" + DATABASE_NAME;
    private final String USER_NAME = "admin"; 
    private final String PASSWORD = "hamBurger1212";
    
    
    public void add(Employee emp) throws ClassNotFoundException, SQLException{
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        String strSQL = "INSERT INTO employee (emp_fname, emp_lname, emp_phone, emp_address, emp_code)" +
                "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(strSQL);
        pstmt.setString(2, emp.getFName());
        pstmt.setString(3, emp.getLName());
        pstmt.setString(4, emp.getPhone());
        pstmt.setString(5, emp.getAddress()); 
        pstmt.setInt(6, emp.getEmpCode());
        
        pstmt.execute();
        
        con.close();
        
    }
    
}
