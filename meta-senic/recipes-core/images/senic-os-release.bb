require senic-os-minimal.bb

IMAGE_INSTALL += " \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "bluez5-noinst-tools", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "linux-firmware-mediatek", "",d)} \	
	python \
	python3 \
	python3-pip \
	packagegroup-core-ssh-openssh \
	openssl \	
"
