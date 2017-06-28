SUMMARY = "Transaction management for Python"
HOMEPAGE = "https://github.com/zopefoundation/transaction"
LICENSE = "ZPL"
LIC_FILES_CHKSUM = "file://COPYRIGHT.txt;md5=9c79c4dbe7ac16ff23aa2072665b3d9a \
                    file://LICENSE.txt;md5=78ccb3640dc841e1baecb3e27a6966b2"

inherit setuptools
inherit pypi

RDEPENDS_${PN} += "python3-zope.interface"
SRC_URI[md5sum] = "375a7f9d434ad0e9bf2039a3a0588e2b"
