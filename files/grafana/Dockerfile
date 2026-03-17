
FROM grafana/grafana-oss:10.0.3


COPY ../files/grafana /var/lib/grafana


EXPOSE 3000

ENV GF_SECURITY_ADMIN_USER=admin
ENV GF_SECURITY_ADMIN_PASSWORD=admin


CMD [ "/run.sh" ]