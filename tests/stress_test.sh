#!/bin/sh
### BEGIN INIT INFO
# Provides: stress_test
# Required-Start:
# Required-Stop:
# Default-Start:     S
# Default-Stop:
# Short-Description: Put all four CPUs into 100% load
### END INIT INFO

echo -e "\n [Senic Hub] Starting stress test"
stress -c 4 -q &
