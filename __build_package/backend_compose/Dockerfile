# usage: docker build . -t <tag> service/Dockerfile

FROM openjdk:17-jdk

WORKDIR /opt/aiot

COPY ./target/cmir-mgt-*-SNAPSHOT.jar /opt/aiot/cmir-mgt.jar
COPY ./__build_package/backend_compose/logback.xml /opt/aiot/logback.xml
COPY ./__build_package/backend_compose/start.sh /opt/aiot/start.sh

ENV JAVA_ARGS "-Xms512m -Xmx512m"
ENV JVM_PROPS "-Dlogging.config=file:///opt/aiot/logback.xml \
               -Djava.util.prefs.systemRoot=/opt/aiot/.java \
               -Djava.util.prefs.userRoot=/opt/aiot/.java/.userPrefs \
               -XX:ErrorFile=/var/log/jvm/aiot_hs_err_%p.log \
               -XX:+HeapDumpOnOutOfMemoryError \
               -XX:HeapDumpPath=/var/log/jvm/aiot_heapdump_%p.hprof"

RUN chmod +x /opt/aiot/start.sh && \
    echo "veil: true" > /opt/aiot/application-veil.yaml && \
    echo "alias ll=\"ls -alF --color\"" ~/.bashrc

CMD ["/bin/bash", "/opt/aiot/start.sh"]
