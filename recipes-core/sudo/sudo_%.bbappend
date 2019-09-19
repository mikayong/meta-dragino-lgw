FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://dragino \
"

do_install_append() {
    install -d ${D}/etc/sudoers.d
    install -m 0644 ${WORKDIR}/dragino ${D}/etc/sudoers.d/dragino
}
