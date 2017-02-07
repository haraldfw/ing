import socket

UDP_HOST = '0.0.0.0'
UDP_PORT = 5005


def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.bind((UDP_HOST, UDP_PORT))

    msgs = 0
    while 1:
        sock.recvfrom(16)
        msgs += 1
        print('Msgs rec: ' + str(msgs))


if __name__ == "__main__":
    main()
