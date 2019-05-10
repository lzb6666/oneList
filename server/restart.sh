#!/usr/bin/env bash
SPRING_BOOT_JAR_NAME='onelist-0.0.1-SNAPSHOT.jar'
PIDS=`ps -ef |awk -v name=${SPRING_BOOT_JAR_NAME} '{if(($0~/name/)&&($0!~/awk/))print $2}'`
if [ -z "$PIDS" ]; then
  echo "首次启动项目$SPRING_BOOT_JAR_NAME"
fi
for PID in $PIDS ; do
  kill $PID
done
nohup java -jar /home/jenkins/projects/$SPRING_BOOT_JAR_NAME > log 2>&1
echo "启动完毕"
cat log