SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "(senic-hub-beta|senic-hub)"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc

RDEPENDS_kernel-base=""

KBRANCH = "senic/4.10"
SRCREV = "b18fe1a6fd81ac94f85cbf58ce3b4dbed1f7d7f4"

PV = "4.10+git${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/getsenic/senic-os-linux.git;protocol=git;branch=${KBRANCH} \
        file://defconfig \
        "