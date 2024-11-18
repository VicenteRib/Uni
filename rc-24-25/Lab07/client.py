import socket
import sys
import time
import select

def waitForPing(s):
    rx, tx, er = select.select([s], [], [], 1)
    return rx != []

def main(serverIP, port):
    counterLoss = 0
    socketClient = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    for i in range(10):
        currentTime = time.time()
        socketClient.sendto(b"ping", (serverIP, port))
        if waitForPing(socketClient):
            msg, adress = socketClient.recvfrom(256)
            recTime = time.time()
            rtt = recTime - currentTime
            print(f"{len(msg)} bytes received from {serverIP} at the {i} ping with a RTT of {rtt}")
        else:
            counterLoss += 1
    print(f"Data loss percentage of {counterLoss*10}%")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 pingUDPserver.py serverPort")
        sys.exit(1)
    serverIp = sys.argv[1]
    pingP = sys.argv[2]
    main(serverIp,int(pingP))
