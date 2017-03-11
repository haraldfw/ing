import socket
from threading import Thread

from common.constants import BUFFER_SIZE

s = None


def read_listen_props():
    ip = input('Ip to listen on (blank for 0.0.0.0): ')
    if not ip:
        ip = '0.0.0.0'
    port = input('Port to listen on (blank for 80): ')
    if not port:
        port = 80
    return ip, int(port)


def start_socket():
    global s
    ip, port = read_listen_props()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((ip, port))
    s.listen(10)
    print('Socket started')


def client_handler(connection, address):
    try:
        data = connection.recv(BUFFER_SIZE).decode('utf-8')
    except ConnectionResetError:
        print('Client ' + str(address) + ' closed by remote host')
        connection.close()
        return
    headers = str(data).split('\n')
    lis = []
    for h in headers:
        if h:
            lis.append('<li>{}</li>'.format(h))
    packet = "HTTP/1.0 200 OK\n" \
             "Content-Type: text/html\n" \
             "\n" \
             "<html><body>" \
             "<H1> Hilsen. Du har koblet deg opp til min enkle web-tjener </h1>" \
             "Header fra klient er:" \
             "<ul>\n"

    packet += ''.join(lis) + "</ul></body></html>"

    connection.send(packet.encode('utf8'))
    print('Done handling request. Exiting client-handler')
    connection.close()


def accept_connections():
    global s
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
