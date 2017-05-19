#!/bin/sh
### BEGIN INIT INFO
# Provides: stress_test
# Required-Start:
# Required-Stop:
# Default-Start:     S
# Default-Stop:
# Short-Description: Test suit to test survival on ESD 
### END INIT INFO

echo -e "\n [Senic Hub] ESD survival test"
IS_WLAN="$(usb-devices | grep mt7601u | wc -l)"
IS_BLE="$(usb-devices | grep btusb | wc -l)"

if [ $IS_WLAN -gt 0 ]; then
	echo " WLAN Dongle is found"
fi

if [ $IS_BLE -gt 0 ]; then
	echo " BLE Dongle is found"
fi