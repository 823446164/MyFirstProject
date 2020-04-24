#指定基础镜像，在其上进行定制
FROM java:8

#维护者信息
MAINTAINER gc

VOLUME /var/app
VOLUME /var/log

#定义一个参数
ARG JAR_FILE

#添加asrc.jar 到容器里
ADD ${JAR_FILE} /var/app/app.jar

#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
EXPOSE 5555

#指定容器启动程序及参数  <ENTRYPOINT> "<CMD>"
ENTRYPOINT ["java","-jar","/var/app/app.jar"]
