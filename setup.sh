#!/bin/bash

DEPLOYMENT_DIR="/opt/radioalarm"
APP_NAME="radioalarm"
CONFIG_FILE="application.yml"
START_SCRIPT_FILE="start.sh"
STOP_SCRIPT_FILE="stop.sh"
SSL_DIR="ssl"
SERVICE_NAME="radioalarm.service"
JDK_ALIAS="22.0.2-zulu"
MVN_ALIAS="3.9.9"

CURRENT_USER="$USER"
CURRENT_UID="$UID"

function dev() {
    echo "Running environment setup..."
    # Install SDKMAN
    curl -s "https://get.sdkman.io" | bash
    source "$HOME/.sdkman/bin/sdkman-init.sh"
    sdk version
    # Install stuff
    sdk install java "$JDK_ALIAS"
    sdk install maven "$MVN_ALIAS"
}

function ssl() {
  KEYSTORE_PASS="changeit"

  mkdir -p "$SSL_DIR"
  keytool -genkeypair \
    -alias radioalarm \
    -keyalg RSA \
    -keysize 2048 \
    -storetype PKCS12 \
    -storepass "$KEYSTORE_PASS" \
    -keystore "$SSL_DIR/keystore.p12" \
    -dname "CN=admin, OU=, O=, L=, S=, C=" \
    -validity 365
}


function build() {
    echo "Building the project..."
    # Update JDK path 
    JAVA_HOME="$SDKMAN_CANDIDATES_DIR/java/$JDK_ALIAS"
    # Build app 
    mvn clean install -Dbuild.finalName="$APP_NAME"
}

function writeline() {
    LINE=$1
    FILE=$2
    echo "$1" | sudo tee -a "$2" > /dev/null
}

function deploy() {
    echo "Deploying the project..."

    # Copy stuff to /opt
    sudo mkdir -p "$DEPLOYMENT_DIR"
    sudo cp -rf "./$CONFIG_FILE" "$DEPLOYMENT_DIR/$CONFIG_FILE"
    sudo cp -rf "./$SSL_DIR" "$DEPLOYMENT_DIR/$SSL_DIR"
    sudo cp -rf "./target/$APP_NAME.jar" "$DEPLOYMENT_DIR/$APP_NAME.jar"
    
    # Create scripts
    START_SCRIPT="$DEPLOYMENT_DIR/$START_SCRIPT_FILE"
    sudo truncate -s 0 "$START_SCRIPT" 
    writeline "#!/bin/bash" "$START_SCRIPT"
    writeline "$SDKMAN_CANDIDATES_DIR/java/$JDK_ALIAS/bin/java -jar $DEPLOYMENT_DIR/$APP_NAME.jar" "$START_SCRIPT"
    
    STOP_SCRIPT="$DEPLOYMENT_DIR/$STOP_SCRIPT_FILE"
    sudo truncate -s 0 "$STOP_SCRIPT" 
    writeline "#!/bin/bash" "$STOP_SCRIPT"
    writeline "pkill -f $APP_NAME.jar*" "$STOP_SCRIPT"
    
    # Update service
    UNITFILE="/etc/systemd/system/$SERVICE_NAME"
    sudo truncate -s 0 "$UNITFILE" 
    writeline "[Unit]" "$UNITFILE"
    writeline "Description=Radio Alarm Service" "$UNITFILE"
    writeline "After=network.target" "$UNITFILE"
    writeline "StartLimitIntervalSec=0" "$UNITFILE"
    writeline "" "$UNITFILE"
    writeline "[Service]" "$UNITFILE"
    writeline "Type=simple" "$UNITFILE"
    writeline "Restart=no" "$UNITFILE"
    writeline "RestartSec=1" "$UNITFILE"
    writeline "User=$CURRENT_USER" "$UNITFILE"
    writeline "Environment=PULSE_RUNTIME_PATH=/run/user/$CURRENT_UID/pulse/" "$UNITFILE"
    writeline "WorkingDirectory=$DEPLOYMENT_DIR" "$UNITFILE"
    writeline "ExecStart=/bin/bash $START_SCRIPT_FILE" "$UNITFILE"
    writeline "ExecStop=/bin/bash $STOP_SCRIPT_FILE" "$UNITFILE"
    writeline "" "$UNITFILE"
    writeline "[Install]" "$UNITFILE"
    writeline "WantedBy=multi-user.target" "$UNITFILE"
    writeline "" "$UNITFILE"
    
    sudo systemctl daemon-reload
    sudo systemctl enable "$SERVICE_NAME"
}

function start {
    sudo systemctl start "$SERVICE_NAME"
}

function stop {
    sudo systemctl stop "$SERVICE_NAME"
}

function logs {
    sudo journalctl -u "$SERVICE_NAME" | tail -20
}


for arg in "$@"; do
    case $arg in
        dev)
            dev
            ;;
        ssl)
            ssl
            ;;
        build)
            build
            ;;
        deploy)
            deploy
            ;;
        logs)
            logs
            ;;
        start)
            start
            ;;
        stop)
            stop
            ;;            
        *)
            echo "Unknown option: $arg"
            echo "Usage: $0 [dev] [ssl] [build] [deploy] [start] [stop] [logs]"
            exit 1
            ;;
    esac
done

if [ $# -eq 0 ]; then
    echo "Usage: $0 [dev] [ssl] [build] [deploy] [stop] [start] [logs]"
fi
