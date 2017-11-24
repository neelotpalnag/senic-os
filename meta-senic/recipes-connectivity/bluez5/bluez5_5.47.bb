require bluez5.inc

REQUIRED_DISTRO_FEATURES = "bluez5"

SRC_URI[md5sum] = "783e15f65e70cdb8f721c659e140dd56"
SRC_URI[sha256sum] = "cf75bf7cd5d564f21cc4a2bd01d5c39ce425397335fd47d9bbe43af0a58342c8"

# noinst programs in Makefile.tools that are conditional on READLINE
# support
NOINST_TOOLS_READLINE ?= " \
    tools/gatt-service \
    tools/obex-client-tool \
    tools/obex-server-tool \
    tools/bluetooth-player \
    tools/obexctl \
    tools/btmgmt \
"

PACKAGECONFIG += "experimental"
# noinst programs in Makefile.tools that are conditional on EXPERIMENTAL
# support
NOINST_TOOLS_EXPERIMENTAL ?= " \
    tools/bdaddr \
    tools/avinfo \
    tools/avtest \
    tools/scotest \
    tools/amptest \
    tools/hwdb \
    tools/hcieventmask \
    tools/hcisecfilter \
    tools/btinfo \
    tools/btattach \
    tools/btsnoop \
    tools/btproxy \
    tools/btiotest \
    tools/mcaptest \
    tools/cltest \
    tools/oobtest \
    tools/seq2bseq \
    tools/ibeacon \
    profiles/iap/iapd \
    tools/btgatt-client \
    tools/btgatt-server \
    tools/gatt-service \
"

inherit senic-base
SRC_URI += "file://bluetooth.conf \
	    file://bluetooth.service \
	"

python do_render_templates() {
  render_template('bluetooth.conf')
}
addtask render_templates after do_compile before do_install

do_install_append() {
    install -m 644 ${WORKDIR}/bluetooth.conf ${D}${sysconfdir}/dbus-1/system.d/bluetooth.conf

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
       install -d ${D}${systemd_unitdir}/system
       install -m 0644 ${WORKDIR}/bluetooth.service ${D}${systemd_unitdir}/system
    fi
}

do_render_templates[nostamp] = "1"
