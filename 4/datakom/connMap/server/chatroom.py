from server.client import Client

class ChatRoom:
    clients = []

    def client_sender(self, client):
        pass

    def parsemsg(self, data):
        pass

    @staticmethod
    def client_listener(client):
        while True:
            data = client.receive()
            msg =
            if msg:
                if msg == '/quit':
                    break
                else:

        pass

    def join(self, name, ip):
        self.clients.append(Client(name, ip))

    def leave(self, ip):
        c, i = self.getfromip(ip)
        if c and i:
            c.disconnect()
            self.clients.pop(i)

    def getfromip(self, ip):
        i = 0
        for c in self.clients:
            if c.ip == ip:
                return c, i
            i += 1

    def send(self, message):
        for c in self.clients:
            c.send(message)
