import java.util.Scanner;

// Host class implement comparable on its own Object i.e. class. This class allows us to sort the ip address.
public class Host implements Comparable<Host> {
    private String ip;
    
    //Constructor for Host class that takes a string of ip address
    public Host(String ip) {
        this.ip = ip;
    }
    
    //Method that represent the value as a String object
    public String toString() {
        return this.ip;
    }
    
    // Method that compares two IP addresses and returns an integer value
    public int compareTo(Host other){
        Scanner ipScanner = new Scanner(this.ip).useDelimiter("\\.");
        Long ipLong = (ipScanner.nextLong() << 24) + (ipScanner.nextLong() << 16) + (ipScanner.nextLong() << 8) + (ipScanner.nextLong());
        ipScanner.close();
        
        Scanner otherScanner = new Scanner(other.ip).useDelimiter("\\.");
        Long otherLong = (otherScanner.nextLong() << 24) + (otherScanner.nextLong() << 16) + (otherScanner.nextLong() << 8) + (otherScanner.nextLong());
        otherScanner.close();
        
        return ipLong.compareTo(otherLong);
    }
}