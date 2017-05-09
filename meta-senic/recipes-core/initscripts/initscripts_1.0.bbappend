FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://senic_leds.sh"

do_install_append() {

	# The extra files need to go in the respective directories
	install -m 0755 ${WORKDIR}/senic_leds.sh	${D}${sysconfdir}/init.d/senic_leds.sh

	# Create the symbolic links
	update-rc.d -r ${D} senic_leds.sh start 99 5 .
}
