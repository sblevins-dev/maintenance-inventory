/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nechi
 */
public class CurrentEmployee
{
    private static int employeeID;
    private static String FName;
    private static String LName; 
    private static String phone;
    private static String address; 
    private static int empCode;
    
    public CurrentEmployee(){
        employeeID = 0;
        FName = "N/A";
        LName = "N/A";
        phone = "N/A";
        address = "N/A";
        empCode = 0;
    }
    
    @Override
    public String toString()
    {
        return FName + " " + LName;
    }
    
    //parameterized constructor
    public CurrentEmployee(int employeeID, String FName, String LName, String phone, String address, int empCode){
        this.employeeID = employeeID;
        this.FName = FName;
        this.LName = LName;
        this.phone = phone;
        this.address = address;
        this.empCode = empCode; 
    }
    //all getters and setters for the employee class
    public static int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFName() {
        return FName;
    }
    
    public static String getFullName() {
        return FName + " " + LName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmpCode() {
        return empCode;
    }

    public void setEmpCode(int empCode) {
        this.empCode = empCode;
    }
    
    public String showEmpDetails(){
        String output = "Employee: " + FName + " " +  LName + "\n";
                  output += "Employee ID: " +  Integer.toString(employeeID) + "\n";
                  output += "Employee Phone: " + phone + "\n";
                  output += "Employee Address: " + address + "\n";
                  output += "Employee Code: " + Integer.toString(empCode) + "\n";
                  return output; 
    }
}
