import socket

sock = None


def satb(strings):
    return ','.join(strings).encode('utf8')


def build_join_request(room_name, username):
    return satb(['0', username, room_name])


def build_disconnect_request():
    return satb(['1'])


def build_message_request(room_name, message):
    return satb(['2', room_name, message])


def main():
    global sock
    room_name = input("Room name: ")[:12]
    username = input("Username: ")[:12]
    server_ip = input("Server ip: ")

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((server_ip, 5005))

    sock.send(build_join_request(room_name, username))

    while 1:
        message = input("Input message or '0' to disconnect\n")
        if message:
            if message == '0':
                sock.send(build_disconnect_request())
                break
            else:
                sock.send(build_message_request(room_name, message))


if __name__ == "__main__":
    main()
