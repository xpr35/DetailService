FROM tomcat:latest

COPY target/DetailService-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/detailservice.war
