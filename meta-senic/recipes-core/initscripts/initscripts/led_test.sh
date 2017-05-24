#!/bin/sh
### BEGIN INIT INFO
# Provides: led_test
# Required-Start:
# Required-Stop:
# Default-Start:     S
# Default-Stop:
# Short-Description: 	Set Heartbeat trigger to Blue LED 
#						and switch of Green LED. This can be done 
#						with Device Tree as well.
### END INIT INFO

echo -e "[Senic Hub] Triggering Blue and Green LEDs"

echo none > /sys/class/leds/nanopi:green:pwr/trigger
echo 0 > /sys/class/leds/nanopi:green:pwr/brightness

echo heartbeat > /sys/class/leds/nanopi:blue:status/trigger
