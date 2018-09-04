FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD back-center.jar app.jar

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]