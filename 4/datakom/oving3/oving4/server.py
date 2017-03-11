import socket
import struct

BUFFER_SIZE = 1024
s = None


def read_listen_props():
    ip = input('Ip to listen on (blank for 0.0.0.0): ')
    if not ip:
        ip = '0.0.0.0'
    port = int(input('Port to listen on: '))
    return ip, port


def start_socket():
    global s
    ip, port = read_listen_props()
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  # datagram socket
    s.bind((ip, port))
    print('Listening...')
    return port


def main():
    global s
    port = start_socket()
    while (True):
        data, addr = s.recvfrom(BUFFER_SIZE)
        data = data.decode('utf-8')
        print(data)
        values = data.split(sep=',')
        op = values[0]
        num1 = float(values[1])
        num2 = float(values[2])
        ans = -1337
        if op == '0':
            ans = num1 + num2
        elif op == '1':
            ans = num1 - num2
        s.sendto(struct.pack('f', ans), (addr, port))


if __name__ == '__main__':
    main()
