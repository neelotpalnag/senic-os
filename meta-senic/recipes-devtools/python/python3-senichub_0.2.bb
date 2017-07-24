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
PV = "0.2.96+d3be7cd"
SRC_URI[sha256sum] = "aaf011c3ccde48f9712dff586b2f9634ecd5613bc04e1b8974c6015dcc2eafe8"
PYPI_SRC_URI = "https://pypi.senic.com/getsenic/master/+f/aaf/011c3ccde48f9/senic_hub-0.2.96+d3be7cd.tar.gz"

do_install_append() {
  # remove static docs
  rm -rf ${D}/usr/share/senic-hub-docs
}
