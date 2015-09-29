FROM maven:3.3.3-jdk-8

MAINTAINER shelltea@gmail.com

COPY . /usr/seeker
WORKDIR /usr/seeker

RUN ["mvn", "clean", "package"]
