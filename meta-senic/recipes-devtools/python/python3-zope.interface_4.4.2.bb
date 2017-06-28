SUMMARY = "Interfaces for Python"
HOMEPAGE = "https://github.com/zopefoundation/zope.interface"
LICENSE = "ZPL"
LIC_FILES_CHKSUM = "file://COPYRIGHT.txt;md5=9c79c4dbe7ac16ff23aa2072665b3d9a \
                    file://LICENSE.txt;md5=78ccb3640dc841e1baecb3e27a6966b2"

inherit setuptools
inherit pypi

RDEPENDS_${PN} += "python-setuptools"
RDEPENDS_${PN} += "python-core python-io python-netclient python-shell python-subprocess python-textutils"
SRC_URI[md5sum] = "d69131352ef06753388c3da09cd8c8aa"
