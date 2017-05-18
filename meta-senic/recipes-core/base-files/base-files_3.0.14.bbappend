FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://motd_senic"

do_install_append() {

	# The extra files need to go in the respective directories
	install -m 0755 ${WORKDIR}/motd_senic	${D}${sysconfdir}/motd_senic
}