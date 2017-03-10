separator = '|/|'
escaped_separator = '|//|'


def escape(data):
    return data.replace(separator, escaped_separator)


def unescape(data):
    return data.replace(escaped_separator, separator)


def build_message(data_array):
    return separator.join([escape(e) for e in data_array])


def parse_message(data):
    data_array = [unescape(e) for e in data.split(separator)]
    message_type = data_array[0]
    sender_ip = data_array[1]
    sender_name = data_array[2]

