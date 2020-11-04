/*
 * Name: Natalia Usoltseva
 * IP: nuso101
 * ID: 416666305
 * 
 * PacketTableModel creates the object[][] that we use for creating a table model for JTable in the main class A2. 
 */

import java.awt.Color;
import java.awt.Component;
import java.util.*;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class PacketTableModel extends AbstractTableModel{
    private int rowCount;
    private Object[][] table;
    private double total;
    private double average;
    private String[] columnNames;
    
  //Constructor for PacketTableModel class that extends AbstractTableModel that allows us to create the table model later
  // Takes as input a Packet array and boolean value that indicates that if it's false then we create object[][] where we add destination host
  // if it's true then we add source host to the object[][] 
    public PacketTableModel(Packet[] packets, boolean bool){
        total = 0.0;
        this.rowCount = packets.length+2;
        table = new Object[packets.length+2][3];
        
        
        if(bool == false){
            for(int i=0; i < packets.length; i++){
                table[i][0] = packets[i].getTimeStamp();
                table[i][1] = packets[i].getDestinationHost();
                table[i][2] = packets[i].getIpPacketSize();
                total += packets[i].getIpPacketSize();
            }
        }
        if(bool == true){
            for(int i=0; i < packets.length; i++){
                table[i][0] = packets[i].getTimeStamp();
                table[i][1] = packets[i].getSourceHost();
                table[i][2] = packets[i].getIpPacketSize();
                total += packets[i].getIpPacketSize();
            }
        }
        
        average = total/packets.length;
        if(bool == true) {
        	columnNames = new String[] {"timeStamp", "Source IPs", "Packet Size"};
        }
        else {
        	columnNames = new String[] {"timeStamp", "Destination IPs", "Packet Size"};
        }
        long factor = (long) Math.pow(10, 1);
        average = average * factor;
        long tmp = Math.round(average);
        double finalValue = (double) tmp / factor;
        table[rowCount-2][2] = (int) total;
        table[rowCount-1][2] = finalValue;
        
    }
    
    /**
     * Gets the column count that is always 3 
     * @return this colCount
     */
    public int getColumnCount(){
        int colCount = 3;
        return colCount;
    }
    
    /**
     * Gets the row count
     * @return this rowCount
     */
    public int getRowCount(){
        return this.rowCount;
    }
    
    /**
     * Gets the value at the value from the object[][] by using row and column values 
     * @return this table[row][column]
     */
    
    public Object getValueAt(int row, int col){
        if(row == table.length){
            //setValueAt(total, row, col);
        }
        else if(row == (table.length + 1)) {
        	//setValueAt(average, row, col);
        }
        return table[row][col];
        
    }
    
    /**
     * Changes the value in the object[][] at the passed row and column
     * @param table[row][column] = value
     */
    public void setValueAt(Object object, int row, int col) {
    	try {
    		int value = Integer.parseInt(object.toString());
    		this.table[row][col] = value;
        	fireTableCellUpdated(row, col);
    	}
    	catch( NumberFormatException e) {
    		return;
    		
    	}
    	
    }
    /**
     * Gets the name of the column i.e. the header for the jTable we use 
     * @return this table[row][column]
     */
    public String getColumnName(int columnIndex) {
    	return columnNames[columnIndex];
    }
    /**
     * Checks if the cell can be editable and the last column i.e. the packet size column is always editable 
     * @return  true if the last column is passed to the method otherwise 
     * @return false as we do not want to be able to add other columns
     */
    public boolean isCellEditable(int row, int col) {
	    if(col == 2){
	    	return true;
	    }
	    else {
	    	return false; 
	    }
    }

    
}
