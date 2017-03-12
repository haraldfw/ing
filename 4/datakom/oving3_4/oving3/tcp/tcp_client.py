import socket

import common.ans_packet as ans_packet
import common.qpacket as qpacket
from common.constants import BUFFER_SIZE
from common.input_helper import read_conn_props, read_calc_input, validate_input

sock = None


def connect():
    global sock
    ip, port = read_conn_props(machine_role='server')
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((ip, port))


def main():
    connect()
    while True:
        op, num1, num2 = read_calc_input()
        if op == 'q':
            print('Client actively quit the application. Sending exit-signal...')
            sock.send(op.encode('utf-8'))
            break
        if not validate_input(op, num1, num2):
            print('Invalid input')
            continue
        packet = qpacket.from_values(op, num1, num2)
        sock.send(packet.packet_bytes)
        packet = ans_packet.from_bytes(sock.recv(BUFFER_SIZE))
        print('Answer returned from server: ' + str(packet.value))
    sock.close()


if __name__ == "__main__":
    main()
