基础API架构

编译：mvn eclipse:eclipse -Dwtpversion=1.0


功能：

1.校验拦截器:/duobao-api/src/main/java/com/aibinong/api/web/filter/ValidationFilter.java

2.注解事务处理:/duobao-api/src/main/java/com/aibinong/api/service/user/impl/UserServiceImpl.java

3.超时拦截:/duobao-api/src/main/java/com/aibinong/api/web/interceptor/TimeoutInterceptor.java

4.RedisDao:/duobao-api/src/main/java/com/aibinong/api/dao/RedisDao.java

5.FreeMarker:/duobao-api/src/main/java/com/aibinong/api/util/TemplateUtil.java

6.防SQL注入的durid数据库连接池

7.阿里云ONS MQ Producer工具类



请求测试:
http://localhost:8080/duobao-api/api/user/get_users?sign=94ca8e228c55edd4780839bd29f2cf61&access_token=abcde