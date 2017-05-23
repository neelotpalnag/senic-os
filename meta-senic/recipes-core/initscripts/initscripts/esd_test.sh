#!/bin/bash
### BEGIN INIT INFO
# Provides: stress_test
# Required-Start:
# Required-Stop:
# Default-Start:     S
# Default-Stop:
# Short-Description: Test suite to test survival on ESD / EMV Test 
### END INIT INFO

echo -e "[Senic Hub] ESD survival test"
echo none > /sys/class/leds/nanopi:blue:status/trigger
echo 0 > /sys/class/leds/nanopi:blue:status/brightness

USB_LIST="$(usb-devices)"
IS_WLAN="$(echo "$USB_LIST" | grep mt7601u | wc -l)"
IS_BLE="$(echo "$USB_LIST" | grep btusb | wc -l)"

switch_led_on()
{
	echo 255 > /sys/class/leds/nanopi:blue:status/brightness
}

switch_led_off()
{
	echo 0 > /sys/class/leds/nanopi:blue:status/brightness
}

# Check if WiFi dongle is found
if [ $IS_WLAN -gt 0 ]; then
	echo " WLAN Dongle is found"
	switch_led_on
else
	echo " WLAN Dongle is not found"
	switch_led_off
fi

# Check if Bluetooth dongle is found
if [ $IS_BLE -gt 0 ]; then
	echo " BLE Dongle is found"
	switch_led_on
else 
	echo " BLE Dongle is not found"
	switch_led_off
fi