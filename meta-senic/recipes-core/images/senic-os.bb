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
	python3-asn1crypto \
	python3-translationstring \
	python3-netifaces \
	python3-multiprocessing \
    python3-chardet \
    python3-colander \
    python3-cffi \
    python3-click \
    python3-idna \
    python3-pyyaml \
    python3-requests \
    python3-six \
    python3-urllib3 \
	python3-cffi \
	python3-cryptography \
	python3-venusian \
	python3-webob \
	python3-websocket-client \
	python3-xmltodict \
	python3-zeroconf \
	python3-zope.deprecation \
	python3-zope.interface \
	python3 \
	python3-dbus \
	python3-pygobject \
	hub-configuration \
"

# Custom kernel module to handle Button interrupts
# IMAGE_INSTALL_append = " senic-button"

