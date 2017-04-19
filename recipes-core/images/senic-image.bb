require senic-image-minimal.bb

IMAGE_INSTALL += " \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "bluez5-noinst-tools", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "linux-firmware-mediatek", "",d)} \
	htop \
	python \
	python3 \
	packagegroup-core-ssh-openssh \
	openssl \
	usbutils \
"
