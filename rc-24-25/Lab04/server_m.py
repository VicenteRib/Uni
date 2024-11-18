#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  2 10:37:36 2024

@author: cpm
"""
from socket import *
import sys
from threading import Thread
import traceback

HOST = "127.0.0.1"  # server address
PORT = 12345        # server port
BSIZE = 1024        # socket buffer size

def start_server():
   soc = socket(AF_INET, SOCK_STREAM)
   # SO_REUSEADDR socket option is set in order to immediately reuse previous 
   # sockets which were bound on the same address and remained in TIME_WAIT state
   soc.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
   
   print("Socket created")
   try:
      soc.bind((HOST, PORT))
   except:
      print("Bind failed. Error : " + str(sys.exc_info()))
      sys.exit(1)
      
   soc.listen(5)
   print("Socket now listening")
   
   # infinite loop- do not reset for every requests
   while True:
      try:
          connection, address = soc.accept()
          ip, port = str(address[0]), str(address[1])
          print("Connected with: ", ip, port)
      except KeyboardInterrupt:
          print("Closing socket")
          soc.close()
          sys.exit(1)
   
      try:
          tid = Thread(target=client_thread, args=(connection, ip, port))
          tid.start()
      except:
          print("Thread did not start.")
          traceback.print_exc()
          
   soc.close()
   
   
def client_thread(connection, ip, port):
    while True:
      client_input = connection.recv(BSIZE)
        
      decoded_input = client_input.decode()
      print("Processing the input received from client")
      result = "Hello " + decoded_input.upper()
      
      if "QUIT" in result:
         print("Client is requesting to quit")
         connection.close()
         print("Connection " + ip + ":" + port + " closed")
         break    
      else:
         print("Result:", result)
         connection.send(result.encode())

def main():
   start_server()
   
if __name__ == "__main__":
   main()