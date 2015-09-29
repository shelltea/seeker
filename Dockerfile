FROM maven:3.3.3-jdk-8

MAINTAINER shelltea@gmail.com
WORKDIR /
RUN ["mvn", "clean", "package"]
