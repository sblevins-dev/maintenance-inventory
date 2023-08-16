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
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class dataIO
{

    private final String DATABASE_NAME = "gb-manufacturing";
    private final String CONNECTION_STRING = "jdbc:mysql://aws-gb-man.ctkxvwo1jmoa.us-east-1.rds.amazonaws.com:3306/" + DATABASE_NAME;
    private final String USER_NAME = "admin";
    private final String PASSWORD = "hamBurger1212";

    public void add(Employee emp) throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);

        String strSQL = "INSERT INTO employee (emp_fname, emp_lname, emp_phone, emp_address, emp_code)"
                + "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(strSQL);
        pstmt.setString(1, emp.getFName());
        pstmt.setString(2, emp.getLName());
        pstmt.setString(3, emp.getPhone());
        pstmt.setString(4, emp.getAddress());
        pstmt.setInt(5, emp.getEmpCode());

        pstmt.execute();

        con.close();

    }

    public ArrayList<Employee> getList() throws SQLException
    {
        ArrayList<Employee> empArr = new ArrayList<Employee>();

        //connect
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM employee";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next())
        {
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

    public void delete(int empID) throws SQLException
    {
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);

        String sql = "DELETE FROM employee WHERE emp_id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, empID);
        stmt.execute();

        con.close();
    }

    public ResultSet getTools() throws ClassNotFoundException, SQLException
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

    public ArrayList<Rental> getRentals(int id) throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);

        // create list model
        ArrayList<Rental> rentals = new ArrayList<Rental>();

        String strSQL = "SELECT * FROM rental WHERE emp_id=? AND status='open'";
        PreparedStatement pstmt = con.prepareStatement(strSQL);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
        {
            Rental rent = new Rental();

            rent.setRentalID(rs.getInt(1));
            rent.setEmpID(rs.getInt(2));
            rent.setStatus(rs.getString(3));

            rentals.add(rent);
        }
        con.close();

        return rentals;
    }
    
    public ArrayList getToolsList(int id) throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);

        // create list model
        ArrayList tools = new ArrayList();

        String strSQL = "SELECT tool.tool_name FROM tool JOIN rental_intermediary"
                + " WHERE rental_id=? && "
                + "rental_intermediary.tool_id = tool.tool_id;";
        PreparedStatement pstmt = con.prepareStatement(strSQL);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next())
        {
            String tool = rs.getString(1);
            tools.add(tool);
            
        }
        
        con.close();
        
        return tools;
    }
    
    public void updateRentalStatus(int id) 
            throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);
        
        String strSQL2 = "UPDATE tool SET quantity = quantity + 1 "
                + "WHERE tool_id IN (SELECT tool_id "
                + "FROM rental_intermediary "
                + "WHERE rental_id=? )";
        PreparedStatement pstmt2 = con.prepareStatement(strSQL2);
        pstmt2.setInt(1, id);
        
        pstmt2.execute();
        
        String strSQL = "UPDATE rental SET status=\"closed\" WHERE rental_id=?";
        PreparedStatement pstmt = con.prepareStatement(strSQL);
        pstmt.setInt(1, id);
        
        
        
        pstmt.execute();
        
        con.close();
    }
    
    public void checkOutTools(int empID, Integer[] list, int rentalID) throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);
        
        String status = "open";
        
        String strSQL = "INSERT INTO rental (rental_id, emp_id, status) "
                + "VALUES (?, ?, ?);";
        PreparedStatement pstmt = con.prepareStatement(strSQL);
        pstmt.setInt(1, rentalID);
        pstmt.setInt(2, empID);
        pstmt.setString(3, status);
        
        pstmt.execute();
        
        updateTools(con, list);
        addRental(con, list, rentalID);
        
        con.close();
    }

    private void updateTools(Connection con, Integer[] list) throws SQLException
    {
        
        String strSQL2;
        
        for (int i = 0; i < list.length; i++)
        {
            strSQL2 = "UPDATE tool SET tool.quantity=tool.quantity - 1 "
                + "WHERE tool_id=?;";
            PreparedStatement pstmt2 = con.prepareStatement(strSQL2);
            pstmt2.setInt(1, list[i]);
            pstmt2.execute();
        }
    }

    private void addRental(Connection con, Integer[] list, int rentalID) throws SQLException
    {
        String strSQL3 = "INSERT INTO rental_intermediary (rental_id, tool_id) "
                + "VALUES (?, ?)";
        
        for (int i = 0; i < list.length; i++)
        {
            
            
            PreparedStatement pstmt3 = con.prepareStatement(strSQL3);
            pstmt3.setInt(1, rentalID);
            pstmt3.setInt(2, list[i]);
            pstmt3.execute();
        }
    }

}
