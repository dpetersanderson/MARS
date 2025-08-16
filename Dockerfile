FROM rust:1.88-bullseye

ENV DEBIAN_FRONTEND=noninteractive

# Install Python3 (for the build scripts), alien (for the Oracle JDK 8), mingw (for Windows cross compilation)
# and OpenJDK 11 (the version used in the judge)
RUN apt update
RUN apt install -y python3 alien mingw-w64 openjdk-11-jdk

# Install Java 8u441 by Oracle
COPY jdk-8u441-linux-x64.rpm /tmp
RUN alien -i /tmp/jdk-8u441-linux-x64.rpm
ENV PATH="$PATH:/usr/lib/jvm/jdk-1.8.0_441-oracle-x64/bin"

# Rust cross compilation extra setup for MacOSX: download and unpack the MacOSX SDK
RUN curl -L https://github.com/roblabla/MacOSX-SDKs/releases/download/13.3/MacOSX13.3.sdk.tar.xz | tar xJ
ENV SDKROOT="$PWD/MacOSX13.3.sdk/"
ENV PATH="$PATH:~/.rustup/toolchains/stable-x86_64-unknown-linux-gnu/lib/rustlib/x86_64-unknown-linux-gnu/bin/"
ENV CARGO_TARGET_X86_64_APPLE_DARWIN_LINKER="rust-lld"

# Install the cross compilation targets
RUN rustup target add x86_64-pc-windows-gnu
RUN rustup target add x86_64-unknown-linux-gnu
RUN rustup target add x86_64-apple-darwin

# Run the build script to package the project and test it
COPY . /app
WORKDIR /app
CMD ["bash", "-c", "python3 build.py && python3 /app/wrap.py && cd /mnt && python3 /app/test.py"]
