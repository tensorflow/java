FROM centos:7

ENV GLIBC "glibc glibc-common glibc-devel glibc-headers"
RUN yum --disablerepo updates -y install $GLIBC
RUN yum -x "$GLIBC" -y update && yum -x "$GLIBC" -y install centos-release-scl-rh && yum -x "$GLIBC" -y install  \
  scl-utils \
  epel-release \
  java-1.8.0-openjdk-devel \
  devtoolset-7 \
  unzip \
  which \
  rh-git218 \
  patch \
  perl-Data-Dumper \
  python36-devel && yum -x "$GLIBC" -y install \
  python36-numpy \
  python36-pip \
  python36-six

RUN echo "Downloading Maven" && \
    curl -L https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -o $HOME/apache-maven-3.6.3-bin.tar.gz && \
    tar xzf $HOME/apache-maven-3.6.3-bin.tar.gz -C /opt/ && \
    ln -sf /opt/apache-maven-3.6.3/bin/mvn /usr/bin/mvn

RUN echo "Downloading Bazel" && \
    curl -L https://github.com/bazelbuild/bazel/releases/download/3.7.2/bazel-3.7.2-installer-linux-x86_64.sh -o bazel.sh --retry 10 && \
    bash bazel.sh

ARG GPU=false

ADD install_cuda.sh /install_cuda.sh
RUN chmod +x /install_cuda.sh
RUN /install_cuda.sh

ENV JAVA_HOME /usr/lib/jvm/java-1.8.0

ADD scl_enable.sh /scl_enable.sh

ADD entrypoint.sh /entrypoint
ENTRYPOINT [ "/entrypoint" ]

ENV CI=true

VOLUME /root/.m2
VOLUME /root/.cache/bazel

ENV BASH_ENV=/scl_enable.sh \
    ENV=/scl_enable.sh \
    PROMPT_COMMAND=". /scl_enable.sh"
