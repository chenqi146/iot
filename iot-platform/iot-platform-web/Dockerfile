FROM openjdk:8-alpine
MAINTAINER cq
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN /bin/sh -c 'touch /app.jar'
ENV LANG C.UTF-8
ENV TZ=Asia/Shanghai
EXPOSE 58080
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=58080", "/app.jar"]
