import socket
import threading
from threading import Thread

import struct

BUFFER_SIZE = 1024
s = None

connectionThreads = []


def read_listen_props():
    ip = input('Ip to listen on (blank for 0.0.0.0): ')
    if not ip:
        ip = '0.0.0.0'
    port = int(input('Port to listen on: '))
    return ip, port


def start_socket():
    global s
    ip, port = read_listen_props()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((ip, port))
    s.listen(1)
    print('Listening...')


def client_handler(connection, addr):
    while True:
        data = connection.recv(BUFFER_SIZE).decode('utf-8')
        print(data)
        if data.find('q') > -1:
            connection.close()
            # FIXME
            connectionThreads.pop(connectionThreads.index((connection, addr)))
            break
        values = data.split(sep=',')
        op = values[0]
        num1 = float(values[1])
        num2 = float(values[2])
        ans = -1337
        if op == '0':
            ans = num1 + num2
        elif op == '1':
            ans = num1 - num2
        connection.send(struct.pack('f', ans))



def accept_connections():
    while True:
        print('Started accepting connections')
        conn, addr = s.accept()
        connectionThreads.append(Thread(target=client_handler(conn, addr)))


def main():
    start_socket()
    Thread(target=accept_connections()).start()

# TODO implement PING/PONG to close connections


if __name__ == '__main__':
    main()
