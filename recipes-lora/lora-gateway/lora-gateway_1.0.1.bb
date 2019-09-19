DESCRIPTION = "LoRa Packet Forwarder"
HOMEPAGE = "https://github.com/dragino/pi_gateway_fwd"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5cdd0e28c2e40b8586dc01aa5d226c01"

PR = "r1"
SRCREV = "dragino-1.0.1"

SRC_URI = "git://github.com/dragino/pi_gateway_fwd.git;;protocol=git \
           file://lora-gateway.init \
           file://lora-gateway.monit \
           file://lora-gateway.default \
"

inherit update-rc.d

INITSCRIPT_NAME = "lora-gateway"
INITSCRIPT_PARAMS = "defaults"

S = "${WORKDIR}/git"
LORA_DIR = "/opt/lora-gateway"
LORA_CONF_DIR = "${sysconfdir}/lora-gateway"

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${LORA_DIR}
    install -d ${D}${LORA_DIR}/utils

    install -m 0755 lora_pkt_fwd/lora_pkt_fwd ${D}${LORA_DIR}/
    install -m 0755 ${S}/lora_pkt_fwd/update_gwid.sh ${D}${LORA_DIR}/

    install -m 0755 libloragw/test_loragw_reg ${D}${LORA_DIR}/utils/
    install -m 0755 libloragw/test_loragw_gps ${D}${LORA_DIR}/utils/
    install -m 0755 util_sink/util_sink ${D}${LORA_DIR}/utils/
    install -m 0755 util_ack/util_ack ${D}${LORA_DIR}/utils/
    install -m 0755 util_tx_test/util_tx_test ${D}${LORA_DIR}/utils/
    install -m 0755 util_tx_continuous/util_tx_continuous ${D}${LORA_DIR}/utils/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/lora-gateway.init ${D}${sysconfdir}/init.d/lora-gateway

    install -d ${D}${sysconfdir}/monit.d
    install -m 0644 ${WORKDIR}/lora-gateway.monit ${D}${sysconfdir}/monit.d/lora-gateway

    install -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/lora-gateway.default ${D}${sysconfdir}/default/lora-gateway

    install -d ${D}${LORA_CONF_DIR}
    install -d ${D}${LORA_CONF_DIR}/dragino
    install -d ${D}${LORA_CONF_DIR}/ic880a
    install -d ${D}${LORA_CONF_DIR}/ic980a
    install -d ${D}${LORA_CONF_DIR}/loraga_port
    install -d ${D}${LORA_CONF_DIR}/rak831
    install -d ${D}${LORA_CONF_DIR}/rhf0m301
    install -d ${D}${LORA_CONF_DIR}/pislora

    install -m 0644 ${S}/lora_pkt_fwd/*.json ${D}${LORA_CONF_DIR}

    install -m 0644 ${S}/lora_pkt_fwd/dragino/*.json ${D}${LORA_CONF_DIR}/dragino
    install -m 0644 ${s}/lora_pkt_fwd/ic880a/*.json ${D}${LORA_CONF_DIR}/ic880a
    install -m 0644 ${s}/lora_pkt_fwd/ic980a/*.json ${D}${LORA_CONF_DIR}/ic980a
    install -m 0644 ${s}/lora_pkt_fwd/lorago_port/*.json ${D}${LORA_CONF_DIR}/lorago_port
    install -m 0644 ${s}/lora_pkt_fwd/rak831/*.json ${D}${LORA_CONF_DIR}/rak831
    install -m 0644 ${s}/lora_pkt_fwd/rhf0m301/*.json ${D}${LORA_CONF_DIR}/rhf0m301
    install -m 0644 ${s}/lora_pkt_fwd/pislora/*.json ${D}${LORA_CONF_DIR}/pislora
}

FILES_${PN} += "${LORA_DIR}"
FILES_${PN}-dbg += "${LORA_DIR}/.debug ${LORA_DIR}/utils/.debug"

INSANE_SKIP_${PN} = "ldflags"
