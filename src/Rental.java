
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nechi
 */
public class Rental
{
    private int rental_id;
    private int emp_id;
    private String status;
    private Date start_date;
    private Date end_date;

    public Rental()
    {
        this.rental_id = 000;
        this.emp_id = 000;
        this.status = "unknown";
    }

    public Rental(int rental_id, int emp_id, String status, Date start_date, Date end_date)
    {
        this.rental_id = rental_id;
        this.emp_id = emp_id;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    
    @Override
    public String toString()
    {
        String id = String.valueOf(this.rental_id);
        return id;
    }
    
    public int getRentalID()
    {
        return this.rental_id;
    }
    
    public void setRentalID(int id)
    {
        this.rental_id = id;
    }
    
    public int getEmpID()
    {
        return this.emp_id;
    }
    
    public void setEmpID(int emp_id)
    {
        this.emp_id = emp_id;
    }
    
    public String getStatus()
    {
        return this.status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String showRentalDetails(int id) throws ClassNotFoundException, SQLException
    {
        
        System.out.println();
        
        return "hello";
    }
}
