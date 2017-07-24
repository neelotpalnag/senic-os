SUMMARY = "Configure the senic hub"
DESCRIPTION = "\
Creates various configuration files"
DEPENDS_${PN} = "\
  hub-installation \
  bluez5 \
"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://supervisor_senic_hub.conf"
SRC_URI += "file://supervisor_bluenet.conf"
SRC_URI += "file://supervisor_netwatch.conf"
SRC_URI += "file://supervisor_nuimo_app.conf"
SRC_URI += "file://supervisor_device_discovery.conf"
SRC_URI += "file://production.ini"
SRC_URI += "file://locales.sh"
SRC_URI += "file://LICENSE.txt"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.txt;md5=62d64e0a0688cba2e9ede69d1f702e1c"

inherit senic-base
inherit logging

do_configure[deptask] = "do_install"

python do_compile() {
  render_template('supervisor_senic_hub.conf')
  render_template('supervisor_bluenet.conf')
  render_template('supervisor_netwatch.conf')
  render_template('supervisor_nuimo_app.conf')
  render_template('supervisor_device_discovery.conf')
  render_template('production.ini')
  render_template('locales.sh')
}

do_install() {
    # create and populate the deployment location
    install -m 0755 -o ${SNC_BUILD_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DEPLOY_LOCATION}
    install -m 0755 -o ${SNC_RUNTIME_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DATA_LOCATION}
    install -m 0755 -o ${SNC_RUNTIME_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DATA_LOCATION}/logs
    install -m 0755 -o ${SNC_BUILD_USER} -g ${SNC_RUNTIME_USER} ${WORKDIR}/production.ini ${D}${SNC_BACKEND_DATA_LOCATION}/production.ini
    install -m 0755 -d ${D}${SNC_HASS_DATA_LOCATION}

    # configure supervisor processes
    install -m 0755 -d ${D}${sysconfdir}/supervisor/conf.d/
    install -m 0755 ${WORKDIR}/supervisor_senic_hub.conf ${D}${sysconfdir}/supervisor/conf.d/senic_hub.conf
    install -m 0755 ${WORKDIR}/supervisor_bluenet.conf ${D}${sysconfdir}/supervisor/conf.d/bluenet.conf
    install -m 0755 ${WORKDIR}/supervisor_netwatch.conf ${D}${sysconfdir}/supervisor/conf.d/netwatch.conf
    install -m 0755 ${WORKDIR}/supervisor_nuimo_app.conf ${D}${sysconfdir}/supervisor/conf.d/nuimo_app.conf
    install -m 0755 ${WORKDIR}/supervisor_device_discovery.conf ${D}${sysconfdir}/supervisor/conf.d/device_discovery.conf

    # global system configuration
    install -m 0755 -d ${D}${sysconfdir}/profile.d/
    install -m 644 ${WORKDIR}/locales.sh ${D}${sysconfdir}/profile.d/locales.sh

}

FILES_${PN} = "\
    ${SNC_BACKEND_DATA_LOCATION}/logs \
    ${SNC_BACKEND_DEPLOY_LOCATION} \
    ${SNC_HASS_DATA_LOCATION} \
    ${sysconfdir}/supervisor/conf.d/senic_hub.conf \
    ${sysconfdir}/supervisor/conf.d/bluenet.conf \
    ${sysconfdir}/supervisor/conf.d/netwatch.conf \
    ${sysconfdir}/supervisor/conf.d/nuimo_app.conf \
    ${sysconfdir}/supervisor/conf.d/device_discovery.conf \
    ${sysconfdir}/profile.d/locales.sh \
"
