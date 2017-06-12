SUMMARY = "Configure the senic hub"
DESCRIPTION = "\
Creates various configuration files"
DEPENDS = "python-supervisor"
SRC_URI += "file://senic_hub.conf \
"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSES.txt;md5=62d64e0a0688cba2e9ede69d1f702e1c"

python do_compile() {
  print(d.getVar('SNC_BACKEND_DEPLOY_LOCATION', True))
  with open(d.expand('${B}/senic_hub.conf'), 'w') as outfile:
    outfile.write('hello')
}

do_install() {
  install -d ${D}${sysconfdir}/supervisor/conf.d
  install ${B}/senic_hub.conf ${D}${sysconfdir}/supervisor/conf.d/senic_hub.conf
}
