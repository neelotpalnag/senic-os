SUMMARY = "Python support for YAML"
HOMEPAGE = "http://www.pyyaml.org"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6015f088759b10e0bc2bf64898d4ae17"
DEPENDS = "libyaml python-cython-native"


PYPI_PACKAGE = "PyYAML"
SRC_URI[md5sum] = "4c129761b661d181ebf7ff4eb2d79950"


inherit distutils3
inherit pypi

BBCLASSEXTEND = "native"

