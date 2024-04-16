FROM openjdk:21-oracle
MAINTAINER Gardenia

USER root

WORKDIR /opt

# 在主机 /var/lib/docker 目录下创建一个临时文件，并链接到容器的 /tmp
VOLUME ["/tmp"]

ARG Jar_path=gardenia-main-service/target/*.jar
# 将 jar 包添加到容器中，并命名为 springboot_docker.jar
ADD ${Jar_path} /opt/springboot_docker.jar

# Params
ARG MYSQL_DB_HOST=106.14.45.61,MYSQL_DB_PORT=30169

# Meta Data
LABEL MYSQL_DB_HOST=106.14.45.61 MYSQL_DB_PORT=30169

# 运行jar包
RUN bash -c 'touch /opt/springboot_docker.jar'
ENTRYPOINT ["java", "-jar", "/opt/springboot_docker.jar", "--MYSQL_DB_HOST=106.14.45.61", "--MYSQL_DB_PORT=30169"]

# SpringBoot 项目配置的端口号为 6001，需要将 6001 暴露出去
EXPOSE 9001
