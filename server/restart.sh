#!/bin/bash
SPRING_BOOT_JAR_NAME='onelist-0.0.1-SNAPSHOT.jar'
PIDS=`ps -ef | grep "$SPRING_BOOT_JAR_NAME" | grep java |awk '{print $2}'`
if [ -z "$PIDS" ]; then
  echo "首次启动项目$SPRING_BOOT_JAR_NAME"
fi
for PID in $PIDS ; do
  kill $PID
done
java -jar /home/jenkins/projects/${SPRING_BOOT_JAR_NAME}