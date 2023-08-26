/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jake
 */
import java.sql.*;
import java.util.ArrayList;

public class dataIO
{

    private final String DATABASE_NAME = System.getProperty("DATABASE_NAME");
    private final String CONNECTION_STRING = System.getProperty("CONNECTION_STRING");
    private final String USER_NAME = System.getProperty("USER_NAME");
    private final String PASSWORD = System.getProperty("SQL_PASS");

    public void add(Employee emp) throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);

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

    public void loginEmp(String fn, char[] pw) throws SQLException, ClassNotFoundException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");

        //connect
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);
        String sql = "SELECT * FROM employee WHERE emp_fname=? AND emp_id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, fn);
        pstmt.setString(2, String.valueOf(pw));
        ResultSet rs = pstmt.executeQuery();

        CurrentEmployee emp = new CurrentEmployee();

        while (rs.next())
        {
            emp.setEmployeeID(rs.getInt(1));
            emp.setFName(rs.getString(2));
            emp.setLName(rs.getString(3));
            emp.setPhone(rs.getString(4));
            emp.setAddress(rs.getString(5));
            emp.setEmpCode(rs.getInt(6));
        }

        con.close();
    }

    public ArrayList<Employee> getList() throws SQLException
    {
        ArrayList<Employee> empArr = new ArrayList<Employee>();

        //connect
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);
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
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);

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
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);

        String str = "SELECT * FROM tool";

        Statement stmt = con.createStatement();
        ResultSet results = stmt.executeQuery(str);

        return results;
    }

    public void enterJob(String name, String desc, String[] list,
            String jobCode, int reqMatID)
            throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);

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

    public ArrayList<Job> getJobs(int empCode) throws SQLException
    {

        ArrayList<Job> jobArr = new ArrayList<Job>();

        //connect
        Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        Statement stmt = con.createStatement();
        String sql = null;
        ResultSet rs = null;
        System.out.println(empCode);

        if (empCode == 110)
        {
            sql = "SELECT * FROM job WHERE job_code=1000";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {

                Job job = new Job();

                job.setJob_id(rs.getInt(1));
                job.setJob_name(rs.getString(2));
                job.setJob_desc(rs.getString(3));
                job.setEmp_id(rs.getInt(4));
                job.setJob_code(rs.getInt(5));
                job.setReq_mat_id(rs.getInt(6));

                jobArr.add(job);
                System.out.println(job);
            }
        } else if (empCode == 120)
        {
            sql = "SELECT * FROM job WHERE job_code= 2000";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {

                Job job = new Job();

                job.setJob_id(rs.getInt(1));
                job.setJob_name(rs.getString(2));
                job.setJob_desc(rs.getString(3));
                job.setEmp_id(rs.getInt(4));
                job.setJob_code(rs.getInt(5));
                job.setReq_mat_id(rs.getInt(6));

                jobArr.add(job);
            }
        } else if (empCode == 130)
        {
            sql = "SELECT * FROM job WHERE job_code= '3000'";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {

                Job job = new Job();

                job.setJob_id(rs.getInt(1));
                job.setJob_name(rs.getString(2));
                job.setJob_desc(rs.getString(3));
                job.setEmp_id(rs.getInt(4));
                job.setJob_code(rs.getInt(5));
                job.setReq_mat_id(rs.getInt(6));

                jobArr.add(job);
            }
        } else if (empCode == 140)
        {
            sql = "SELECT * FROM job WHERE job_code=4000";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {

                Job job = new Job();

                job.setJob_id(rs.getInt(1));
                job.setJob_name(rs.getString(2));
                job.setJob_desc(rs.getString(3));
                job.setEmp_id(rs.getInt(4));
                job.setJob_code(rs.getInt(5));
                job.setReq_mat_id(rs.getInt(6));

                jobArr.add(job);
            }
        } else
        {
            System.out.println("BROKEN");
        }

        con.close();

        return jobArr;
    }

    public int getEmpCode(String empID)
    {
        String query = null;
        ResultSet rs = null;
        Statement st = null;
        int code = 0;
        try
        {
            Connection con = DriverManager.getConnection(CONNECTION_STRING,
                    USER_NAME, PASSWORD);
            query = "SELECT emp_code FROM employee WHERE emp_id=" + empID;
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next())
            {
                code = rs.getInt("emp_code");
            }

        } catch (SQLException ex)
        {
            java.util.logging.Logger.getLogger(dataIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return code;
    }

    public void addEmpToJob(String job_name, int empID)
    {

        try
        {
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
            PreparedStatement stmt = con.prepareStatement("UPDATE job SET emp_id=? WHERE job_name=?");
            stmt.setInt(1, empID);
            stmt.setString(2, job_name);

            stmt.executeUpdate();

            con.close();

        } catch (SQLException ex)
        {
            java.util.logging.Logger.getLogger(dataIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Rental> getAllRentals() throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);

        // create list model
        ArrayList<Rental> rentals = new ArrayList<Rental>();

        String strSQL = "SELECT * FROM rental";
        PreparedStatement pstmt = con.prepareStatement(strSQL);

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

    public ArrayList<Rental> getRentals(int id)
            throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);

        // create list model
        ArrayList<Rental> rentals = new ArrayList<Rental>();

        String strSQL = "SELECT * FROM rental WHERE emp_id=?";
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

    public ArrayList<Rental> getRentalHistory(int id)
            throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);

        String strSQL = "SELECT * FROM rental WHERE emp_id=?;";
        PreparedStatement pstmt = con.prepareStatement(strSQL);
        pstmt.setInt(1, id);

        ResultSet r = pstmt.executeQuery();

        ArrayList<Rental> rentals = new ArrayList();

        while (r.next())
        {
            Rental rent = new Rental();
            rent.setRentalID(r.getInt(1));
            rent.setEmpID(r.getInt(2));
            rent.setStatus(r.getString(3));

            rentals.add(rent);
        }

        con.close();

        return rentals;
    }

    public ArrayList getToolsList(int id)
            throws ClassNotFoundException, SQLException
    {
        //check for the driver
        Class.forName("software.aws.rds.jdbc.mysql.Driver");
        //connect to DB
        Connection con = DriverManager.getConnection(CONNECTION_STRING,
                USER_NAME, PASSWORD);

        // create list model
        ArrayList tools = new ArrayList();

        String strSQL = "SELECT tool.tool_name "
                + "FROM tool JOIN rental_intermediary "
                + "WHERE rental_id=? && "
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

    public void checkOutTools(int empID, Integer[] list, int rentalID)
            throws ClassNotFoundException, SQLException
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

    private void updateTools(Connection con, Integer[] list)
            throws SQLException
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

    private void addRental(Connection con, Integer[] list, int rentalID)
            throws SQLException
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
