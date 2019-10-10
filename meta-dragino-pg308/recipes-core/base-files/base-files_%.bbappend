FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://motd.sh \
                  file://path.sh \
                  file://lastlog \
"

do_install_append() {
    install -d ${D}/etc/profile.d
    install -m 755 ${WORKDIR}/motd.sh ${D}/etc/profile.d/motd.sh
    install -m 755 ${WORKDIR}/path.sh ${D}/etc/profile.d/path.sh

    install -d ${D}/var/log
    install -m 755 ${WORKDIR}/lastlog ${D}/var/log/lastlog
}
