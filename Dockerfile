FROM maven:3.3.3-jdk-8

MAINTAINER shelltea@gmail.com

RUN ["ls", "."]
RUN ["ls", "/home"]
RUN ["ls", "/root"]
RUN ["mvn", "clean", "package"]
