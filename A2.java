import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.math.*;
/*
 * Name: Natalia Usoltseva
 * IP: nuso101
 * ID: 416666305
 * The main class where JFrame created with radio buttons, combo box for IPs and JTable.
 */
public class A2 extends JFrame {
	
	private JTable jTable;
    private JPanel buttonPanel;
    private ButtonGroup radioButtons;
    private JRadioButton sourceButton;
    private JRadioButton destinationButton;
    private Font font;
    private JComboBox<String> IPComboBox;
    private Simulator data;
    private ArrayList<Double> allBytes;
    private boolean checkingHost;
    private DefaultTableModel  tableModel;
    private Packet[] tableData;
    private PacketTableModel model;
    private Object[][] objectData;
    private JPanel tablePanel;
    private JScrollPane scrollPane;
    private Packet p;
    private ArrayList<Packet> array;
  
    // Constructor for the main class where we call methods to setup Menu for getting a file, to make radio buttons and combo box
    public A2() {
        super("Flow volume viewer");
        setLayout(new FlowLayout());
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        font = new Font("Sans-serif", Font.PLAIN, 20);
        
        
        setupMenu();
        setupRadioButtons();
        setupComboBox();
        
        
        jTable = new JTable() {
        	public Component prepareRenderer (TableCellRenderer renderer, int row, int column) {
        		Component c = super.prepareRenderer(renderer, row, column);
        		if(row == getRowCount()-1) {
        			c.setBackground(Color.CYAN);
    				c.setForeground(Color.BLACK);

        		}
        		else if(row == getRowCount()-2) {
        			c.setBackground(Color.ORANGE);
    				c.setForeground(Color.BLACK);
        		}
        		else {
        			c.setBackground(Color.WHITE);
        			c.setForeground(Color.BLACK);
        		}
        		return c;
        	}
        };

        jTable.setRowHeight(20);
        scrollPane = new JScrollPane(jTable);
        Dimension panelSize = new Dimension(500,450);
		scrollPane.setPreferredSize(panelSize);
        setVisible(true);

    }
    
    // Method that setups menu to be able to open a trace file and quit.
    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menuFile = new JMenu("File");
        menuFile.setFont(font);
        menuBar.add(menuFile);
        JMenuItem openTrace = new JMenuItem("Open trace file");
        JMenuItem exittingFile = new JMenuItem("Quit");
        menuFile.add(openTrace);
        openTrace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");
                int retval = fileChooser.showOpenDialog(A2.this);
                if (retval == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooser.getSelectedFile().getPath();
                    data = new Simulator(file);
                    p = new Packet(file);
                    //jTable.getModel().addTableModelListener(new TableModelListener() {
                    //	public void tableChanged(TableModelEvent e) {
                    //		int row = e.getFirstRow();
                    //		int column = e.getColumn();
                    //		model = (PacketTableModel) e.getSource();
                    //		Object value = model.getValueAt(row, column);
                    //		int intValue = Integer.parseInt(value.toString());
                    //		p.setIpPacketSize(intValue);
                    //		try (FileWriter writer = new FileWriter(file)){
                    //			array = data.getValidPackets();
                    //			for(int i=0; i<array.size(); i++) {
                    //				writer.write(array.get(i));
                    //			}
                    //		}
                    //		return;
                    //		
                    //	}
                   // });
                    if (destinationButton.isSelected() == true) {
                    	checkingHost = true;
                        newComboBox(data.ReturningDestinationIPs());
                        add(scrollPane);
                        
                    } else {
                    	checkingHost = false;
                        newComboBox(data.ReturningSourceIPs());
                        add(scrollPane);
                    }

                }
            }
        }
        );
        menuFile.add(exittingFile);
        exittingFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );
    }
    
    // Method that setups radio buttons to look good and working correctly
    private void setupRadioButtons() {
        buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        radioButtons = new ButtonGroup();
        HandlingRadioButtons handler = new HandlingRadioButtons();
        sourceButton = new JRadioButton("Source hosts");
        sourceButton.setFont(font);
        sourceButton.setSelected(true);
        sourceButton.addActionListener(handler);
        
        radioButtons.add(sourceButton);
        buttonPanel.add(sourceButton, constraints);
        destinationButton = new JRadioButton("Destination hosts");
        destinationButton.setFont(font);
        destinationButton.addActionListener(handler);
        radioButtons.add(destinationButton);
        buttonPanel.add(destinationButton, constraints);
        add(buttonPanel);
    } 
    
    // Method that setups combo box that takes a list of IPs (if destination radio button is selected then we have a list of destination IPs
    // if source radio button is selected then we have a list of source IPS)
    private void setupComboBox() {
        IPComboBox = new JComboBox<>();
        IPComboBox.setMaximumRowCount(8);
        IPComboBox.setFont(font);
        IPComboBox.setMinimumSize(new Dimension(300, 25));
        HandlingComboBox combo = new HandlingComboBox();
        IPComboBox.addActionListener(combo);
        IPComboBox.setVisible(false);
        add(IPComboBox);
    }
    
    
    // Method that puts all the IPs in the combo box in a sorted way
    private void newComboBox(String[] Sorted) {
        IPComboBox.removeAllItems();
        for (String i : Sorted) {
            IPComboBox.addItem(i);
            IPComboBox.setVisible(true);
        }

    }

    // Method that updates the jTable using PacketTableModel class
    private void setupNewTable() {
		model = new PacketTableModel(tableData, checkingHost);
		jTable.setModel(model);
    }
    
    // Method that handles radio buttons using actionlistener to set the value of combo box correctly depending on the selected radio buttons
    public class HandlingRadioButtons implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (destinationButton.isSelected() == true) {
            	checkingHost = true;
                newComboBox(data.ReturningDestinationIPs());
            } else {
            	checkingHost = false;
                newComboBox(data.ReturningSourceIPs());
            }
        }
    }
    
    // Method where we get the table data by knowing which IP was selected in combo box
    public class HandlingComboBox implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
            	tableData = data.getTableData(IPComboBox.getSelectedItem().toString(), checkingHost);
            	
            	setupNewTable();
            } catch (Exception NullPointerException) {
            	return;
            }

        }
    }
    
}
