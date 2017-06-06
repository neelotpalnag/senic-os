#!/bin/sh
### BEGIN INIT INFO
# Provides: update_motd
# Required-Start:
# Required-Stop:
# Default-Start:     S
# Default-Stop:
# Short-Description: Update the motd file with actual data
### END INIT INFO

echo -e "[Senic Hub] Updating motd"

HW="$(uname -n)"
KERNEL="$(uname -r)"
BUILD_DATE="$(uname -v)"
VER="$(cat /etc/os-release | grep "VERSION=" | cut -d '"' -f 2)"
RELEASE="$(cat /etc/os-release | grep "ID=" | cut -d '"' -f 2 | awk 'NR==1')"
RAM_SIZE="$(awk '$3=="kB"{$2=$2/1024;$3="MB"} 1' /proc/meminfo | grep MemTotal | cut -d ':' -f2 | sed -e 's/^[ \t]*//')"
ROM_SIZE="$(fdisk -l | grep Disk | cut -d ':' -f 2 | cut -d ',' -f 1 | sed -e 's/^[ \t]*//')"
META_SENIC_SHA="$(cat /etc/os-release | grep "META_SENIC_SHA=" | cut -d '"' -f 2)"
BUILD_DATE="$(cat /etc/os-release | grep "BUILD_DATE=" | cut -d '"' -f 2)"

LINE1="Distro: Senic OS \t\t Version: $RELEASE $VER"
LINE2="Kernel: $KERNEL \t\t\t Hardware: $HW"
LINE3="RAM: $RAM_SIZE \t\t ROM: $ROM_SIZE"
LINE4="\nBuild Date: $BUILD_DATE"
LINE5="Git SHA: $META_SENIC_SHA"


cat /etc/motd_senic > /etc/motd
echo -e "\n" >>  /etc/motd
echo -e $LINE1 >>  /etc/motd
echo -e $LINE2 >>  /etc/motd
echo -e $LINE3 >>  /etc/motd
echo -e $LINE4 >>  /etc/motd
echo -e $LINE5 >>  /etc/motd
echo -e "\n" >>  /etc/motd
