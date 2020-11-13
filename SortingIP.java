import java.net.InetAddress;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
/*
 * SortingIP class is used to sort the IP addressed that we use in the combo box
 */

public class SortingIP {
    private Map sorted = new TreeMap();
    
    // The method converts the String IP address that passed into long value 
    private static long convertiptotypelong(String ip) {
        long j = 0;
        try {
            byte[] address = InetAddress.getByName(ip).getAddress();
            for (int i = 0; i < address.length; i++) {
                long x = address[i];
                if (x < 0) {
                    x += 256;
                }
                j += x << ((3 - i) * 8);
            }
            return j;
        } catch (Exception UnknownHostException) {
            return j;
        }
    }
    // The method is used to add the IP address passed to Map
    public void add(String ip) {
        try {
            sorted.put(new Long(convertiptotypelong(ip)), ip);
        } catch (Exception UnknownHostException) {
        }
    }
    
    /** Method that converts Collection<String> to String[] array
     * @return string array
     */
    public String[] processed() {
        Collection<String> values = sorted.values();
        String[] stringArray = values.toArray(new String[values.size()]);
        return stringArray;

    }
}