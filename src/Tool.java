/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nechi
 */
public class Tool
{
    private int tool_id;
    private String tool_name;
    private String tool_location;
    private int quantity;

    public Tool()
    {
        this.tool_id = 000;
        this.tool_name = "unknown";
        this.tool_location = "unknown";
        this.quantity = 0;
    }

    public Tool(int tool_id, String tool_name, String tool_location, int quantity)
    {
        this.tool_id = tool_id;
        this.tool_name = tool_name;
        this.tool_location = tool_location;
        this.quantity = quantity;
    }
     @Override
    public String toString()
    {
        return this.tool_name;
    }
    
    public int getToolID()
    {
        return tool_id;
    }
    
    public void setToolID(int tool_id)
    {
        this.tool_id = tool_id;
    }
    
    public String getToolName()
    {
        return tool_name;
    }
    
    public void setToolName(String tool_name)
    {
        this.tool_name = tool_name;
    }
    
    public String getToolLocation()
    {
        return tool_location;
    }
    
    public void setToolLocation(String tool_location)
    {
        this.tool_location = tool_location;
    }
    
    public int getToolQuantity()
    {
        return quantity;
    }
    
    public void setToolQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
}
