# 下载镜像

    docker pull docker-0.unsee.tech/apache/seata-server:2.3.0

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
    name: seata-server  # 在nacos中配置的seata-server的名称

logging:
  config: classpath:logback-spring.xml
  file:
    path: ${user.home}/logs/seata
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
  config:
    # support: nacos, consul, apollo, zk, etcd3
    type: nacos  # 使用nacos作为配置中心
    nacos:
      server-addr: 172.17.0.1:8848  # seata访问nacos ，属于容器与容器的访问
      group: SEATA_GROUP # 指定配置文件在 nacos 中所属的分组
      namespace: seata # 指定配置文件在 nacos 中的命名空间
      username: nacos
      password: nacos
      data-id: seataServer.properties   # 指定配置文件在 nacos 中的名称
  registry:
    # support: nacos, eureka, redis, zk, consul, etcd3, sofa
    type: nacos
    nacos: # 同register
      application: seata-server
      server-addr: 172.17.0.1:8848
      group: SEATA_GROUP
      namespace: seata
      cluster: default
      username: nacos
      password: nacos
  store:
    # support: file 、 db 、 redis
    mode: file
  #  server:
  #    service-port: 8091 #If not configured, the default is '${server.port} + 1000'
  security:
    secretKey: SeataSecretKey0c382ef121d778043159209298fd40bf3850a017
    tokenValidityInMilliseconds: 1800000
    ignore:
      urls: /,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-fe/public/**,/api/v1/auth/login
```

# 在nacos中创建txt配置文件，seataServer.properties,并修改store.db.xx相关的数据库配置

```txt
#For details about configuration items, see https://seata.apache.org/zh-cn/docs/user/configurations
#Transport configuration, for client and server
transport.protocol=seata
transport.type=TCP
transport.server=NIO
transport.heartbeat=true
transport.enableTmClientBatchSendRequest=false
transport.enableRmClientBatchSendRequest=true
transport.enableTcServerBatchSendResponse=false
transport.rpcRmRequestTimeout=30000
transport.rpcTmRequestTimeout=30000
transport.rpcTcRequestTimeout=30000
transport.enableClientSharedEventLoopGroup=false
transport.threadFactory.bossThreadPrefix=NettyBoss
transport.threadFactory.workerThreadPrefix=NettyServerNIOWorker
transport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler
transport.threadFactory.shareBossWorker=false
transport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector
transport.threadFactory.clientSelectorThreadSize=-1
transport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread
transport.threadFactory.bossThreadSize=1
transport.threadFactory.workerThreadSize=default
transport.shutdown.wait=3
transport.serialization=seata
transport.compressor=none

#Transaction routing rules configuration, only for the client
service.vgroupMapping.default_tx_group=default
#If you use a registry, you can ignore it
service.default.grouplist=127.0.0.1:8091
service.disableGlobalTransaction=false

client.metadataMaxAgeMs=30000
#Transaction rule configuration, only for the client
client.rm.asyncCommitBufferLimit=10000
client.rm.lock.retryInterval=10
client.rm.lock.retryTimes=30
client.rm.lock.retryPolicyBranchRollbackOnConflict=true
client.rm.reportRetryCount=5
client.rm.tableMetaCheckEnable=true
client.rm.tableMetaCheckerInterval=60000
client.rm.sqlParserType=druid
client.rm.reportSuccessEnable=false
client.rm.sagaBranchRegisterEnable=false
client.rm.sagaJsonParser=fastjson
client.rm.tccActionInterceptorOrder=-2147482648
client.rm.sqlParserType=druid
client.tm.commitRetryCount=5
client.tm.rollbackRetryCount=5
client.tm.defaultGlobalTransactionTimeout=60000
client.tm.degradeCheck=false
client.tm.degradeCheckAllowTimes=10
client.tm.degradeCheckPeriod=2000
client.tm.interceptorOrder=-2147482648
client.undo.dataValidation=true
client.undo.logSerialization=jackson
client.undo.onlyCareUpdateColumns=true
server.undo.logSaveDays=7
server.undo.logDeletePeriod=86400000
client.undo.logTable=undo_log
client.undo.compress.enable=true
client.undo.compress.type=zip
client.undo.compress.threshold=64k
#For TCC transaction mode
tcc.fence.logTableName=tcc_fence_log
tcc.fence.cleanPeriod=1h
# You can choose from the following options: fastjson, jackson, gson
tcc.contextJsonParserType=fastjson

#Log rule configuration, for client and server
log.exceptionRate=100

#Transaction storage configuration, only for the server. The file, db, and redis configuration values are optional.
store.mode=file
store.lock.mode=file
store.session.mode=file
#Used for password encryption
store.publicKey=

#If `store.mode,store.lock.mode,store.session.mode` are not equal to `file`, you can remove the configuration block.
store.file.dir=file_store/data
store.file.maxBranchSessionSize=16384
store.file.maxGlobalSessionSize=512
store.file.fileWriteBufferCacheSize=16384
store.file.flushDiskMode=async
store.file.sessionReloadReadSize=100

#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.
store.db.datasource=druid
store.db.dbType=mysql
store.db.driverClassName=com.mysql.jdbc.Driver
# 修改为自己的数据库配置
store.db.url=jdbc:mysql://mysql-3124:3306/seata?useUnicode=true&rewriteBatchedStatements=true
store.db.user=xc_dev
store.db.password=E7F@oFw2Ks

store.db.minConn=5
store.db.maxConn=30
store.db.globalTable=global_table
store.db.branchTable=branch_table
store.db.distributedLockTable=distributed_lock
store.db.vgroupTable=vgroup-table
store.db.queryLimit=100
store.db.lockTable=lock_table
store.db.maxWait=5000

store.db.druid.timeBetweenEvictionRunsMillis=120000
store.db.druid.minEvictableIdleTimeMillis=300000
store.db.druid.testWhileIdle=true
store.db.druid.testOnBorrow=false
store.db.druid.keepAlive=false

store.db.hikari.idleTimeout=600000
store.db.hikari.keepaliveTime=120000
store.db.hikari.maxLifetime=1800000
store.db.hikari.validationTimeout=5000

store.db.dbcp.timeBetweenEvictionRunsMillis=120000
store.db.dbcp.minEvictableIdleTimeMillis=300000
store.db.dbcp.testWhileIdle=true
store.db.dbcp.testOnBorrow=false

#These configurations are required if the `store mode` is `redis`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `redis`, you can remove the configuration block.
store.redis.mode=single
store.redis.type=pipeline
store.redis.single.host=127.0.0.1
store.redis.single.port=6379
store.redis.sentinel.masterName=
store.redis.sentinel.sentinelHosts=
store.redis.sentinel.sentinelPassword=
store.redis.maxConn=10
store.redis.minConn=1
store.redis.maxTotal=100
store.redis.database=0
store.redis.password=
store.redis.queryLimit=100

#Transaction rule configuration, only for the server
server.recovery.committingRetryPeriod=1000
server.recovery.asynCommittingRetryPeriod=1000
server.recovery.rollbackingRetryPeriod=1000
server.recovery.endstatusRetryPeriod=1000
server.recovery.timeoutRetryPeriod=1000
server.maxCommitRetryTimeout=-1
server.maxRollbackRetryTimeout=-1
server.rollbackFailedUnlockEnable=false
server.distributedLockExpireTime=10000
server.session.branchAsyncQueueSize=5000
server.session.enableBranchAsyncRemove=false
server.enableParallelRequestHandle=true
server.enableParallelHandleBranch=false
server.applicationDataLimit=64000
server.applicationDataLimitCheck=false

server.raft.server-addr=127.0.0.1:7091,127.0.0.1:7092,127.0.0.1:7093
server.raft.snapshotInterval=600
server.raft.applyBatch=32
server.raft.maxAppendBufferSize=262144
server.raft.maxReplicatorInflightMsgs=256
server.raft.disruptorBufferSize=16384
server.raft.electionTimeoutMs=2000
server.raft.reporterEnabled=false
server.raft.reporterInitialDelay=60
server.raft.serialization=jackson
server.raft.compressor=none
server.raft.sync=true

server.ratelimit.enable=false
server.ratelimit.bucketTokenNumPerSecond = 999999
server.ratelimit.bucketTokenMaxNum = 999999
server.ratelimit.bucketTokenInitialNum = 999999

#Metrics configuration, only for the server
metrics.enabled=true
metrics.registryType=compact
metrics.exporterList=prometheus
metrics.exporterPrometheusPort=9898
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
    image: docker-0.unsee.tech/apache/seata-server:2.3.0
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
    volumes:
      - /mnt/seata/config/resources:/seata-server/resources


```