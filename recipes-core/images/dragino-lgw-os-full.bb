DESCRIPTION = "Image including the LoRa packet-forwarder and all LoRa Server components installed."

require recipes-core/images/dragino-lgw-os-base.bb

IMAGE_INSTALL += " \
	bash \
    postgresql \
    postgresql-client \
    postgresql-contrib \
	redis \
	mosquitto \
	mosquitto-clients \
	firstbootinit \
	loraserver \
	lora-app-server \
"
