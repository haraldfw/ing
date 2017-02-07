import client


class ChatRoom:
    clients = []

    def join(self, name, ip):
        self.clients.append(client.Client(name, ip))

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
