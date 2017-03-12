from common.constants import TCP_PORT, HOME_IP


def read_conn_props(standard_ip=HOME_IP, standard_port=TCP_PORT, machine_role='client'):
    return read_ip(standard_ip, machine_role), read_port(standard_port, machine_role)


def read_ip(standard_ip=HOME_IP, machine_role='client'):
    ip = input('Ip to ' + machine_role + ' calc-server (blank for ' + str(standard_ip) + '): ')
    if not ip:
        ip = standard_ip
    return ip


def read_port(standard_port=TCP_PORT, machine_role='client'):
    port = input('Port to ' + machine_role + ' calc-server (blank for ' + str(standard_port) + '): ')
    if not port:
        port = standard_port
    return int(port)


def read_calc_input():
    op = input('Operation (0: add, 1: subtract, q: quit program): ')
    if op == 'q':
        return op, 0, 0
    num1 = input('Number 1: ')
    num2 = input('Number 2: ')
    return op, num1, num2


def validate_input(op, num1, num2):
    try:
        ['0', '1', 'q'].index(op)
        float(num1)
        float(num2)
        return True
    except ValueError:
        return False
