SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "(senic-hub-beta|senic-hub)"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc

KBRANCH = "senic/4.13"
SRCREV = "59f592f0abeb1b3a936cd531885426ac798efa0c"

PV = "4.13+git${SRCPV}"
S = "${WORKDIR}/git"

KERNEL_DEVICETREE+="sun8i-h3-senic-hub.dtb"

SRC_URI = "git://github.com/getsenic/senic-os-linux.git;protocol=git;branch=${KBRANCH} \
        file://defconfig \
        "
