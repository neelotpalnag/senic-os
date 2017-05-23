FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://update_motd.sh"

# Tests needed only on debug release
SRC_URI += "file://led_test.sh"
SRC_URI += "file://esd_test.sh"

do_install_append() {

	# Install initscripts into /etc/init.d
	install -m 0755 ${WORKDIR}/update_motd.sh	${D}${sysconfdir}/init.d/senic_update_motd
	
	# Add initscripts to runlevel 5 (default)
	update-rc.d -r ${D} senic_update_motd start 98 5 .

	# Tests needed only on debug release
	install -m 0755 ${WORKDIR}/led_test.sh	${D}${sysconfdir}/init.d/senic_led_test
	install -m 0755 ${WORKDIR}/esd_test.sh	${D}${sysconfdir}/init.d/senic_esd_test

	update-rc.d -r ${D} senic_esd_test start 99 5 .
}
