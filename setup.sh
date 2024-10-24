#!/bin/bash

# Install SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version

# Install stuff
sdk install java 23.0.1-zulu
sdk install maven 3.9.9

export JAVA_HOME=${SDKMAN_CANDIDATES_DIR}/java/${CURRENT}

# Build app 
mvn clean install

# Modify permissions
chmod +x ./start.sh
chmod +x ./stop.sh

# Copy stuff to /opt
sudo mkdir /opt/radioalarm
sudo cp -rf ./start.sh /opt/radioalarm/start.sh
sudo cp -rf ./stop.sh /opt/radioalarm/stop.sh
sudo cp -rf ./target/radioalarm.jar /opt/radioalarm/radioalarm.jar
sudo cp -rf ./application.yml /opt/radioalarm/application.yml

# Setup service
sudo cp -rf ./radioalarm.service /etc/systemd/system/radioalarm.service
sudo systemctl stop radioalarm
sudo systemctl start radioalarm
sudo systemctl enable radioalarm

