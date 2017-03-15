# https://github.com/elastic/kibana-docker
FROM docker.elastic.co/kibana/kibana:5.2.1

# Add your kibana plugins setup here
# Example: RUN kibana-plugin install <name|url>

COPY entrypoint.sh /tmp/entrypoint.sh
RUN chmod +x /tmp/entrypoint.sh

ADD kibana.yml /opt/kibana/config/

CMD ["/tmp/entrypoint.sh"]
