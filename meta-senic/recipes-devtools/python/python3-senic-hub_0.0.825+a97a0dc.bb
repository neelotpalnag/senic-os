DESCRIPTION = "The main application powering the Senic Hub"
HOMEPAGE = "https://developers.senic.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SNC_DEVPI_INDEX = "getsenic/master"
PYPI_PACKAGE = "senic_hub"
RPROVIDES_${PN} += "python3-senic-hub"
SRC_URI[sha256sum] = "bb50b6ca111a4bcaea4a2660c2d48924f63f68cd07717fa7f0ff49a729747887"
inherit setuptools3
inherit pypi

do_install() {
  # remove static docs
  rm -rf ${D}/usr/share/senic-hub-docs
}
