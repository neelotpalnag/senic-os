FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RDEPENDS_${PN} += "\
  python \
"

SRC_URI += "file://motd_senic"
SRC_URI += "file://senic_button.py"

do_install_append() {

	# The extra files need to go in the respective directories
	install -m 0755 ${WORKDIR}/motd_senic	${D}${sysconfdir}/motd_senic
	install -m 0755 ${WORKDIR}/senic_button.py ${D}${sbindir}/senic_button.py
}
