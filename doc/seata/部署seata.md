# 下载镜像

    docker pull docker-0.unsee.tech/seataio/seata-server:2.0.0

# 启动一个镜像，拷贝出配置

    docker run -d -p 8091:8091 -p 7091:7091  --name seata-server
    # 复制文件来处
    docker cp seata-server:/seata-server/resources /docker-app/seata/config
    # 停止&删除容器
    docker stop seata-server && docker rm seata-server

# 修改宿主机的配置文件application.yml

    cd /docker-app/seata/config/resources
    vi application.yml

```yml
server:
  port: 7091

spring:
  application:
    name: seata-server

logging:
  config: classpath:logback-spring.xml
  file:
    path: ${log.home:${user.home}/logs/seata}
  extend:
    logstash-appender:
      destination: 127.0.0.1:4560
    kafka-appender:
      bootstrap-servers: 127.0.0.1:9092
      topic: logback_to_logstash

console:
  user:
    username: seata
    password: seata
seata:
  #配置中心
  config:
    type: nacos
    #nacos配置中心配置信息
    nacos:
      server-addr: nacos:8848
      namespace: dev
      username: nacos
      password: zwcb3axb
      group: DEFAULT_GROUP
      context-path:
      #此项可以不加
      file-extension: yaml
      #data-id: seata.properties
      #指向nacos配置中心的seata服务的配置文件（seata服务的配置文件）
      data-id: seata.yaml
  #注册中心
  registry:
    type: nacos
    #nacos注册中心配置信息
    nacos:
      application: seata-server
      server-addr: nacos:8848
      namespace: dev
      username: nacos
      password: zwcb3axb
      group: DEFAULT_GROUP
      #事务分组需要对应内容
      cluster: default
      context-path:
  store:
    mode: db
    lock:
      mode: db
    session:
      mode: db
  #数据库连接
  db:
    datasource: druid
    db-type: mysql
    #mysql5.x：driverClassName: com.mysql.jdbc.Driver
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.5.111:3124/seata?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true
    user: xc_dev
    password: E7F@oFw2Ks
    min-conn: 10
    max-conn: 100
    global-table: global_table
    branch-table: branch_table
    lock-table: lock_table
    distributed-lock-table: distributed_lock
    query-limit: 1000
    max-wait: 5000
  #  server:
  #    service-port: 8091 #If not configured, the default is '${server.port} + 1000'
  security:
    secretKey: SeataSecretKey0c382ef121d778043159209298fd40bf3850a017
    tokenValidityInMilliseconds: 1800000
    ignore:
      urls: /,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.jpeg,/**/*.ico,/api/v1/auth/login,/metadata/v1/**

```

# 启动docker-compose

```yaml
version: "3.1"
networks:
  my_network:
    external: true
    name: xc-network

services:
  seata-server:
    image: docker-0.unsee.tech/seataio/seata-server:2.0.0
    container_name: seata-server
    restart: always
    privileged: true
    networks:
      - my_network
    ports:
      - 8091:8091
      - 7091:7091
    environment:
      - SEATA_IP=192.168.5.111
      - STORE_MODE=db
      - SEATA_PORT=8091
    volumes:
      - /mnt/seata/application.yml:/seata-server/resources/application.yml
      - /usr/share/zoneinfo/Asia/Shanghai:/etc/localtime        #设置系统时区
      - /usr/share/zoneinfo/Asia/Shanghai:/etc/timezone  #设置时区

```