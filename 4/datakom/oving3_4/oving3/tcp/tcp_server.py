import socket
from threading import Thread

import common.ans_packet as ans_packet
import common.qpacket as qpacket
from common.constants import BUFFER_SIZE
from common.input_helper import read_conn_props

sock = None


def start_socket():
    global sock
    ip, port = read_conn_props(standard_ip='0.0.0.0')
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
    global sock
    print('Started accepting connections...')
    while True:
        conn, address = s.accept()
        print('Accepted new connection with address ' + str(address))
        Thread(target=client_handler, args=(conn, address,)).start()


def main():
    start_socket()
    accept_connections()


if __name__ == '__main__':
    main()
