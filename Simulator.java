import java.io.*;
import java.util.*;

/*
 * Name: Natalia Usoltseva
 * IP: nuso101
 * ID: 416666305
 * 
 * The Simulator class that creates an ArrayList<Packet> that is made from the text file that is given
 */
public class Simulator {
	
	private SortingIP sortedDestinationIPs;
    private SortingIP sortedSourceIPs;
	private ArrayList<Packet> content = new ArrayList<Packet>();
	
	// Constructor method for the Simulator class that takes the text file as string i.e. filename. 
	// It reads the given file and checks if the IP address of the source host matches the correct one i.e. the list contains IPs of IP version 4 (IPv4)
    public Simulator(String file ){
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                Packet p = new Packet(line);
               
                if (p.getSourceHost().matches("^192.168.0.\\d{1,3}$")) {
                    content.add(p);
                }               
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("java.io.FileNotFoundException: " + file + " (No such file or directory)");
        }

    }
    
    /**
     * Gets the valid Packets i.e. gets the ArrayList of the packets that were added into the list
     * @return this content where content is an ArrayList<Packet>
     */
    public ArrayList<Packet> getValidPackets(){
        return this.content;
    }
    
    /**
     * Gets the Host[] of the sorted source hosts by using ArrayList<Host> and HashSet<String> as the hostArray can be sorted and then converted
     * into the correct array of Host[]
     * @return host array
     */
    public Object[] getUniqueSortedSourceHosts(){
        HashSet<String> set = new HashSet<String>();
        ArrayList<Host> hostArrayList = new ArrayList<Host>();
        for (Packet pString : this.content){
            set.add(pString.getSourceHost());
        }
        
        for (String s : set) {
            Host host = new Host(s);
            hostArrayList.add(host);
        }
        
        Collections.sort(hostArrayList);
        Host[] hostArray = new Host[hostArrayList.size()];
        hostArray = hostArrayList.toArray(hostArray);
        return hostArray;
    }
    
    /**
     * Gets the Host[] of the sorted destination hosts by using ArrayList<Host> and HashSet<String> as the hostArray can be sorted and then converted
     * into the correct array of Host[]
     * @return host array
     */
    public Object[] getUniqueSortedDestHosts(){
        HashSet<String> set = new HashSet<String>();
        ArrayList<Host> hostArrayList = new ArrayList<Host>();
        for (Packet pString : this.content){
            set.add(pString.getDestinationHost());
        }
        for (String s : set) {
            Host host = new Host(s);
            hostArrayList.add(host);
        }
        Collections.sort(hostArrayList);
        Host[] hostArray = new Host[hostArrayList.size()];
        hostArray = hostArrayList.toArray(hostArray);
        return hostArray;
    }
    
    
    /**
     * Gets the Packet[] that is created by using the passed IP address and the boolean value that indicates that if it's false then return source hosts
     * if it's true then return destination hosts. That's get stored into the Packet[] array.
     * @return result source if boolean is false
     * @return result destination of boolean is true
     */
    public Packet[] getTableData(String ipAddress, boolean isSrcHost){
        ArrayList<Packet> sourceArrayList = new ArrayList<Packet>();
        ArrayList<Packet> destinationArrayList = new ArrayList<Packet>();
        Packet[] resultDestination = new Packet[destinationArrayList.size()];
        if(isSrcHost == false){
            for (Packet pString : this.content){
                String source = pString.getSourceHost();
                if(source.equals( ipAddress)){
                    sourceArrayList.add(pString);
                }
            }
            Packet[] resultSource = new Packet[sourceArrayList.size()];
            resultSource = sourceArrayList.toArray(resultSource);
            return resultSource;
        }
        else {
            for (Packet pString : this.content){
                String destination = pString.getDestinationHost();
                if(destination.equals( ipAddress)){
                    destinationArrayList.add(pString);
                }
            }
            
            resultDestination = destinationArrayList.toArray(resultDestination);
        }
        return resultDestination;
    }
    
    /**
     * Gets the Packet[] that is created by using the passed IP address and the boolean value that indicates that if it's false then return source hosts
     * if it's true then return destination hosts. That's get stored into the Packet[] array.
     * @return result source if boolean is false
     * @return result destination of boolean is true
     */
	public String[] ReturningDestinationIPs() {
		sortedDestinationIPs = new SortingIP();
		Object[] array = getUniqueSortedDestHosts();
		for(int i = 0; i < array.length; i++) {
			sortedDestinationIPs.add(array[i].toString());
		}
		return sortedDestinationIPs.processed();
	}

	public String[] ReturningSourceIPs() {
		sortedSourceIPs = new SortingIP();
		Object[] array = getUniqueSortedSourceHosts();
		for(int i = 0; i < array.length; i++) {
			sortedSourceIPs.add(array[i].toString());
		}
		return sortedSourceIPs.processed();
	}
}
