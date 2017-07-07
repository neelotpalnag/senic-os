FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://update_motd.sh"
SRC_URI += "file://networkmanager"
SRC_URI += "file://fs_expander"

# Tests needed only on debug release
SRC_URI += "file://esd_test.sh"

do_install_append() {

	# Install initscripts into /etc/init.d
	install -m 0755 ${WORKDIR}/fs_expander  ${D}${sysconfdir}/init.d/fs_expander
	install -m 0755 ${WORKDIR}/update_motd.sh	${D}${sysconfdir}/init.d/senic_update_motd
	install -m 0755 ${WORKDIR}/networkmanager	${D}${sysconfdir}/init.d/networkmanager

	# Add initscripts to runlevel 5 (default)
	update-rc.d -r ${D} senic_update_motd start 98 5 .
	update-rc.d -r ${D} networkmanager start 91 5 .

	# Add filesystem expander script to runlevel S after root fs is mounted rw
	update-rc.d -r ${D} fs_expander start 99 S .

	# Tests needed only on debug release	
	# install -m 0755 ${WORKDIR}/esd_test.sh	${D}${sysconfdir}/init.d/senic_esd_test
	# update-rc.d -r ${D} senic_esd_test start 99 5 .
}
