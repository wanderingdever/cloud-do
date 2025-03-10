# 启动前准备

启动前需安装好Mysql8.X、Redis、Nacos、Seata分布式事务

# 打包

## docker

> 修改pom.xml中的profile打包配置，然后执行docker打包命令。

    mvn -DsendCredentialsOverHttp=true -Dmaven.test.skip=true clean install -T 12 -P${profileId}