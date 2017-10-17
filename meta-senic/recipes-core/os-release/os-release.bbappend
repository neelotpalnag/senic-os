python () {
	# setting nostamp on do_install function makes it rebuild every time
	# if this is not set, git sha won't be updated unless os-release recipe
	# is changed.
    d.setVarFlag('do_install', 'nostamp', '1')
}


# include git sha of senic-os repo and update build date on every build
do_install_append() {
	build_date=$(date)
	date_str='BUILD_DATE="'$build_date'"'
	echo $date_str >> ${D}${sysconfdir}/os-release

	for layer_path in ${BBLAYERS};do
		cd $layer_path
		layer_git_ver=$(git describe --tags --always)
		layer_name=$(basename ${layer_path})
		layer_str=$layer_name=$layer_git_ver
		echo $layer_str >> ${D}${sysconfdir}/os-release
	done
}
