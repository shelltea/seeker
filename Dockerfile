FROM maven:3.3.3-jdk-8

MAINTAINER shelltea@gmail.com
WORKDIR /
RUN ["pwd"]
RUN ["ls"]
RUN ["mvn", "clean", "package"]
