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
PV = "0.2.52+d362204"
SRC_URI[sha256sum] = "bff152382e996c73960ec996b950ad84fc9a3220a404ab466bf375a2ff71bbe4"
PYPI_SRC_URI = "https://pypi.senic.com/getsenic/master/+f/bff/152382e996c73/senic_hub-0.2.52+d362204.tar.gz"


do_install_append() {
  # remove static docs
  rm -rf ${D}/usr/share/senic-hub-docs
}
