SUMMARY = "A daemon for handling the factory-reset button"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

RDEPENDS_${PN} += "\
  python \
  bash \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://senic_hub_button_daemon"
SRC_URI += "file://senic_hub_factory_reset"
SRC_URI += "file://senic-hub-button.service"

inherit systemd

SYSTEMD_SERVICE_${PN} = "senic-hub-button.service"

do_install_append() {
  install -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/senic_hub_button_daemon ${D}${bindir}/senic_hub_button_daemon
  install -m 0755 ${WORKDIR}/senic_hub_factory_reset ${D}${bindir}/senic_hub_factory_reset

  install -d ${D}${systemd_system_unitdir}
  install -m 0644 ${WORKDIR}/senic-hub-button.service ${D}${systemd_system_unitdir}
}

FILES_${PN} = "\
  ${bindir} \
  ${bindir}/senic_hub_button_daemon \
  ${bindir}/senic_hub_factory_reset \
  ${systemd_system_unitdir} \
"
