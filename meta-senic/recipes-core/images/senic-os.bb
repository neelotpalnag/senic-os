LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit core-image

IMAGE_BOOT_FILES = "zImage zImage-${KERNEL_DEVICETREE} boot.scr"

IMAGE_INSTALL = " \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "bluez5-noinst-tools", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "linux-firmware-mediatek", "",d)} \	
	packagegroup-core-boot \
	packagegroup-base \	
	packagegroup-core-ssh-openssh \
	ifupdown \
	openssl \
	lighttpd \
	os-release \
	glibc-locale \
	glibc-utils \ 
	networkmanager \
	ntp \
	python \
	python-supervisor \
	python3-netifaces \
	python3-multiprocessing \
	python3-cffi \
	python3-cryptography \
	python3 \
	python3-dbus \
	python3-pygobject \
	hub-configuration \
"

# Custom kernel module to handle Button interrupts
# IMAGE_INSTALL_append = " senic-button"

# create users
inherit extrausers
EXTRA_USERS_PARAMS = "\
  groupadd ${SNC_RUNTIME_USER}; \
  groupadd ${SNC_BUILD_USER}; \
  useradd -u 1000 -g ${SNC_BUILD_USER} -G ${SNC_RUNTIME_USER} -d /home/${SNC_BUILD_USER} ${SNC_BUILD_USER}; \
  useradd -u 1001 -g ${SNC_RUNTIME_USER} -d ${SNC_BACKEND_DEPLOY_LOCATION} ${SNC_RUNTIME_USER}; \
  "

