import socket


class Client:
    sock = None
    name = None

    def __init__(self, name, ip):
        self.name = name
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.connect((ip, 5005))

    def disconnect(self):
        self.sock.close()

    def send(self, message):
        self.sock.send(message)

    def receive(self):
        return self.sock.recv(1024)
