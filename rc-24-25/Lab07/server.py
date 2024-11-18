# pingUDPserver.py
import random
import sys
from socket import *

bufferSize = 1024

def main(port):
    # get my ip address
    myIP = gethostbyname(gethostname())
    # Create a UDP socket
    print(myIP)
    serverSocket = socket(AF_INET, SOCK_DGRAM)
    # Assign IP address and port number to socket
    serverSocket.bind((myIP, port))
    print("ping server starting ...")
    while True:
        # Generate random number in the range of 1 to 10
        rand = random.randint(1, 10)
        # Receive the client packet along with the address it is coming from
        message, address = serverSocket.recvfrom(bufferSize)
        # Capitalize the message from the client
        message = message.upper()
        # If rand is less than 4, consider the packet lost and do not respond
        if rand >= 4:
            serverSocket.sendto(message, address)

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python3 pingUDPserver.py serverPort")
        sys.exit(1)
    pingP = sys.argv[1]
    print("gekk")
    main(int(pingP))
