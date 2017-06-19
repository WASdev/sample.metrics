# Download latest Liberty from DockerHub
FROM websphere-liberty:latest

# Create a Liberty server instance called "server1"
RUN /opt/ibm/wlp/bin/server create server1

# Copy over the customized server.xml to the Liberty server
COPY server.xml /opt/ibm/wlp/usr/servers/server1/

# Copy the MetricsExample WAR application to the Liberty server
COPY MetricsExample.war /opt/ibm/wlp/usr/servers/server1/dropins

# Set Path Shortcuts, redirect all Liberty logs to "/logs"
ENV LOG_DIR=/logs

# Create the key.jks keystore file using securityUtility with CommonName as Liberty
RUN /opt/ibm/wlp/bin/securityUtility createSSLCertificate --password=Liberty --subject=CN=Liberty --server=server1

# Start the Liberty server
CMD ["/opt/ibm/wlp/bin/server", "run", "server1"]
