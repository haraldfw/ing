import socket

import struct

BUFFER_SIZE = 1024
s = None


def read_conn_props():
    ip = input('Ip to calc-server: ')
    port = int(input('Port to calc-server: '))
    return ip, port


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
            print('Client actively quit the application. Exiting...')
            s.send(op.encode('utf-8'))
            break
        if not validate_input(op, num1, num2):
            print('Invalid input')
            continue
        s.send(','.join([op, num1, num2]).encode('utf-8'))
        print(struct.unpack('f', s.recv(BUFFER_SIZE)))
    s.close()


if __name__ == "__main__":
    main()
