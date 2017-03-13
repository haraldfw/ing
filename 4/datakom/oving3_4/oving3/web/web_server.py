import socket
from threading import Thread

from common.constants import BUFFER_SIZE, LISTEN_ALL_IP, HTTP_PORT
from common.input_helper import read_conn_props

sock = None


def start_socket():
    global sock
    ip, port = read_conn_props(standard_ip=LISTEN_ALL_IP, standard_port=HTTP_PORT)
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((ip, port))
    sock.listen(10)
    print('Socket started')


def client_handler(connection, address):
    try:
        data = connection.recv(BUFFER_SIZE).decode('utf-8')
    except ConnectionResetError:
        print('Client ' + str(address) + ' closed by remote host')
        connection.close()
        return
    lis = ('<li>{}</li>'.format(h) for h in str(data).split('\n'))

    packet = 'HTTP/1.0 200 OK\nContent-Type: text/html\n\n' \
             '<html><body><H1> Good day. This is my simple web-server coded using tcp-sockets in Python </h1>' \
             'Header(s) from the client:' \
             '<ul>{0}</ul></body></html>\n'.format(''.join(lis))

    connection.send(packet.encode('utf8'))
    print('Done handling request. Exiting client-handler')
    connection.close()


def accept_connections():
    global sock
    print('Started accepting connections...')
    while True:
        conn, address = sock.accept()
        print('Accepted new connection with address ' + str(address))
        Thread(target=client_handler, args=(conn, address,)).start()


def main():
    start_socket()
    accept_connections()


if __name__ == '__main__':
    main()
