import socket

from common.constants import UDP_SERV_INPUT_PORT, HOME_IP, BUFFER_SIZE, UDP_CLNT_INPUT_PORT, LISTEN_ALL_IP
from common.input_helper import read_calc_input, read_conn_props, validate_input
import common.qpacket as qpacket
import common.ans_packet as ans_packet

sock = None


def start_socket():
    global sock
    sock = socket.socket(socket.AF_INET,  # Internet
                         socket.SOCK_DGRAM)  # UDP
    ip, port = read_conn_props(standard_ip=LISTEN_ALL_IP, standard_port=UDP_CLNT_INPUT_PORT, machine_role='client')
    sock.bind((ip, port))


def main():
    start_socket()
    global sock
    ip, port = read_conn_props(standard_ip=HOME_IP, standard_port=UDP_SERV_INPUT_PORT, machine_role='server')
    while True:
        op, num1, num2 = read_calc_input()
        if op.find('q') != -1:
            sock.close()
            print('Client actively quit. Socket closed')
        if not validate_input(op, num1, num2):
            print('Invalid input')
            continue
        packet = qpacket.from_values(op, num1, num2)
        sock.sendto(packet.packet_bytes, (ip, port))
        packet = ans_packet.from_bytes(sock.recv(BUFFER_SIZE))
        print('Answer returned from server: ' + str(packet.value))

if __name__ == '__main__':
    main()
