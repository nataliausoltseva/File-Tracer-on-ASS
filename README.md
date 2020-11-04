# File-Tracer-on-ASS

An application that displays the data in tabular form. The toal size of a trace file vary substantially dependning on the number of hosts involved in the experiment and the length of the experiment. There are large and small text files available in this repo. The small one contains 148315 lines and large one contains 651274 lines.

## Files:
- First field:
    - A seuqential number for each packet that is added by the eavesdropping program.

- Second field:
    - A time stamp that is also added by the aevesdropping program. Each trace file starts with a time stamp of 0.000000000 for the first packet in the file.

- Third field:
    - The IP address of the source host. IP addresses identify machines on the network and help routers forward packets between source and destination. Each IP address consists of 4 decimal numbers between 0 and 255 seperated by dots. The trace files here only show packets heading towards destination hosts on the island side, so all source host IP addresses start with `192.168.0.`, indicating that they are 'world side' addresses.

- Fourth field:
    - TCP port on the hosts that the respective packets travel between. They identify the application that ahve sent or would have received the packets.

- Fifth field:
    - The IP addresses of the distination host from the island network. All of the island addresses start with `10.0.`.

- Sixth filed:
    - Same as `Fourth field` above.

- Seventh, eighth and ninth:
    - Packet sizes in bytes. THe size we are interested is that in 8th field, it's the IP packet size. The size in 7th field is that of the whole Enternet frame contains the IP packet, and 9th field is the TCP payload size.