#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct  1 15:00:57 2024

@author: mac
"""

import socket
import ethernetTools as et
SIZE = 1024

if __name__ == "__main__":
    #creates RAW ethernet socket
    sck = socket.socket(socket.AF_PACKET, socket.SOCK_RAW, socket.htons(et.ETH_P_ALL))
    #Binding ethernet interface to the socket
    sck.bind(("eth0", 0))
    
    #eth0 - default name of the ethernet interface in UNIX
    my_addr = et.get_self_addr(sck, 'eth0')
    #prints ethernet address in human-readable format
    print('Ethernet address:', et.bytes_ether_addr_to_string(my_addr))
   
    while True:         #Keeps waiting for ethernet frames and prints them.
        print("Waiting for an ethernet frame...")
        #receives an ethenet frame
        recv_frame = sck.recv(et.MAX_FRAME_SIZE)
    
        print("Ethernet frame received!")
        #extracts content of the frame
        my_addr_in_frame, sender_addr, _, fName = et.extract_frame(recv_frame)
        print("My addr in frame:", et.bytes_ether_addr_to_string(my_addr_in_frame))
        print("Sender addr:", et.bytes_ether_addr_to_string(sender_addr))
        print("File to transfer:", fName)
        try:    
            f = open(fName, "rb")
            data = f.read(SIZE)
            print("Copying ")
            print(".",end='')
            while (data):
                frame = et.build_frame(sender_addr, my_addr, data)
                sck.send(frame)
                print(".",end='')
                data = f.read(SIZE)
            f.close()
            print("\nFile",fName,"has been transfered")
        except:
            data = "File " + fName + " not found"
            frame = et.build_frame(sender_addr, my_addr, data)
            sck.send(frame)

    sck.close()
