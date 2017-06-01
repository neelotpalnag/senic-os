#!/bin/sh
### BEGIN INIT INFO
# Provides: esd_test
# Required-Start:
# Required-Stop:
# Default-Start:     S
# Default-Stop:
# Short-Description: Test suite to test survival on ESD / EMV Test 
### END INIT INFO

echo -e "[Senic Hub] ESD survival test"
echo none > /sys/class/leds/nanopi:blue:status/trigger
echo 0 > /sys/class/leds/nanopi:blue:status/brightness
echo 198 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio198/direction
echo 1 > /sys/class/gpio/gpio198/value

USB_LIST="$(usb-devices)"
IS_WLAN="$(echo "$USB_LIST" | grep mt7601u | wc -l)"
IS_BLE="$(echo "$USB_LIST" | grep btusb | wc -l)"

switch_led_on()
{
	echo 255 > /sys/class/leds/nanopi:blue:status/brightness
	echo 0 > /sys/class/gpio/gpio198/value
}

switch_led_off()
{
	echo 0 > /sys/class/leds/nanopi:blue:status/brightness
	echo 1 > /sys/class/gpio/gpio198/value
}

# Check if WiFi dongle is found
if [ $IS_WLAN -gt 0 ]; then
	echo " WLAN Dongle is found"	
else
	echo " WLAN Dongle is not found"	
fi

# Check if Bluetooth dongle is found
if [ $IS_BLE -gt 0 ]; then
	echo " BLE Dongle is found"	
else 
	echo " BLE Dongle is not found"	
fi

# Switch LED on if tests passed
if [ $IS_WLAN -gt 0 ] && [ $IS_BLE -gt 0 ]; then
	echo " TEST passed"	
	switch_led_on
else
	echo " TEST failed"	
	switch_led_off
fi
