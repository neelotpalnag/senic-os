FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://senic_leds.sh"
SRC_URI += "file://update_motd.sh"

do_install_append() {

	# Install initscripts into /etc/init.d
	install -m 0755 ${WORKDIR}/senic_leds.sh	${D}${sysconfdir}/init.d/senic_leds.sh
	install -m 0755 ${WORKDIR}/update_motd.sh	${D}${sysconfdir}/init.d/update_motd.sh

	# Add initscripts to runlevel 5 (default)
	update-rc.d -r ${D} senic_leds.sh start 99 5 .
	update-rc.d -r ${D} update_motd.sh start 99 5 .
}
