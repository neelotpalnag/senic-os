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
PV = "0.2.136+1dca0ad"
SRC_URI[sha256sum] = "aa5c54f87539922164cccf5546471b5b9078e3701e589093e340a8ebffd9c81e"
PYPI_SRC_URI = "https://pypi.senic.com/getsenic/master/+f/aa5/c54f875399221/senic_hub-0.2.136+1dca0ad.tar.gz"

do_install_append() {
  # remove static docs
  rm -rf ${D}/usr/share/senic-hub-docs
}
