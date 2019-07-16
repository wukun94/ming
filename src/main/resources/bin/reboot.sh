#!/bin/bash

pid=`ps -ef|grep \ ming-server-dev.jar |grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
  kill -9 $pid
fi

mv -f ming-server-2.0.0.RELEASE.jar ming-server-dev.jar

nohup java -server -Xms256m -Xmx256m -jar -Djava.security.egd=file:/dev/./urandom risk-server-dev.jar --spring.profiles.active=test --server.port=9527 &
echo "=========================="

