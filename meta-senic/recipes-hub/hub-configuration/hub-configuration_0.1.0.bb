SUMMARY = "Configure the senic hub"
DESCRIPTION = "\
Creates various configuration files"
DEPENDS = "python-supervisor"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://senic_hub.conf"
SRC_URI += "file://production.ini"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.txt;md5=62d64e0a0688cba2e9ede69d1f702e1c"

inherit senic-base
inherit logging

python do_compile() {
  render_template('senic_hub.conf')
  render_template('production.ini')
}


do_install() {
    # create and populate the deployment location
    install -m 0755 -d ${D}${SNC_BACKEND_DEPLOY_LOCATION}
    install -m 0755 -d ${D}${SNC_BACKEND_DATA_LOCATION}
    install -m 0755 -d ${D}${SNC_BACKEND_DATA_LOCATION}/logs
    install -m 0755 ${WORKDIR}/production.ini ${D}${SNC_BACKEND_DEPLOY_LOCATION}/production.ini
    install -m 0755 -d ${D}${SNC_HASS_DATA_LOCATION}
    # configure supervisor processes
    install -m 0755 -d ${D}${sysconfdir}/supervisor/conf.d/
    install -m 0755 ${WORKDIR}/senic_hub.conf ${D}${sysconfdir}/supervisor/conf.d/senic_hub.conf
}

FILES_${PN} = "\
    ${SNC_BACKEND_DATA_LOCATION}/logs \
    ${SNC_BACKEND_DEPLOY_LOCATION} \
    ${SNC_HASS_DATA_LOCATION} \
    ${sysconfdir}/supervisor/conf.d/senic_hub.conf \
"
