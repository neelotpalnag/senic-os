DESCRIPTION = "The main application powering the Senic Hub"
HOMEPAGE = "https://developers.senic.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3
inherit pypi

PROVIDES += "python3-senic-hub"
RPROVIDES_${PN} += "python3-senic-hub"

# point to our own devpi instance
SNC_DEVPI_INDEX = "tomster/master"
PYPI_PACKAGE = "senic_hub"
# version specific info, change this for new releases
PV = "0.2.241+af8069e"
SRC_URI[sha256sum] = "7d9d38f4be8a7d5da7e75ff3330fba904ee651e0d0ba2f83efb0d44a23a7a4cc"
PYPI_SRC_URI = "https://pypi.senic.com/getsenic/master/+f/7d9/d38f4be8a7d5d/senic_hub-0.2.241+af8069e.tar.gz"

do_install_append() {
  # remove static docs
  rm -rf ${D}/usr/share/senic-hub-docs
}
