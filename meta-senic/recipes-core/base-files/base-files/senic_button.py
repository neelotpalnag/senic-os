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
        write_once(GPIO_PATH + 'export', GPIO_PIN)
    write_once(PIN_PATH + 'direction', 'in')
    write_once(PIN_PATH + 'active_low', '1')
    write_once(PIN_PATH + 'edge', 'both')


def write_once(path, value):
    with open(path, 'w') as f:
        f.write(value)


def execute_and_log(command):
    daemon.logger.info("Executing: %s", command)
    output = commands.getstatusoutput(command)
    if output[1]:
        for line in output[1].split('\n'):
            daemon.logger.info("    " + line)


def on_short_press(press_duration):
    daemon.logger.info("Reset button held for %0.2f seconds: REBOOT" %
                       press_duration)
    execute_and_log('/sbin/reboot')
    exit()


def on_long_press(press_duration):
    daemon.logger.info("Reset button held for %0.2f seconds: FACTORY RESET" %
                       press_duration)
    execute_and_log('/etc/init.d/supervisor stop')
    execute_and_log('/usr/bin/nmcli connection del bluenet')
    execute_and_log('/sbin/reboot')
    exit()


def listen_for_button_presses():
    init_gpio()

    f = open(PIN_PATH + 'value', 'r')
    po = select.poll()
    po.register(f, select.POLLPRI)

    start = 0
    while True:
        po.poll(None)  # Blocking until button state change

        f.seek(0)
        state = f.read()

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
