LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit core-image

IMAGE_BOOT_FILES = "zImage zImage-${KERNEL_DEVICETREE} boot.scr"

IMAGE_INSTALL = " \
  ${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "bluez5-noinst-tools", "", d)} \
  ${@bb.utils.contains("DISTRO_FEATURES", "wifi", "linux-firmware-mediatek", "",d)} \	
  packagegroup-core-boot \
  packagegroup-base \	
  packagegroup-core-ssh-openssh \
  openssl \
  lighttpd \
  os-release \
  glibc-locale \
  glibc-utils \ 
  networkmanager \
  ntp \
  python \
  python3 \
  python3-repoze-lru \
  python-supervisor \
  python3-asn1crypto \
  python3-translationstring \
  python3-netifaces \
  python3-multiprocessing \
  python3-chardet \
  python3-colander \
  python3-cffi \
  python3-click \
  python3-idna \
  python3-pyyaml \
  python3-requests \
  python3-six \
  python3-urllib3 \
  python3-cffi \
  python3-cryptography \
  python3-webob \
  python3-websocket-client \
  python3-xmltodict \
  python3-zeroconf \
  python3-zope.deprecation \
  python3-zope.interface \
  python3-dbus \
  python3-pygobject \
  python3-contextlib2 \
  python3-cornice \
  python3-cryptoyaml \
  python3-gatt \
  python3-hupper \
  python3-iso8601 \
  python3-lightify \
  python3-netdisco \
  python3-nuimo \
  python3-phue \
  python3-plaster \
  python3-pytz \
  python3-raven \
  python3-simplejson \
  python3-soco \
  python3-transaction \
  python3-waitress \
  python3-venusian \
  python3-paste\
  python3-pastedeploy \
  python3-pyramid \
  python3-pyramid-tm \
  python3-senic-hub \
  hub-configuration \
"

# Custom kernel module to handle Button interrupts
# IMAGE_INSTALL_append = " senic-button"

