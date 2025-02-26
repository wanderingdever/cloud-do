｜[简体中文](README-cn.md)

# Project Introduction

> This project is a scaffolding project of the SpringCloudAlibaba microservice framework. The running environment is
> JDK21. The dependencies used include SpringBoot3, SpringCloudAlibaba, Sa-Token, Mysql, Mybatis Plus, Redis, Dubbo,
> Nacos, Knife4j, Minio, Alibaba Cloud OSS, Tencent COS, etc.

# Project Structure

# Structure Overview

```text
 ┌cloud-do
 |——api                     # Microservice remote API module, depending on Dubbo
 |————api-auth-center       # Remote service of authentication center
 |————api-server-system     # Remote service of system management
 |  
 |——auth-center             # Authentication center, public authorization and authentication. The user system is under this module.
 |  
 |——common # Common dependencies    
 |————common-auth           # Authentication module. If the authorization function needs to be used, this module needs to be introduced.
 |————common-core           # Common basic module, containing basic core functions. This module depends on the common-util module and is depended on by other modules.
 |————common-redis          # Redis module. This module depends on the common-core module. If the Redis function needs to be used, this module needs to be introduced.
 |————common-datasource     # Data source module. This module depends on the common-core module. If the data source function needs to be used, this module needs to be introduced. Mybatis Plus is used.
 |————common-util           # Utility class module, which is depended on by the common-core module by default.
 |————common-web            # Web module. This module depends on the common-core module. If the web function needs to be used, this module needs to be introduced.
 |————common-dubbo          # Dubbo module, used for remote calls of microservices. This module is depended on by the API module. All services that need remote calls need to depend on this module.
 |  
 |——gateway                 # Gateway
 | 
 |——server                  # Server side
 |————server-common         # Common service, providing common services such as file upload and email sending.
 |————server-system         # System management service, providing a back-end management system, such as user management, role management, menu management, department management, dictionary management, parameter management, etc.
 └
```

## Detailed Structure Description

### api

This module is a dependency module that provides remote interfaces. For example, if service module A needs to provide an
interface for service B to call, a corresponding service module should be created under this module, such as
api-server-A. Create the interface TestRemoteService in the module, implement the remote TestRemoteService interface in
service A, and then service B can call the interface.

### auth-center

This module is the authentication center, which is responsible for public authorization and authentication. The user
system is under this module, but this module does not provide specific permission management. It only provides user
authentication functions, such as user login, user logout, and user information acquisition. And it provides some
interfaces externally for other services to process user management-related data.

### common

This module is a common basic module, and basically each module can be used independently. For example, if only the web
service is needed, only the common-web module can be introduced. If a data source is needed, the common-datasource
module can be introduced. If Redis is needed, the common-redis module can be introduced.

### gateway

This module is the gateway, used to handle requests, such as current limiting, authentication, load balancing, etc.

### server

This module is the server side, providing specific services. Newly created services are under this module, such as
system management services and common services (file upload, email sending, etc.).