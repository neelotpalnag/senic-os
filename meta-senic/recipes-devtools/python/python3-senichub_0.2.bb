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
PV = "0.2.58+ebcc65a"
SRC_URI[sha256sum] = "74c20a7e2fcb4e9c24e18de409cc6736d5551ef6bfe5ba67c164380f02bafd6a"
PYPI_SRC_URI = "https://pypi.senic.com/getsenic/master/+f/74c/20a7e2fcb4e9c/senic_hub-0.2.58+ebcc65a.tar.gz"


do_install_append() {
  # remove static docs
  rm -rf ${D}/usr/share/senic-hub-docs
}
