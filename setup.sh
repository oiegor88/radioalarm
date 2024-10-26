#!/bin/bash

DEPLOYMENT_DIR="/opt/radioalarm"
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

function build() {
    echo "Building the project..."
    # Update JDK path 
    JAVA_HOME="$SDKMAN_CANDIDATES_DIR/java/$JDK_ALIAS"
	# Build app 
	mvn clean install
}

function writeline() {
  	LINE=$1
  	FILE=$2
  	echo "$1" | sudo tee -a "$2" > /dev/null
}

function deploy() {
    echo "Deploying the project..."
    # Modify permissions
	chmod +x ./start.sh
	chmod +x ./stop.sh
	# Copy stuff to /opt
	sudo mkdir -p "$DEPLOYMENT_DIR"
	sudo cp -rf ./application.yml "$DEPLOYMENT_DIR/application.yml"
	sudo cp -rf ./target/radioalarm.jar "$DEPLOYMENT_DIR/radioalarm.jar"
	
	# Create scripts
	START_SCRIPT="$DEPLOYMENT_DIR/start.sh"
	sudo truncate -s 0 "$START_SCRIPT" 
	writeline "#!/bin/bash" "$START_SCRIPT"
	writeline "$SDKMAN_CANDIDATES_DIR/java/$JDK_ALIAS/bin/java -jar /opt/radioalarm/radioalarm.jar" "$START_SCRIPT"
	
	STOP_SCRIPT="$DEPLOYMENT_DIR/stop.sh"
	sudo truncate -s 0 "$STOP_SCRIPT" 
	writeline "#!/bin/bash" "$STOP_SCRIPT"
	writeline "pkill -f radioalarm.jar*" "$STOP_SCRIPT"
	
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
	writeline "WorkingDirectory=/opt/radioalarm" "$UNITFILE"
	writeline "ExecStart=/bin/bash start.sh" "$UNITFILE"
	writeline "ExecStop=/bin/bash stop.sh" "$UNITFILE"
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
            echo "Usage: $0 [dev] [build] [deploy] [start] [stop] [logs]"
            exit 1
            ;;
    esac
done

if [ $# -eq 0 ]; then
    echo "Usage: $0 [dev] [build] [deploy] [stop] [start] [logs]"
fi
