FROM selenium/standalone-chrome:latest

RUN sudo apt-get update && \
    sudo apt-get install -y openjdk-8-jdk git

ENV JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64/

RUN sudo apt-get update