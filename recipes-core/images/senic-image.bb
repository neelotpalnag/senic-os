require senic-image-minimal.bb

IMAGE_INSTALL += " \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "bluez5-noinst-tools", "", d)} \
"
