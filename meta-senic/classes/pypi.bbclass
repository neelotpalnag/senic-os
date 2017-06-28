def pypi_package(d):
    bpn = d.getVar('BPN', True)
    if bpn.startswith('python-'):
        return bpn[7:]
    elif bpn.startswith('python3-'):
        return bpn[8:]
    return bpn

PYPI_PACKAGE ?= "${@pypi_package(d)}"
PYPI_PACKAGE_EXT ?= "tar.gz"

def pypi_src_uri(d):
    """ compute the source URL assuming that it's hosted on a devpi instance"""
    package = d.getVar('PYPI_PACKAGE', True)
    package_ext = d.getVar('PYPI_PACKAGE_EXT', True)
    package_base_url = d.getVar('SNC_DEVPI_BASE_URL', True)
    package_index = d.getVar('SNC_DEVPI_INDEX', True)
    package_checksum = d.getVarFlag('SRC_URI', 'md5sum', True)
    if package_checksum is None:
      package_checksum = d.getVarFlag('SRC_URI', 'sha256sum', True)
    pv = d.getVar('PV', True)
    return '%s%s/+f/%s/%s/%s-%s.%s' % (package_base_url, package_index, package_checksum[:3], package_checksum[3:16], package, pv, package_ext)

PYPI_SRC_URI ?= "${@pypi_src_uri(d)}"

HOMEPAGE ?= "https://pypi.python.org/pypi/${PYPI_PACKAGE}/"
SECTION = "devel/python"
SRC_URI += "${PYPI_SRC_URI}"
S = "${WORKDIR}/${PYPI_PACKAGE}-${PV}"

