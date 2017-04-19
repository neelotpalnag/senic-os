DESCRIPTION="Upstream's U-boot configured for sunxi devices"
LICENSE = "GPLv2"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

COMPATIBLE_MACHINE = "(nanopi-m1|senic-hub-beta)"

SRCBRANCH = "senic/v2017.03"
SRC_URI = "git://git@tau.free-electrons.com/senic/uboot.git;protocol=ssh;branch=${SRCBRANCH}; \
	   file://boot.cmd \
	   "

SRCREV = "10c97b571e72382fa75c10bb3b16bc6baff0d28e"
PV = "v2017.03+git${SRCPV}"
S = "${WORKDIR}/git"

SPL_BINARY="u-boot-sunxi-with-spl.bin"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

do_compile_append() {
    ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}