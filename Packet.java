/*
 * Packet class that find the correct value of each line in the text given.
 */

public class Packet {

        private String source;
        private String dest;
        private double timestamp;
        private int size;
        
        // Constructor for the Packet class that takes the string of text and sets the correct data to the given instance values from the text
        public Packet(String text) {
              source = "";
              dest = "";
              timestamp = 0;
              size = 0;
              String fields[] = text.split("\t", -1);
              if (fields.length >= 8) {
                    source = fields[2].trim();
                    dest = fields[4].trim();
                    timestamp = parseAsNumber(fields[1]);
                    size = (int) parseAsNumber(fields[7]);
              }
        }
        /** Method that converts a string into double value
        * @return number if it's possible to convert otherwise
        * @return 0
        */
        private double parseAsNumber(String text) {
              try {
                    double number = Double.parseDouble(text);
                    return number;

              } catch (Exception e) {              }
              return 0;
        }
        
        /**
        * Gets the Source Host
        * @return this source host
        */
        public String getSourceHost() {
              return source;
        }
        
        /**
        * Gets the Destination Host
        * @return this destination host
        */
        public String getDestinationHost() {
              return dest;
        }
        
        /**
        * Gets the Time Stamp
        * @return this time stamp
        */
        public double getTimeStamp() {
              return timestamp;
        }
        
        
        public int getIpPacketSize() {
              return size;
        }
        
        /**
        * Changes the value of Source Host
        * @param source
        */	
        public void setSourceHost(String source) {
              this.source = source;
        }
        
        /**
         * Changes the value of Destination Host
         * @param dest
         */
        public void setDestinationHost(String dest) {
              this.dest = dest;
        }
        
        /**
         * Changes the value of Time Stamp
         * @param timestamp
         */
        public void setTimeStamp(double timestamp) {
              this.timestamp = timestamp;
        }
        /**
         * Changes the value of IP Packet Size
         * @param size
         */
        public void setIpPacketSize(int size) {
              this.size = size;
        }
        
        /**
         * @return String that contains all of the data i.e. source host, destination host, time stamp, ip packet size
         */
        public String toString() {

              return String.format("src=%s, dest=%s, time=%.2f, size=%d", source, dest, timestamp, size);

        }

    }