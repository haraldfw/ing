import socket

import struct

from common.constants import BUFFER_SIZE
import common.ans_packet as ans_packet
import common.qpacket as qpacket
s = None


def read_conn_props():
    ip = input('Ip to calc-server (blank for 127.0.0.1): ')
    if not ip:
        ip = '127.0.0.1'
    port = input('Port to calc-server (blank for 5005): ')
    if not port:
        port = 5005
    return ip, int(port)


def connect():
    global s
    ip, port = read_conn_props()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((ip, port))


def read_calc_input():
    op = input('Operation (0: add, 1: subtract, q: quit program): ')
    if op == 'q':
        return op, 0, 0
    num1 = input('Number 1: ')
    num2 = input('Number 2: ')
    return op, num1, num2


def validate_input(op, num1, num2):
    try:
        ['0', '1', 'q'].index(op)
        float(num1)
        float(num2)
        return True
    except ValueError:
        return False


def main():
    connect()
    while True:
        op, num1, num2 = read_calc_input()
        if op == 'q':
            print('Client actively quit the application. Sending exit-signal...')
            s.send(op.encode('utf-8'))
            break
        if not validate_input(op, num1, num2):
            print('Invalid input')
            continue
        packet = qpacket.from_values(op, num1, num2)
        s.send(packet.packet_bytes)
        packet = ans_packet.from_bytes(s.recv(BUFFER_SIZE))
        print('Answer returned from server: ' + str(packet.value))
    s.close()


if __name__ == "__main__":
    main()
