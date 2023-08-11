/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nechi
 */
public class Job
{
    private int job_id;
    private String job_name;
    private String job_desc;
    private int emp_id;
    private int job_code;
    private int req_mat_id;

    public Job()
    {
        this.job_name = "unknown";
        this.job_desc = "unknown";
        this.emp_id = 000;
        this.job_code = 000;
        this.req_mat_id = 000;
    }

    public Job(int job_id, String job_name, String job_desc, int emp_id, int job_code, int req_mat_id)
    {
        this.job_id = job_id;
        this.job_name = job_name;
        this.job_desc = job_desc;
        this.emp_id = emp_id;
        this.job_code = job_code;
        this.req_mat_id = req_mat_id;
    }
    
    
}
