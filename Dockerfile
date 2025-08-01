FROM rust:1.88-bullseye

ENV DEBIAN_FRONTEND=noninteractive
ENV PATH="$PATH:/usr/lib/jvm/jdk-1.8.0_441-oracle-x64/bin"
#ENV PATH="$PATH:/usr/java/jdk1.6.0_45/bin"

# Install Python3
RUN apt update
RUN apt install -y python3 alien mingw-w64

# Install Java 8u441 by Oracle
COPY jdk-8u441-linux-x64.rpm /tmp
RUN alien -i /tmp/jdk-8u441-linux-x64.rpm
#COPY jdk-6u45-linux-amd64.rpm /tmp
#RUN alien -i /tmp/jdk-6u45-linux-amd64.rpm

# Rust cross compilation
RUN curl -L https://github.com/roblabla/MacOSX-SDKs/releases/download/13.3/MacOSX13.3.sdk.tar.xz | tar xJ
ENV SDKROOT="$PWD/MacOSX13.3.sdk/"
ENV PATH="$PATH:~/.rustup/toolchains/stable-x86_64-unknown-linux-gnu/lib/rustlib/x86_64-unknown-linux-gnu/bin/"
ENV CARGO_TARGET_X86_64_APPLE_DARWIN_LINKER="rust-lld"

RUN rustup target add x86_64-pc-windows-gnu
RUN rustup target add x86_64-unknown-linux-gnu
RUN rustup target add x86_64-apple-darwin

COPY . /app

# Set the working directory inside the container
WORKDIR /app

# Run the build script to package the project and test it
CMD ["bash", "-c", "python3 build.py && cd /mnt && python3 /app/test.py && python3 /app/wrap.py"]
