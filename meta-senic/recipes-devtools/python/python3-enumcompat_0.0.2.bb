SUMMARY = "enum/enum34 compatibility package"
HOMEPAGE = "https://github.com/jstasiak/enum-compat"
LICENSE = "LGPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.0;md5=9427b8ccf5cf3df47c29110424c9641a"

inherit setuptools3
inherit pypi

RPROVIDES_${PN} += "python3-enum-compat"
PYPI_PACKAGE = "enum-compat"
SRC_URI[md5sum] = "3002940b6620837d0fbc86ec72509be3"
