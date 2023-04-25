FROM adoptopenjdk/maven-openjdk8 AS build
# 复制项目文件到容器中
COPY . /app
# 设置工作目录
WORKDIR /app
# 构建项目
RUN mvn clean package  --settings settings.xml -DskipTests=true

FROM openjdk:8u102-jre
# 复制生成的 jar 文件到容器中
COPY --from=0 /app/target/*.jar /app/app.jar
# 设置工作目录
WORKDIR /app
# 暴露端口
EXPOSE 9001
# 设置时区为 Asia/Shanghai
ENV TZ=Asia/Shanghai
# 设置容器的时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 定义启动命令
ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=prod app.jar "]