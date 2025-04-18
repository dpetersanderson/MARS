FROM debian:11-slim

ENV DEBIAN_FRONTEND=noninteractive
ENV PATH="$PATH:/usr/lib/jvm/jdk-11.0.12/bin"

# Install Python3
RUN apt update
RUN apt install -y python3

# Install Java 11.0.12 by Oracle
COPY jdk-11.0.12_linux-x64_bin.deb /tmp
RUN apt install -y /tmp/jdk-11.0.12_linux-x64_bin.deb

# Verify Java installation
RUN java --version

COPY . /app

# Set the working directory inside the container
WORKDIR /app

# Run the build script to package the project
RUN python3 build.py

# Run tests by default
CMD ["python3", "test.py"]
