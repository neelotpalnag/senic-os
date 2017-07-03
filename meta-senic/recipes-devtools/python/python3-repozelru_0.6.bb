SUMMARY = "A tiny LRU cache implementation and decorator"
HOMEPAGE = "http://www.repoze.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${THISDIR}/licenses/REPOZE_LICENSE.txt;md5=2513261c6e403f9e6fd2ae3ce8f2c710"

SRC_URI[md5sum] = "2c3b64b17a8e18b405f55d46173e14dd"
PYPI_PACKAGE = "repoze.lru"
RPROVIDES_${PN} += "python3-repoze-lru"
inherit pypi setuptools3

