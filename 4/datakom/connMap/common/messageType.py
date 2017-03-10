from enum import Enum


class MessageType(Enum):
    CONNECT = 0
    DISCONNECT = 1
    MESSAGE = 2
    COMMAND = 3
