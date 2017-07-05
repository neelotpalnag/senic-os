SUMMARY = "Install the senic hub"
DESCRIPTION = "\
Creates a virtualenv and install the required python eggs"
DEPENDS_${PN} = "\
  python3-pip \
  python3 \
  python3-netifaces \
  python3-multiprocessing \
  python3-cffi \
  python3-click \
  python3-cryptography \
  python3-cffi \
  python3-pydoc \
  python3-dbus \
  python3-pygobject \
  python3-idna \
  python3-requests \
  python3-six \
  python3-urllib3 \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
#SRC_URI = "file://supervisor_senic_hub.conf"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.txt;md5=62d64e0a0688cba2e9ede69d1f702e1c"

inherit senic-base
inherit logging


do_install() {
    # create and populate the deployment location
    install -m 0755 -o ${SNC_BUILD_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DEPLOY_LOCATION}
    install -m 0755 -o ${SNC_RUNTIME_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DATA_LOCATION}
    install -m 0755 -o ${SNC_RUNTIME_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DATA_LOCATION}/logs
}

FILES_${PN} = "\
    ${SNC_BACKEND_DATA_LOCATION}/logs \
    ${SNC_BACKEND_DEPLOY_LOCATION} \
"

