SUMMARY = "Kernel Driver to handle interrupts of Button on Senic Hub"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit module

SRC_URI = "file://COPYING \
           file://Makefile \
           file://senic-button.c \
          "

S = "${WORKDIR}"

# Load the module during the boot
KERNEL_MODULE_AUTOLOAD += "senic-button"
