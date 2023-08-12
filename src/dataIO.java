/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jake
 */

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import javax.swing.DefaultListModel;


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
        pstmt.setString(1, emp.getFName());
        pstmt.setString(2, emp.getLName());
        pstmt.setString(3, emp.getPhone());
        pstmt.setString(4, emp.getAddress()); 
        pstmt.setInt(5, emp.getEmpCode());
        
        pstmt.execute();
        
        con.close();
        
    }
    
    public ResultSet getTools () throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        // create list model
        DefaultListModel<String> tools = new DefaultListModel();
        
        String str = "SELECT * FROM tool";
        
        Statement stmt = con.createStatement();
        ResultSet results = stmt.executeQuery(str);
        
        
        return results;
    }
    
    public void enterJob(String name, String desc, String[] list, String jobCode, int reqMatID) throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        createReqMat(list, reqMatID, con); 
        createJob(name, desc, jobCode, reqMatID, con);
        
        con.close();
    }

    private void createReqMat(String[] list, int reqMatID, Connection con) 
            throws SQLException
    {
        int rowCount = list.length;
        for (int row = 0; row < rowCount; row++)
        {
            String strSQL = "INSERT INTO req_mat (req_mat_id, tool_id)"
                    + "VALUES(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(strSQL);
            pstmt.setInt(1, reqMatID);
            pstmt.setInt(2, Integer.parseInt(list[row]));

            pstmt.execute();
        }
    }

    private void createJob(String name, String desc, String jobCode, 
            int reqMatID, Connection con) throws SQLException
    {
        String strSQL = "INSERT INTO job (job_name, job_desc, job_code, req_mat_id)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(strSQL);
            pstmt.setString(1, name);
            pstmt.setString(2, desc);
            pstmt.setInt(3, Integer.parseInt(jobCode));
            pstmt.setInt(4, reqMatID);

            pstmt.execute();
    }
    
}
