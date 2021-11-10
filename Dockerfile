FROM tomcat:9.0.54-jdk17-openjdk
ADD target/api.war ${CATALINA_HOME}/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]