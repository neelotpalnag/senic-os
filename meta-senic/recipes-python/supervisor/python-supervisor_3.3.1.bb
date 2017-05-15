inherit pypi setuptools
SUMMARY = "A system for controlling process state under UNIX"
DESCRIPTION = "\
Supervisor is a client/server system that allows its users to control a number of processes on UNIX-like operating systems."
HOMEPAGE = "http://supervisord.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSES.txt;md5=5b4e3a2172bba4c47cded5885e7e507e"
SRC_URI += "file://supervisor.conf \
           file://supervisor \
"
SRC_URI[md5sum] = "202f760f9bf4930ec06557bac73e5cf2"
PYPI_PACKAGE = "supervisor"
RDEPENDS_${PN} += "${PYTHON_PN}-meld3"

inherit update-rc.d

INITSCRIPT_NAME = "supervisor"
CONFFILES_${PN} = "${sysconfdir}/supervisor/supervisor.conf"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d ${D}${sysconfdir}/supervisor ${D}${sysconfdir}/supervisor/conf.d
	install -m 0755 ${WORKDIR}/supervisor ${D}${sysconfdir}/init.d
	install -m 0644 ${WORKDIR}/supervisor.conf ${D}${sysconfdir}/supervisor
}
