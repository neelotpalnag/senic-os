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
    package = d.getVar('PYPI_PACKAGE', True)
    package_ext = d.getVar('PYPI_PACKAGE_EXT', True)
    package_base_url = d.getVar('SNC_PACKAGE_BASE_URL', True)
    pv = d.getVar('PV', True)
    return '%s/%s/%s-%s.%s' % (package_base_url, package, package, pv, package_ext)

PYPI_SRC_URI ?= "${@pypi_src_uri(d)}"

HOMEPAGE ?= "https://pypi.python.org/pypi/${PYPI_PACKAGE}/"
SECTION = "devel/python"
SRC_URI += "${PYPI_SRC_URI}"
S = "${WORKDIR}/${PYPI_PACKAGE}-${PV}"

