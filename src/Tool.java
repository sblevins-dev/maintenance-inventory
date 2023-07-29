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
    
    
}
