import socket

s = None


def satb(strings):
    return ','.join(strings).encode('utf8')


def build_join_request(room_name, username):
    return satb(['0', username, room_name])


def build_disconnect_request():
    return satb(['1'])


def build_message_request(room_name, message):
    return satb(['2', room_name, message])


def tcp_connect(ip, port):
    global s
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((ip, port))


def main():
    global s
    room_name = input("Room name: ")[:12]
    username = input("Username: ")[:12]
    server_ip = input("Server ip: ")

    tcp_connect(server_ip, 5005)

    s.send(build_join_request(room_name, username))

    while 1:
        message = input("Input message or '0' to disconnect\n")
        print('msg: ' + message)
        if message:
            if message == '0':
                s.send(build_disconnect_request())
                break
            else:
                print('sending message')
                s.send(build_message_request(room_name, message))
        else:
            print('message is None')


if __name__ == "__main__":
    main()
