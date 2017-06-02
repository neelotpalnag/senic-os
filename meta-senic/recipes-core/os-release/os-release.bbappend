python () {
	# setting nostamp on do_install function makes it rebuild every time
	# if this is not set, git sha won't be updated unless os-release recipe
	# is changed.
    d.setVarFlag('do_install', 'nostamp', '1')
}

# include git sha of senic-os repo and update build date on every build
do_install_append() {
	meta_senic_sha=$(git log -1 --format="%H")
	build_date=$(date)
	sha_str='META_SENIC_SHA="'$meta_senic_sha'"'
	date_str='BUILD_DATE="'$build_date'"'

	echo $sha_str >> ${D}${sysconfdir}/os-release
	echo $date_str >> ${D}${sysconfdir}/os-release
}
