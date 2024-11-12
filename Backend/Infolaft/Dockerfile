FROM tomcat:10.1.0-jdk17

# Configura el directorio de despliegue en Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/Infolaft-1.war /usr/local/tomcat/webapps/ROOT.war

# Expone el puerto necesario
EXPOSE 8700

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]
