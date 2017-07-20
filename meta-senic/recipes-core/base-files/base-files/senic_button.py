#!/usr/bin/python

import commands
from daemonize import Daemonize
import select
import time
import os

LOG_NAME = 'senic_button'
DAEMON_PID = '/var/run/senic_button.pid'

GPIO_PIN = '199'
GPIO_PATH = '/sys/class/gpio/'
PIN_PATH = GPIO_PATH + 'gpio' + GPIO_PIN + '/'

DEBOUNCE = 0.05     # Ignore repeated button presses for the first 50ms
FACTORY_RESET = 10  # Factory reset after this many seconds
MAX_DURATION = 30   # Ignore button presses longer than this many seconds


def init_gpio():
    if not os.path.exists(PIN_PATH):
        write_file(GPIO_PATH + 'export', GPIO_PIN)
    write_file(PIN_PATH + 'direction', 'in')
    write_file(PIN_PATH + 'active_low', '1')
    write_file(PIN_PATH + 'edge', 'both')


def write_file(path, value):
    with open(path, 'w') as file_descriptor:
        file_descriptor.write(value)


def execute_and_log(command):
    daemon.logger.info("Executing: %s", command)
    output = commands.getstatusoutput(command)
    if output[1]:
        for line in output[1].split('\n'):
            daemon.logger.info("    " + line)


def on_short_press(press_duration):
    daemon.logger.info(
            "Reset button held for %0.2f seconds: REBOOT" %
            press_duration)
    execute_and_log('/sbin/reboot')
    exit()


def on_long_press(press_duration):
    daemon.logger.info(
            "Reset button held for %0.2f seconds: FACTORY RESET" %
            press_duration)
    execute_and_log('/usr/bin/nmcli connection del bluenet')
    exit()


def listen_for_button_presses():
    init_gpio()

    pin_value = open(PIN_PATH + 'value', 'r')
    pin_event = select.poll()
    pin_event.register(pin_value, select.POLLPRI)

    start = 0
    while True:
        pin_event.poll(None)  # Blocking until button state change

        pin_value.seek(0)
        state = pin_value.read()

        if state == "1\n":
            start = time.time()
        else:
            end = time.time()
            press_duration = end - start

            if press_duration > DEBOUNCE and press_duration < FACTORY_RESET:
                on_short_press(press_duration)
            elif press_duration >= FACTORY_RESET and \
                    press_duration <= MAX_DURATION:
                on_long_press(press_duration)


daemon = Daemonize(app=LOG_NAME,
                   pid=DAEMON_PID,
                   action=listen_for_button_presses)

daemon.start()
