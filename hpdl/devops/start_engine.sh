#!/bin/sh
# Start Engine Script

# Enviroment variables
export HPDL_BASE_DIR="/home/roberto/Projects/Tuts/java-backend-tutorial/hpdl"
export HPDL_CONFIG_FILE="${HPDL_BASE_DIR}/config/app.properties"

#echo ${HPDL_BASE_DIR}
#echo ${HPDL_CONFIG_FILE}

echo "Starting Engine from script"
java -classpath ../lib/engine.jar backend.app.Engine 

exit 0
