import socket

import server.chatroom as chatroom

rooms = {}


def bts(data):
    return data.decode('utf-8')


def handle_join(addr, data):
    print('handling join')
    split_index = str(data).find(',')
    user_name = data[0:split_index]
    room_name = data[split_index + 1:]

    if room_name not in rooms:
        room = chatroom.ChatRoom()
        rooms[room_name] = room
        print('Created room ' + room_name)

    room = rooms[room_name]
    room.join(user_name, addr)

    print('User ' + user_name + ' joined room ' + room_name)


def handle_message(data):
    print('handling msg')
    split_index = str(data).find(',')
    room_name = data[0:split_index]
    message = data[split_index + 1:]
    room = rooms[room_name]
    room.send(message)


def handle_disconnect(addr):
    print('handling disconnect')
    for r in rooms:
        r.leave(addr)


def handle_request(data, addr):
    print(data)
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
    conn = None
    try:
        while 1:
            sock.listen(1)
            conn, addr = sock.accept()

            data = conn.recv(1024)
            handle_request(bts(data), addr[0])
    finally:
        conn.close()
        sock.close()


if __name__ == '__main__':
    main()
