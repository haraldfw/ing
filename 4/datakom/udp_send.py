import socket

REC = '158.38.48.43'
PORT = 5005


def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    msgs = 0
    while 1:
        sock.sendto(b'Hi', (REC, PORT))
        msgs += 1
        print('Msgs sent: ' + str(msgs))


if __name__ == "__main__":
    main()
