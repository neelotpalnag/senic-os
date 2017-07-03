SUMMARY = "A python library to manage encrypted YAML files."
HOMEPAGE = "https://github.com/getsenic/senic.cryptoyaml"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fd254597258a2e2af990cc804b8fe7f3"

inherit setuptools3
inherit pypi

PYPI_PACKAGE_EXT = "zip"
RDEPENDS_${PN} += "python3-pyyaml python3-click python3-cryptography"
SRC_URI[md5sum] = "4af0d2a6d31af3c03d8a0270a702838a"
