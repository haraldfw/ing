def from_bytes(packet_bytes):
    data = packet_bytes.decode('utf-8')
    values = data.split(',')
    op = values[0]
    if str(op).find('q') != -1:
        return QPacket(packet_bytes, op, -1, -1)
    num1 = float(values[1])
    num2 = float(values[2])
    return QPacket(packet_bytes, op, num1, num2)


def from_values(op, num1, num2):
    packet_bytes = ','.join([op, num1, num2]).encode('utf-8')
    return QPacket(packet_bytes, op, float(num1), float(num2))


class QPacket:

    packet_bytes = None
    op = ''
    num1 = 0
    num2 = 0

    def __init__(self, packet_bytes, op, num1, num2):
        self.packet_bytes = packet_bytes
        self.op = op
        self.num1 = num1
        self.num2 = num2
