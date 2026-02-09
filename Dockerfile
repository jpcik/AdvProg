# For finding latest versions of the base image see
# https://github.com/SwissDataScienceCenter/renkulab-docker
ARG RENKU_BASE_IMAGE=renku/renkulab-py:3.11-6080194

#FROM ${RENKU_BASE_IMAGE} as coursier_base

FROM ${RENKU_BASE_IMAGE} as builder


# Uncomment and adapt if code is to be included in the image
# COPY src /code/src

# Uncomment and adapt if your R or python packages require extra linux (ubuntu) software
# e.g. the following installs apt-utils and vim; each pkg on its own line, all lines
# except for the last end with backslash '\' to continue the RUN line
#
# USER root
# RUN apt-get update && \
#    apt-get install -y --no-install-recommends \
#    apt-utils \
#    vim
# USER ${NB_USER}

# Uncomment and adapt if you want to automatically install
# python dependencies when the Docker image builds (pip or conda)
# Note: you will need to add a (pip) requirements.txt file
#       AND a (conda) environment.yml file for the below code to run,
#       but you can remove one or the other.
#
# COPY requirements.txt environment.yml /tmp/
# RUN conda env update -q -f /tmp/environment.yml && \
#    /opt/conda/bin/pip install -r /tmp/requirements.txt && \
#    conda clean -y --all && \
#    conda env export -n "root"



# Dockerfile with support for creating images with kernels for multiple Scala versions.
# Expects ALMOND_VERSION and SCALA_VERSIONS to be set as build arg, like this:
# docker build --build-arg ALMOND_VERSION=0.3.1 --build-arg SCALA_VERSIONS="2.12.9 2.13.0" .

# Set LOCAL_IVY=yes to have the contents of ivy-local copied into the image.
# Can be used to create an image with a locally built almond that isn't on maven central yet.
ARG LOCAL_IVY=no

#FROM jupyter/base-notebook as coursier_base

USER root

RUN apt-get -y update && \
    apt-get install --no-install-recommends -y \
      curl \
      openjdk-21-jdk \
      ca-certificates-java && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN curl -Lo /usr/local/bin/coursier https://github.com/coursier/coursier/releases/download/v2.1.25-M23/coursier && \
    chmod +x /usr/local/bin/coursier

COPY scripts/install-kernels.sh .
RUN chmod +x ./install-kernels.sh

COPY scripts/install-kernels.sh .
RUN chmod +x ./install-kernels.sh

USER $NB_UID


RUN ls .

# ensure the JAR of the CLI is in the coursier cache, in the image
RUN /usr/local/bin/coursier --help

FROM builder as local_ivy_yes
USER $NB_UID
ONBUILD RUN mkdir -p .ivy2/local/
ONBUILD COPY --chown=1000:100 ivy-local/ .ivy2/local/

FROM builder as local_ivy_no

FROM local_ivy_no
ARG ALMOND_VERSION="0.14.2"
# Set to a single Scala version string or list of Scala versions separated by a space.
# i.e SCALA_VERSIONS="2.12.8"
ARG SCALA_VERSIONS="3.3.7"
USER $NB_UID


RUN ./install-kernels.sh && \
    rm install-kernels.sh && \
    rm -rf .ivy2
    
COPY --chown=1000:100 notebooks/ $HOME


RUN pip install RISE

RUN pip install jupyterlab_rise

########################################################
#        Renku install section - do not edit           #


# RENKU_VERSION determines the version of the renku CLI
# that will be used in this image. To find the latest version,
# visit https://pypi.org/project/renku/#history.
ARG RENKU_VERSION=2.9.4

# Install renku from pypi or from github if a dev version
RUN if [ -n "$RENKU_VERSION" ] ; then \
        source .renku/venv/bin/activate ; \
        currentversion=$(renku --version) ; \
        if [ "$RENKU_VERSION" != "$currentversion" ] ; then \
            pip uninstall renku -y ; \
            gitversion=$(echo "$RENKU_VERSION" | sed -n "s/^[[:digit:]]\+\.[[:digit:]]\+\.[[:digit:]]\+\(rc[[:digit:]]\+\)*\(\.dev[[:digit:]]\+\)*\(+g\([a-f0-9]\+\)\)*\(+dirty\)*$/\4/p") ; \
            if [ -n "$gitversion" ] ; then \
                pip install --no-cache-dir --force "git+https://github.com/SwissDataScienceCenter/renku-python.git@$gitversion" ;\
            else \
                pip install --no-cache-dir --force renku==${RENKU_VERSION} ;\
            fi \
        fi \
    fi
#             End Renku install section                #
########################################################

#FROM ${RENKU_BASE_IMAGE}

RUN rm -rf ${HOME}/.renku/venv

COPY --from=builder ${HOME}/.renku/venv ${HOME}/.renku/venv
