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
      #      file-extension: yaml
      data-id: seata.properties
      #指向nacos配置中心的seata服务的配置文件（seata服务的配置文件）
  #      data-id: seata.yaml
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
  #  store:
  #    mode: db
  #    lock:
  #      mode: db
  #    session:
  #      mode: db
  #  #数据库连接
  #  db:
  #    datasource: druid
  #    db-type: mysql
  #    #mysql5.x：driverClassName: com.mysql.jdbc.Driver
  #    driver-class-name: com.mysql.cj.jdbc.Driver
  #    url: jdbc:mysql://192.168.5.111:3124/seata?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true
  #    user: xc_dev
  #    password: E7F@oFw2Ks
  #    min-conn: 10
  #    max-conn: 100
  #    global-table: global_table
  #    branch-table: branch_table
  #    lock-table: lock_table
  #    distributed-lock-table: distributed_lock
  #    query-limit: 1000
  #    max-wait: 5000
  #  server:
  #    service-port: 8091 #If not configured, the default is '${server.port} + 1000'
  security:
    secretKey: SeataSecretKey0c382ef121d778043159209298fd40bf3850a017
    tokenValidityInMilliseconds: 1800000
    ignore:
      urls: /,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.jpeg,/**/*.ico,/api/v1/auth/login,/metadata/v1/**

```

# 在nacos创建配置文件seataServer.properties

```text
store.mode=db
#-----db-----
store.db.datasource=druid
store.db.dbType=mysql
# 需要根据mysql的版本调整driverClassName
# mysql8及以上版本对应的driver：com.mysql.cj.jdbc.Driver
# mysql8以下版本的driver：com.mysql.jdbc.Driver
store.db.driverClassName=com.mysql.cj.jdbc.Driver
store.db.url=jdbc:mysql://192.168.5.111:3124/seata?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true
store.db.user= xc_dev
store.db.password=E7F@oFw2Ks
# 数据库初始连接数
store.db.minConn=1
# 数据库最大连接数
store.db.maxConn=20
# 获取连接时最大等待时间 默认5000，单位毫秒
store.db.maxWait=5000
# 全局事务表名 默认global_table
store.db.globalTable=global_table
# 分支事务表名 默认branch_table
store.db.branchTable=branch_table
# 全局锁表名 默认lock_table
store.db.lockTable=lock_table
# 查询全局事务一次的最大条数 默认100
store.db.queryLimit=100


# undo保留天数 默认7天,log_status=1（附录3）和未正常清理的undo
server.undo.logSaveDays=7
# undo清理线程间隔时间 默认86400000，单位毫秒
server.undo.logDeletePeriod=86400000
# 二阶段提交重试超时时长 单位ms,s,m,h,d,对应毫秒,秒,分,小时,天,默认毫秒。默认值-1表示无限重试
# 公式: timeout>=now-globalTransactionBeginTime,true表示超时则不再重试
# 注: 达到超时时间后将不会做任何重试,有数据不一致风险,除非业务自行可校准数据,否者慎用
server.maxCommitRetryTimeout=-1
# 二阶段回滚重试超时时长
server.maxRollbackRetryTimeout=-1
# 二阶段提交未完成状态全局事务重试提交线程间隔时间 默认1000，单位毫秒
server.recovery.committingRetryPeriod=1000
# 二阶段异步提交状态重试提交线程间隔时间 默认1000，单位毫秒
server.recovery.asynCommittingRetryPeriod=1000
# 二阶段回滚状态重试回滚线程间隔时间  默认1000，单位毫秒
server.recovery.rollbackingRetryPeriod=1000
# 超时状态检测重试线程间隔时间 默认1000，单位毫秒，检测出超时将全局事务置入回滚会话管理器
server.recovery.timeoutRetryPeriod=1000
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