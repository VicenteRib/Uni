import pickle
import select
import socket
import sys

HOST = "127.0.0.1"  # server addr
PORT = 12345        # server port
BSIZE = 1024        # socket buffer size Function to wait for a reply with a timeout

def waitForReply(sock):
    rx, _, _ = select.select([sock], [], [], 1)  # 1-second timeout
    return bool(rx)

# Client code
def run_client(server_host, server_port, file_name, chunk_size):
    # Create UDP socket
    sc = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    port = int(input("Enter client port: "))
    sc.bind(('', port))
    
    # Open the local file for writing the received data
    with open(f"received_{file_name}", 'wb') as local_file:
        offset = 0
        while True:
            # Prepare the request and serialize it
            request = (file_name, offset, chunk_size)
            request_data = pickle.dumps(request)

            # Send the request to the server
            sc.sendto(request_data, (server_host, server_port))

            # Wait for a reply
            if waitForReply(sc):
                # Receive the reply from the server
                reply_data, _ = sc.recvfrom(1024)
                status, bytes_received, file_chunk = pickle.loads(reply_data)

                if status == 1:
                    print(f"File '{file_name}' not found on server.")
                    break
                elif status == 2:
                    print(f"Invalid offset {offset}.")
                    break

                # Write the received chunk to the local file
                local_file.write(file_chunk)

                # Break if end of file
                if bytes_received < chunk_size:
                    break

                # Update offset for the next chunk
                offset += bytes_received

            else:
                print(f"Timeout! Retrying chunk from offset {offset}...")

if __name__ == "__main__":
    server_host = sys.argv[1]
    server_port = int(sys.argv[2])
    file_name = sys.argv[3]
    chunk_size = int(sys.argv[4])

    run_client(server_host, server_port, file_name, chunk_size)
