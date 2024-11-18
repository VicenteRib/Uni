import os
import pickle
import random
import socket

HOST = "127.0.0.1"  # server addr
PORT = 12345        # server port
BSIZE = 1024        # socket buffer size

# Function to simulate datagram loss
def serverReply(msg, sock, address):
    rand = random.randint(0, 9)
    if rand > 2:  # Simulates 20% packet loss
        sock.sendto(msg, address)

# Server code
def run_server():
    # Create UDP socket and bind to port
    ss = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    ss.bind((HOST, PORT))

    print(f"Server listening on port {PORT}...")

    while True:
        # Receive request
        data, addr = ss.recvfrom(1024)
        request = pickle.loads(data)

        file_name, offset, size = request

        try:
            # Try to open the requested file
            with open(file_name, 'rb') as f:
                file_size = os.path.getsize(file_name)

                # Check if the offset is valid
                if offset >= file_size:
                    response = (2, 0, b'')  # Invalid offset
                else:
                    # Seek to the required offset and read bytes
                    f.seek(offset)
                    file_chunk = f.read(size)
                    response = (0, len(file_chunk), file_chunk)

        except FileNotFoundError:
            # File not found
            response = (1, 0, b'')

        # Serialize response and send it back to the client
        response_data = pickle.dumps(response)
        serverReply(response_data, ss, addr)

if __name__ == "__main__":
    run_server()
