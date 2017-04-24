LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit core-image

IMAGE_BOOT_FILES = "zImage zImage-${KERNEL_DEVICETREE} boot.scr"

IMAGE_INSTALL = " \
	packagegroup-core-boot \
	packagegroup-base \
	ifupdown \
"
