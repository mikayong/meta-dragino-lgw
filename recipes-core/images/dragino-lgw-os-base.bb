DESCRIPTION = "Image including the LoRa packet-forwarder and LoRa Gateway Bridge component installed."

require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += " \
    packagegroup-base \
    ca-certificates \
    sudo \
    iptables \
    ntp \
    monit \
    wireguard-client-config \
    lora-gateway \
    lora-gateway-bridge \
    rpio \
    rpi-gpio \
    connman \
    connman-client \
    bluez5 \
    gateway-config \
"

inherit extrausers

DISTRO_FEATURES += "wifi"

EXTRA_USERS_PARAMS = "useradd -P dragino dragino;"
