import struct


def from_float(value):
    value = float(value)
    return AnsPacket(value, struct.pack('f', value))


def from_bytes(packet_bytes):
    val1 = struct.unpack('f', packet_bytes)[0]
    return AnsPacket(val1, packet_bytes)


class AnsPacket:
    packet_bytes = None
    value = 0

    def __init__(self, value, packet_bytes):
        self.value = value
        self.packet_bytes = packet_bytes
