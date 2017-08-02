inherit senic-base

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://ntp.conf"

python do_compile() {
  render_template('ntp.conf')
}

do_install_append() {
    install -m 644 ${WORKDIR}/ntp.conf ${D}${sysconfdir}
}
