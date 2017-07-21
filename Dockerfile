# Download latest Liberty from DockerHub
FROM websphere-liberty:latest

# Create a Liberty server instance called "server1"
RUN /opt/ibm/wlp/bin/server create server1

# Copy over the customized server.xml to the Liberty server
COPY target/liberty/wlp/usr/servers/defaultServer/server.xml /opt/ibm/wlp/usr/servers/server1/

# Copy over the customized server.xml to the Liberty server
COPY target/liberty/wlp/usr/servers/defaultServer/bootstrap.properties /opt/ibm/wlp/usr/servers/server1/

# Copy the sample.servlet.metrics WAR application to the Liberty server
COPY target/MetricsSample.war /opt/ibm/wlp/usr/servers/server1/apps/

# Set Path Shortcuts, redirect all Liberty logs to "/logs"
ENV LOG_DIR=/logs

# Create the key.jks keystore file using securityUtility with CommonName and password set to Liberty for the Liberty server.
RUN /opt/ibm/wlp/bin/securityUtility createSSLCertificate --password=Liberty --subject=CN=Liberty --server=server1

# Start the Liberty server
CMD ["/opt/ibm/wlp/bin/server", "run", "server1"]
