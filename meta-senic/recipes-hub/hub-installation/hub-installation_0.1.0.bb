SUMMARY = "Install the senic hub"
DESCRIPTION = "\
Creates a virtualenv and install the required python eggs"
DEPENDS_${PN} = "\
  python-supervisor \
  python3 \
  python3-asn1crypto \
  python3-cffi \
  python3-chardet \
  python3-click \
  python3-colander \
  python3-contextlib2 \
  python3-cornice \
  python3-cryptography \
  python3-cryptoyaml \
  python3-dbus \
  python3-enum-compat \
  python3-gatt \
  python3-hupper \
  python3-idna \
  python3-idna \
  python3-iso8601 \
  python3-lightify \
  python3-multiprocessing \
  python3-netdisco \
  python3-netifaces \
  python3-networkmanager \
  python3-nuimo \
  python3-pastedeploy \
  python3-phue \
  python3-pip \
  python3-plaster \
  python3-pygobject \
  python3-pygobject \
  python3-pyramid \
  python3-pyramid-tm \
  python3-pytz \
  python3-pyyaml \
  python3-raven \
  python3-repoze-lru \
  python3-requests \
  python3-requests \
  python3-senic-hub \
  python3-simplejson \
  python3-six \
  python3-soco \
  python3-transaction \
  python3-translationstring \
  python3-urllib3 \
  python3-venusian \
  python3-waitress \
  python3-webob \
  python3-websocket-client \
  python3-xmltodict \
  python3-zeroconf \
  python3-zope.deprecation \
  python3-zope.interface \
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

