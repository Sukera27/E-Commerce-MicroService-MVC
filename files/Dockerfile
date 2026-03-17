
FROM prom/prometheus:v2.46.0


COPY ../files/prometheus.yml /etc/prometheus/prometheus.yml


EXPOSE 9090


CMD [ "--config.file=/etc/prometheus/prometheus.yml" ]