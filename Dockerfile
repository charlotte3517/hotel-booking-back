FROM openjdk:21-slim

RUN mkdir -p /hotel-booking

WORKDIR /hotel-booking

RUN apt-get update && apt-get install -y iputils-ping telnet curl unzip libaio1

RUN mkdir -p /opt/oracle \
    && curl -o /tmp/instantclient-basic-linux.x64-21.5.0.0.0dbru.zip https://download.oracle.com/otn_software/linux/instantclient/215000/instantclient-basic-linux.x64-21.5.0.0.0dbru.zip \
    && curl -o /tmp/instantclient-sqlplus-linux.x64-21.5.0.0.0dbru.zip https://download.oracle.com/otn_software/linux/instantclient/215000/instantclient-sqlplus-linux.x64-21.5.0.0.0dbru.zip \
    && unzip /tmp/instantclient-basic-linux.x64-21.5.0.0.0dbru.zip -d /opt/oracle \
    && unzip /tmp/instantclient-sqlplus-linux.x64-21.5.0.0.0dbru.zip -d /opt/oracle \
    && rm /tmp/instantclient-basic-linux.x64-21.5.0.0.0dbru.zip /tmp/instantclient-sqlplus-linux.x64-21.5.0.0.0dbru.zip \
    && ln -s /opt/oracle/instantclient_21_5/sqlplus /usr/bin/sqlplus

ENV LD_LIBRARY_PATH /opt/oracle/instantclient_21_5
ENV PATH /opt/oracle/instantclient_21_5:$PATH

ARG JAR_FILE=/app/hotel-booking.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENV TZ=Asia/Taipei

CMD sleep 3; java $JAVA_OPTS -jar app.jar
