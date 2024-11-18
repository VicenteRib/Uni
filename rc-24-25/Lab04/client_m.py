#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  2 10:57:45 2024

@author: cpm
"""
from socket import *
import sys

HOST = "127.0.0.1"  # server addr
PORT = 12345        # server port
BSIZE = 1024        # socket buffer size

def main():
   soc = socket(AF_INET, SOCK_STREAM)
   
   try:
      soc.connect((HOST, PORT))
   except:
      print("Connection Error")
      sys.exit()
      
   print("Please enter 'quit' to exit")
   message = input("Enter message: ")
   
   while message != 'quit': 
      soc.send(message.encode())
      msg = soc.recv(BSIZE)
      print("received: ",msg.decode())
      
      message = input("Enter message: ")
      
   soc.send("quit".encode())
   soc.close()
   
if __name__ == "__main__":
   main()