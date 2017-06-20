SUMMARY = "Configure the senic hub"
DESCRIPTION = "\
Creates various configuration files"
DEPENDS = "python-supervisor"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://senic_hub.conf"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.txt;md5=62d64e0a0688cba2e9ede69d1f702e1c"

inherit senic-base


python do_compile() {
  write_template(d.expand('${WORKDIR}/senic_hub.conf'), datastore(d))
}


do_install() {
	install -m 0755 -d ${D}${sysconfdir}/supervisor/conf.d/
	install -m 0755 ${WORKDIR}/senic_hub.conf ${D}${sysconfdir}/supervisor/conf.d/senic_hub.conf
}
