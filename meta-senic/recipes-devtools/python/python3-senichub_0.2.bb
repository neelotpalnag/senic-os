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
SRC_URI[sha256sum] = "5357570f177fe5c12626028d15aa8f62eb4b69688d650f3ace72a2a335541b07"
PYPI_SRC_URI = "https://pypi.senic.com/getsenic/master/+f/535/7570f177fe5c1/senic_hub-0.2.60+71ebd3d.tar.gz"

do_install_append() {
  # remove static docs
  rm -rf ${D}/usr/share/senic-hub-docs
}
