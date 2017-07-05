SUMMARY = "A library to work with OSRAM lightify."
HOMEPAGE = "https://github.com/aneumeier/python-lightify"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3
inherit pypi
PV = "2.0.2-senic"
PYPI_PACKAGE = "python-networkmanager"
PROVIDES += "python3-python-networkmanager"
RPROVIDES_${PN} += "python3-python-networkmanager"
SNC_DEVPI_INDEX = "getsenic/master"
SRC_URI[sha256sum] = "74f58558122b98bea9ce6e1a72584c515cbf0c8bb48c20131fcbc49dce99454a"
