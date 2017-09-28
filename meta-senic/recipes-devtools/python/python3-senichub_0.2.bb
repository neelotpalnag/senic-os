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
PV = "0.2.183+2b8fe8e"
SRC_URI[sha256sum] = "7a363d57a661917f34c1465271efaacd0787dac24599c3f536c1e12fc04c8672"
PYPI_SRC_URI = "https://pypi.senic.com/getsenic/master/+f/7a3/63d57a661917f/senic_hub-0.2.183+2b8fe8e.tar.gz"

do_install_append() {
  # remove static docs
  rm -rf ${D}/usr/share/senic-hub-docs
}
