# 项目介绍

> 本项目为SpringCloudAlibaba微服务框架的脚手架项目，运行环境为JDK21，使用依赖有SpringBoot3、SpringCloudAlibaba、Sa-Token、Mysql、Mybatis
> Plus、Redis、Dubbo、Nacos、Knife4j、Minio、阿里云OSS、腾讯与COS等。

# 项目结构

# 结构总览

```text

 ┌cloud-do
 |——api                     # 微服务远程api模块，依赖了dubbo
 |————api-auth-center       # 认证中心远程服务
 |————api-server-system     # 系统管理远程服务
 |  
 |——auth-center             # 认证中心，公共授权认证，用户体系在该模块下
 |  
 |——common # 公共依赖    
 |————common-auth           # 认证模块，需要使用到授权功能则需要引入改模块
 |————common-core           # 公共基础模块，包含了基础核心功能，该模块依赖了common-util模块，被其他模块依赖
 |————common-redis          # Redis模块，该模块依赖了common-core模块，需要使用redis功能则需要引入该模块
 |————common-datasource     # 数据源模块，该模块依赖了common-core模块，需要使用数据源功能则需要引入该模块,使用的是mybatis plus
 |————common-util           # 工具类模块，默认被common-core模块依赖
 |————common-web            # web模块，该模块依赖了common-core模块，需要使用web功能则需要引入该模块
 |————common-dubbo          # dubbo模块，微服务远程调用使用，该模块被api模块依赖，所有需要远程调用的服务都需要依赖该模块
 |  
 |——gateway                 # 网关
 | 
 |——server                  # 服务端
 |————server-common         # 公共服务，提供如文件上传、邮件发送等公共服务
 |————server-system         # 系统管理服务，提供后台管理系统，如用户管理、角色管理、菜单管理、部门管理、字典管理、参数管理等功能
 └
```

## 结构详细说明

### api

该模块为提供远程接口的依赖模块，如A服务模块需要提接口供B服务调用，则在该模块下创建对应的服务模块，如：api-server-A，在模块中创建接口TestRemoteService，在服务A中实现增加远程的TestRemoteService接口，在服务B中调用接口即可。

### auth-center

该模块为认证中心，公共授权认证，用户体系在该模块下，但该模块不提供具体的权限管理，只提供用户认证功能，如用户登录、用户注销、用户信息获取等。且对外提供一部分接口，供其他服务处理用户管理相关的数据。

### common

该模块为公共基础模块，基本上每个模块都可以单独使用。如只需要web服务则可以只引入common-web模块，需要数据源则可以引入common-datasource模块，需要redis则可以引入common-redis模块。

### gateway

该模块为网关，用于处理请求，如限流、鉴权、负载均衡等。

### server

该模块为服务端，提供具体的服务，新建的服务在该模块下。如系统管理服务、公共服务（文件上传、邮件发送）等。