
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
}
