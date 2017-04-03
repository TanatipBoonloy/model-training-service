FROM anapsix/alpine-java:latest
MAINTAINER Tanatip <tanatip.jo@gmail.com>
VOLUME /tmp
ADD build/libs/spring-boot-application.jar app.jar
ENV APP_JAR_DIR build/libs
ENV APP_JAR spring-boot-application.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "bash", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]