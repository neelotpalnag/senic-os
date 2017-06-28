SUMMARY = "Utility library for i18n relied on by various Repoze and Pyramid packages"
HOMEPAGE = "https://github.com/Pylons/translationstring"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYRIGHT.txt;md5=449584db8cda6485cdff4f6cee4a08d8 \
                    file://LICENSE.txt;md5=2b9c5a7e3979930ff2a19f1cb309bb7e"

SRC_URI[md5sum] = "a4b62e0f3c189c783a1685b3027f7c90"

PV = "1.3"


inherit setuptools
inherit pypi

RDEPENDS_${PN} += "python-codecs python-core python-lang python-re python-unittest"
