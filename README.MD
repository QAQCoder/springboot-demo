## SpringBoot社区

##资料
[Spring文档导航](https://spring.io/guides)

[Spring文档-Web](https://spring.io/guides/gs/serving-web-content/)

[Bootstrap](https://v3.bootcss.com/getting-started/)    

[github授权登录](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

####仿造网站
https://elasticsearch.cn/

##工具
git

##数据库相关

####集成Mybatis
[SpringBoot集成mybatis](www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

本项目中不需要配置MyBatis连接数据库的配置，只需要在application.properties中声明【驱动、用户名密码】即可，Spring会自动创建好数据库连接池，并提供出DataBase，供其他ORM框架使用。前提：需要导入如下依赖

其中Spring内置的数据库连接池为HikariCP，据说是一个高性能的数据库连接池
```xml
<!--得到spring默认的数据库连接池支持-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

[Spring内置的数据库连接池](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-configure-datasource)

####建表语句
```sql
create table table_user {

}
```

####加入Flyway
```bash
//执行Flyway
mvn flyway:migrate
```

####加入Mybatis Generator
```bash
//执行MGB
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```