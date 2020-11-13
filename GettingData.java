
import java.io.File;
import java.util.*;

public class GettingData {
    public List<Double> timeList;
    public List<Double> byteList;
    public ArrayList<Double> allBytes;
    public List<String> destinationIPs;
    public List<String> sourceIPs;
    private String file;
    private Scanner data;
    private Set<String> destinationSet = new HashSet<>();
    private Set<String> sourceSet = new HashSet<>();
    private SortingIP sortedDestinationIPs;
    private SortingIP sortedSourceIPs;

    public GettingData(String file) {
        this.file = file;
        process(file);

    }

    private void process(String f) {
        try {
            data = new Scanner(new File(f));
            timeList = new ArrayList<>();
            byteList = new ArrayList<>();
            destinationIPs = new ArrayList<>();
            sourceIPs = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Invalid file"); 
            return;
        }
        while (data.hasNext()) {
            try {
                String line = data.nextLine();
                String[] splitted = line.split("\\t");
                String time = splitted[1];
                timeList.add(Double.parseDouble(time));

                String bytes = splitted[7];
                byteList.add(Double.parseDouble(bytes));

                String destinationip = splitted[4];
                destinationSet.add(destinationip);
                destinationIPs.add(destinationip);

                String sourceip = splitted[2];
                sourceSet.add(sourceip);
                sourceIPs.add(sourceip);


            } catch (Exception ArrayIndexOutOfBoundsException) { 
                byteList.add(0.0);
                sourceIPs.add(null);
                destinationIPs.add(null);
            }

        }

        List sortedSourceIPsSet = new ArrayList(sourceSet);
        sortedSourceIPs = new SortingIP();
        for (Object i : sortedSourceIPsSet) {
            sortedSourceIPs.add(i.toString());
        }
        List sortedDestinationIPsinationSet = new ArrayList(destinationSet);
        sortedDestinationIPs = new SortingIP();
        for (Object i : sortedDestinationIPsinationSet) {
            sortedDestinationIPs.add(i.toString());
        }


    }
    public ArrayList<Double> anotherProcess(String ip) { 
        double startTime = timeList.get(0);
        int totalsindex = 0;
        int index = 0;
        int allBytessize = (int) Math.ceil((timeList.get(timeList.size() - 1) - startTime) / 2);
        allBytes = new ArrayList<>(allBytessize + 1);
        for (int i = 0; i < allBytessize; i++) {
            allBytes.add(0.0);
        } 
        for (Double time : timeList) {
            if (time >= startTime + 2) {
                startTime += 2;
                totalsindex += 1;

            }
            double oldval = allBytes.get(totalsindex);
            if ((destinationIPs.get(index) != null && destinationIPs.get(index).equals(ip)) | (sourceIPs.get(index) != null && sourceIPs.get(index).equals(ip))) {
                if (time >= startTime && time < startTime + 2) {
                    allBytes.set(totalsindex, (oldval + byteList.get(index)));

                }
            }
            index += 1;

        }
        return allBytes;

    }

    public String[] ReturningDestinationIPs() {
        return sortedDestinationIPs.processed();
    }

    public String[] ReturningSourceIPs() {
        return sortedSourceIPs.processed();
    }
}