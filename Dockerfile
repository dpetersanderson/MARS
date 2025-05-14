FROM debian:11-slim

ENV DEBIAN_FRONTEND=noninteractive
ENV PATH="$PATH:/usr/lib/jvm/jdk-1.8.0_441-oracle-x64/bin"
#ENV PATH="$PATH:/usr/java/jdk1.6.0_45/bin"

# Install Python3
RUN apt update
RUN apt install -y python3 alien

# Install Java 8u441 by Oracle
COPY jdk-8u441-linux-x64.rpm /tmp
RUN alien -i /tmp/jdk-8u441-linux-x64.rpm
#COPY jdk-6u45-linux-amd64.rpm /tmp
#RUN alien -i /tmp/jdk-6u45-linux-amd64.rpm

COPY . /app

# Set the working directory inside the container
WORKDIR /app

# Run the build script to package the project and test it
CMD ["bash", "-c", "python3 build.py && cd /mnt && python3 /app/test.py"]
