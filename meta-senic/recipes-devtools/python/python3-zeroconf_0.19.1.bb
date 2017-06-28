SUMMARY = "Pure Python Multicast DNS Service Discovery Library (Bonjour/Avahi compatible)"
HOMEPAGE = "https://github.com/jstasiak/python-zeroconf"
LICENSE = "LGPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.0;md5=9427b8ccf5cf3df47c29110424c9641a"

inherit setuptools
inherit pypi

RDEPENDS_${PN} += "python-six"
RDEPENDS_${PN} += "python-core python-io python-lang python-logging python-re python-threading"

SRC_URI[md5sum] = "12d4cb1eef96c38cd8760a68a83809d4"
