#!/bin/bash

echo "请确保已执行mvn clean install -DskipTests=true"
echo "将在5s后开始构建docker镜像"
sleep 5
v=$1
version=${v:-"latest"}
echo "版本:${version}"
echo "开始构建docker镜像"
docker build -t dailycost:${version} . -f Dockerfile_local
echo "开始发布docker镜像"
docker tag dailycost:${version} registry.cn-hangzhou.aliyuncs.com/zxp_learn/dailycost:${version}
docker push registry.cn-hangzhou.aliyuncs.com/zxp_learn/dailycost:${version}
echo "操作成功，请到服务器运行以下命令启动"
echo "docker rm -f dailycost"
echo "docker run -itd -p 9001:9001 --restart=always --name=dc --privileged=true -v /root/db/dc_prod.db:/opt/dailycost.db registry.cn-hangzhou.aliyuncs.com/zxp_learn/dailycost:${version}"