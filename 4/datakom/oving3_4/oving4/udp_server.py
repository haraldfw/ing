import socket
import struct

import common.qpacket as qpacket
import common.ans_packet as anspacket
from common.constants import UDP_CLNT_INPUT_PORT, UDP_SERV_INPUT_PORT, BUFFER_SIZE, LISTEN_ALL_IP
from common.input_helper import read_conn_props, read_port

sock = None
send_port = UDP_CLNT_INPUT_PORT


def start_socket():
    global sock, send_port
    ip, port = read_conn_props(standard_ip=LISTEN_ALL_IP, standard_port=UDP_SERV_INPUT_PORT)
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.bind((ip, port))
    send_port = read_port(standard_port=UDP_CLNT_INPUT_PORT, machine_role='client')
    print('Listening...')


def main():
    global sock
    start_socket()
    while True:
        try:
            data, addr = sock.recvfrom(BUFFER_SIZE)
        except ConnectionResetError:
            continue
        print('data recieved')
        packet = qpacket.from_bytes(data)
        ans = -1337
        if packet.op == '0':
            ans = packet.num1 + packet.num2
        elif packet.op == '1':
            ans = packet.num1 - packet.num2
        packet = anspacket.from_float(ans)
        print(str(addr))
        print(str(send_port))
        sock.sendto(packet.packet_bytes, (addr[0], send_port))


if __name__ == '__main__':
    main()
