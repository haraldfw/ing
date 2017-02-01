import sys
import threading
from math import sqrt

max_value = 0
to_check = 0
done = False


def get_next_number():
    global to_check, done, max_value
    n = to_check
    to_check += 2
    done = to_check > max_value
    return n


def is_prime(number):
    root = sqrt(number)
    if number % 2 == 0 or root % 1 == 0:
        return False
    i = 3
    while i < root:
        if number % i == 0:
            return False
        i += 2
    return True 


def prime_checker(primes):
    global done
    while not done:
        num = get_next_number()
        if is_prime(num):
            primes.append(num)


def print_primes_in_range(thread_num, rng_start, rng_end):
    global to_check, max_value
    to_check = rng_start
    max_value = rng_end
    primes = []
    threads = []
    for i in range(thread_num):
        threads.append(threading.Thread(target=prime_checker, args=(primes,)))
    for t in threads:
        t.start()
    for t in threads:
        t.join()

    primes.sort()
    print(primes)


def primes_in_range(rng_start, rng_end):
    for i in range(rng_start, rng_end):
        if is_prime(i):
            print(i)


def main():
    usage = 'usage: python.py <number to check>' \
            '\npython primes.py <threads> <number range start> <number range end>'
    args = map(int, sys.argv[1:])
    if len(args) == 1:
        print(is_prime(args[0]))
    elif len(args) == 3:
        print_primes_in_range(args[0], args[1], args[2])
    else:
        print('Invalid number of arguments given. ' + usage)


if __name__ == '__main__':
    main()
