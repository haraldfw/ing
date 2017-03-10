from common.message import Message


class TextMessage(Message):

    text = ''

    def __init__(self, text, sender_ip, sender_name):
        self.text = text
        self.sender_ip = sender_ip
        self.sender_name = sender_name

    def create_text_message(self, data):
        elem_amount = len(data)

        text = ''
        ip = ''
        name = ''

        if elem_amount >= 2:
            ip = data[0]
            name = data[1]
            if elem_amount >= 3:
                text = data[2]

        return TextMessage(text, ip, name)
