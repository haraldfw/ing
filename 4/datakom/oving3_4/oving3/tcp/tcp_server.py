import socket
from _thread import start_new_thread

import common.ans_packet as ans_packet
import common.qpacket as qpacket
from common.constants import BUFFER_SIZE

s = None


def read_listen_props():
    ip = input('Ip to listen on (blank for 0.0.0.0): ')
    if not ip:
        ip = '0.0.0.0'
    port = input('Port to listen on (blank for 5005): ')
    if not port:
        port = 5005
    return ip, int(port)


def start_socket():
    global s
    ip, port = read_listen_props()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((ip, port))
    s.listen(10)
    print('Socket started')


def client_handler(connection, address):
    print('Client handler started for address ' + str(address))
    while True:
        try:
            data = connection.recv(BUFFER_SIZE)
        except ConnectionResetError:
            print('Client ' + str(address) + ' closed by remote host')
            break
        if not data:
            continue
        packet = qpacket.from_bytes(data)
        if packet.op.find('q') != -1:
            connection.close()
            break
        ans = -1337
        if packet.op == '0':
            ans = packet.num1 + packet.num2
        elif packet.op == '1':
            ans = packet.num1 - packet.num2
        print('Data sent to ' + str(address) + ': ' + str(ans))
        packet = ans_packet.from_float(ans)
        connection.send(packet.packet_bytes)


def accept_connections():
    global s
    print('Started accepting connections...')
    while True:
        conn, address = s.accept()
        print('Accepted new connection with address ' + str(address))
        start_new_thread(client_handler, (conn, address,))


def main():
    start_socket()
    accept_connections()


# TODO implement PING/PONG to close connections


if __name__ == '__main__':
    main()
