SUMMARY = "Python decorator that notifies via email (Gmail) the termination (and eventual stacktrace in case of failure) of a function"
HOMEPAGE = "https://github.com/GiulioRossetti/pynotify"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

inherit setuptools3
inherit pypi

SRC_URI[md5sum] = "2db76999db4bdfa0060cdb65914ef314"
