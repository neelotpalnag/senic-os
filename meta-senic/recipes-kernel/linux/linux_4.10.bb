SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "(nanopi-m1|senic-hub-beta|nanopi-neo|senic-hub)"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc

RDEPENDS_kernel-base=""

KBRANCH = "senic/4.10"
SRCREV = "aeb0237178d7a1d72e157fc3c7667580fd567d14"

PV = "4.10+git${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/getsenic/senic-os-linux.git;protocol=git;branch=${KBRANCH} \
        file://defconfig \
        "