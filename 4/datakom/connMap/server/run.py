import socket

import chatroom

rooms = {}


def bts(data):
    return data.decode('utf-8')


def handle_join(addr, data):
    split_index = str(data).find(',')
    user_name = data[0:split_index]
    room_name = data[split_index + 1:]
    print("username: " + user_name)
    print('room name: ' + room_name)

    if room_name not in rooms:
        room = chatroom.ChatRoom()
        rooms[room_name] = room
        print('Created room ' + room_name)

    room = rooms[room_name]
    room.join(user_name, addr)


def handle_message(data):
    room_name = data[4:52]
    message = data[52:]
    room = rooms[room_name]
    room.send(message)


def handle_disconnect(addr):
    for r in rooms:
        r.leave(addr)


def handle_request(data, addr):
    request_type = data[:1]
    print(request_type)
    if request_type == '0':  # join
        handle_join(addr, data[2:])
    elif request_type == '1':  # leave
        handle_disconnect(addr)
    elif request_type == '2':  # message
        handle_message(data[2:])


def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind(('0.0.0.0', 5005))
    while 1:
        sock.listen(1)
        conn, addr = sock.accept()
        addr = addr[0]
        try:
            data = conn.recv(1024)
            handle_request(bts(data), addr)
        finally:
            conn.close()
            sock.close()


if __name__ == '__main__':
    main()
