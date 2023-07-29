/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nechi
 */
public class Employee
{
    private int emp_id;
    private String emp_fname;
    private String emp_lname;
    private String emp_address;
    private int emp_phone;
    private int emp_job_code;

    public Employee(int emp_id, String emp_fname, String emp_lname, 
            String emp_address, int emp_phone, int emp_job_code)
    {
        this.emp_id = emp_id;
        this.emp_fname = emp_fname;
        this.emp_lname = emp_lname;
        this.emp_address = emp_address;
        this.emp_phone = emp_phone;
        this.emp_job_code = emp_job_code;
    }
    
}
