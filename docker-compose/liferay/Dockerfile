FROM liferay/dxp:2024.q2.4

COPY --chown=liferay:liferay /docker-compose/liferay/data/license/*.xml/ /opt/liferay/deploy/
COPY --chown=liferay:liferay /configs/local/osgi/configs/*.config /opt/liferay/osgi/configs/
