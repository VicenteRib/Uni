#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct  1 15:00:57 2024

@author: mac
"""

import socket
import sys
import ethernetTools as et

#sys.argv[1]: ethernet address to send message
if len(sys.argv) < 2:
    print("[ERROR]Unexpected number of arguments")
    print("Usage: python3 simpleClient.py targetEthernetAddress (example: aa:bb:cc:dd:ee:ff)")
    exit(0)


if __name__ == "__main__":
    #creates RAW ethernet socket
    sck = socket.socket(socket.AF_PACKET, socket.SOCK_RAW, socket.htons(et.ETH_P_ALL))
    #Binding ethernet interface to the socket
    sck.bind(("eth0", 0))
    
    #eth0 - default name of the ethernet interface in UNIX
    my_addr = et.get_self_addr(sck, 'eth0')
    #prints ethernet address in human-readable format
    print('Ethernet address:', et.bytes_ether_addr_to_string(my_addr))
    #argv[1]: ethernet address of target device
    target_addr = et.ether_addr_to_bytes(sys.argv[1])
    print('Target ethernet address:', sys.argv[1].lower())
    msg = "Hello " + sys.argv[1].lower() + "! Did you receive my ethernet frame?"
    
    #builds frame to send
    frame = et.build_frame(target_addr, my_addr, msg)
    sck.send(frame)
    print("Ethernet frame with msg '", msg, "' sent to ethernet address", sys.argv[1].lower())
    
    sck.close()
