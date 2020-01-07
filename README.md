### 1、首先创建一个springboot项目
##### 在`pom.xml`中导入`p6spy`
```xml
        <!--    将p6spy导入    -->
        <dependency>
            <groupId>p6spy</groupId>
            <artifactId>p6spy</artifactId>
            <version>3.8.6</version>
        </dependency>

        <!-- 连接mysql -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.0</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
```

##### 在`application.properties`中
```properties
#mybatis config
#spring.datasource.driver-class-name=com.mysql.jdbc.Driver
#spring.datasource.url=jdbc:mysql://localhost:3306/db?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=true
#更换为com.p6spy.engine.spy.P6SpyDriver
spring.datasource.driver-class-name=com.p6spy.engine.spy.P6SpyDriver
spring.datasource.url=jdbc:p6spy:mysql://localhost:3306/data?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=true
spring.datasource.username=root
spring.datasource.password=123456
```
##### 在和`application.properties`同目录下创建一个`spy.properties`配置文件
![](https://upload-images.jianshu.io/upload_images/7473008-28b02b01d012b998.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```properties
#################################################################
# P6Spy Options File                                            #
# See documentation for detailed instructions                   #
# http://p6spy.github.io/p6spy/2.0/configandusage.html          #
#################################################################

#################################################################
# MODULES                                                       #
#                                                               #
# Module list adapts the modular functionality of P6Spy.        #
# Only modules listed are active.                               #
# (default is com.p6spy.engine.logging.P6LogFactory and         #
# com.p6spy.engine.spy.P6SpyFactory)                            #
# Please note that the core module (P6SpyFactory) can't be      #
# deactivated.                                                  #
# Unlike the other properties, activation of the changes on     #
# this one requires reload.                                     #
#################################################################
#modulelist=com.p6spy.engine.spy.P6SpyFactory,com.p6spy.engine.logging.P6LogFactory,com.p6spy.engine.outage.P6OutageFactory

################################################################
# CORE (P6SPY) PROPERTIES                                      #
################################################################

# A comma separated list of JDBC drivers to load and register.
# (default is empty)
#
# Note: This is normally only needed when using P6Spy in an
# application server environment with a JNDI data source or when
# using a JDBC driver that does not implement the JDBC 4.0 API
# (specifically automatic registration).
driverlist=com.mysql.cj.jdbc.Driver

# for flushing per statement
# (default is false)
autoflush=true

# sets the date format using Java's SimpleDateFormat routine.
# In case property is not set, milliseconds since 1.1.1970 (unix time) is used (default is empty)
dateformat=MM-dd-yy HH:mm:ss:SS

# prints a stack trace for every statement logged
#stacktrace=false
# if stacktrace=true, specifies the stack trace to print
#stacktraceclass=

# determines if property file should be reloaded
# Please note: reload means forgetting all the previously set
# settings (even those set during runtime - via JMX)
# and starting with the clean table
# (default is false)
#reloadproperties=false

# determines how often should be reloaded in seconds
# (default is 60)
#reloadpropertiesinterval=60

# specifies the appender to use for logging
# Please note: reload means forgetting all the previously set
# settings (even those set during runtime - via JMX)
# and starting with the clean table
# (only the properties read from the configuration file)
# (default is com.p6spy.engine.spy.appender.FileLogger)
#appender=com.p6spy.engine.spy.appender.Slf4JLogger
appender=com.p6spy.engine.spy.appender.StdoutLogger
#appender=com.p6spy.engine.spy.appender.FileLogger

# name of logfile to use, note Windows users should make sure to use forward slashes in their pathname (e:/test/spy.log)
# (used for com.p6spy.engine.spy.appender.FileLogger only)
# (default is spy.log)
logfile=spy.log
#logfile=D:\\springboot.log
# append to the p6spy log file. if this is set to false the
# log file is truncated every time. (file logger only)
# (default is true)
#append=true

# class to use for formatting log messages (default is: com.p6spy.engine.spy.appender.SingleLineFormat)
logMessageFormat=com.p6spy.engine.spy.appender.MultiLineFormat

# 下面是自定义sql log格式
#logMessageFormat=cn.infomany.P6SpyLogger

# Custom log message format used ONLY IF logMessageFormat is set to com.p6spy.engine.spy.appender.CustomLineFormat
# default is %(currentTime)|%(executionTime)|%(category)|connection%(connectionId)|%(sqlSingleLine)
# Available placeholders are:
#   %(connectionId)            the id of the connection
#   %(currentTime)             the current time expressing in milliseconds
#   %(executionTime)           the time in milliseconds that the operation took to complete
#   %(category)                the category of the operation
#   %(effectiveSql)            the SQL statement as submitted to the driver
#   %(effectiveSqlSingleLine)  the SQL statement as submitted to the driver, with all new lines removed
#   %(sql)                     the SQL statement with all bind variables replaced with actual values
#   %(sqlSingleLine)           the SQL statement with all bind variables replaced with actual values, with all new lines removed
#customLogMessageFormat=%(currentTime)|%(executionTime)|%(category)|connection%(connectionId)|%(sqlSingleLine)

# format that is used for logging of the java.util.Date implementations (has to be compatible with java.text.SimpleDateFormat)
# (default is yyyy-MM-dd'T'HH:mm:ss.SSSZ)
#databaseDialectDateFormat=yyyy-MM-dd'T'HH:mm:ss.SSSZ

# format that is used for logging of the java.sql.Timestamp implementations (has to be compatible with java.text.SimpleDateFormat)
# (default is yyyy-MM-dd'T'HH:mm:ss.SSSZ)
#databaseDialectTimestampFormat=yyyy-MM-dd'T'HH:mm:ss.SSSZ

# format that is used for logging booleans, possible values: boolean, numeric
# (default is boolean)
#databaseDialectBooleanFormat=boolean

# whether to expose options via JMX or not
# (default is true)
#jmx=true

# if exposing options via jmx (see option: jmx), what should be the prefix used?
# jmx naming pattern constructed is: com.p6spy(.<jmxPrefix>)?:name=<optionsClassName>
# please note, if there is already such a name in use it would be unregistered first (the last registered wins)
# (default is none)
#jmxPrefix=

# if set to true, the execution time will be measured in nanoseconds as opposed to milliseconds
# (default is false)
#useNanoTime=false

#################################################################
# DataSource replacement                                        #
#                                                               #
# Replace the real DataSource class in your application server  #
# configuration with the name com.p6spy.engine.spy.P6DataSource #
# (that provides also connection pooling and xa support).       #
# then add the JNDI name and class name of the real             #
# DataSource here                                               #
#                                                               #
# Values set in this item cannot be reloaded using the          #
# reloadproperties variable. Once it is loaded, it remains      #
# in memory until the application is restarted.                 #
#                                                               #
#################################################################
#realdatasource=/RealMySqlDS
#realdatasourceclass=com.mysql.jdbc.jdbc2.optional.MysqlDataSource

#################################################################
# DataSource properties                                         #
#                                                               #
# If you are using the DataSource support to intercept calls    #
# to a DataSource that requires properties for proper setup,    #
# define those properties here. Use name value pairs, separate  #
# the name and value with a semicolon, and separate the         #
# pairs with commas.                                            #
#                                                               #
# The example shown here is for mysql                           #
#                                                               #
#################################################################
#realdatasourceproperties=port;3306,serverName;myhost,databaseName;jbossdb,foo;bar

#################################################################
# JNDI DataSource lookup                                        #
#                                                               #
# If you are using the DataSource support outside of an app     #
# server, you will probably need to define the JNDI Context     #
# environment.                                                  #
#                                                               #
# If the P6Spy code will be executing inside an app server then #
# do not use these properties, and the DataSource lookup will   #
# use the naming context defined by the app server.             #
#                                                               #
# The two standard elements of the naming environment are       #
# jndicontextfactory and jndicontextproviderurl. If you need    #
# additional elements, use the jndicontextcustom property.      #
# You can define multiple properties in jndicontextcustom,      #
# in name value pairs. Separate the name and value with a       #
# semicolon, and separate the pairs with commas.                #
#                                                               #
# The example shown here is for a standalone program running on #
# a machine that is also running JBoss, so the JNDI context     #
# is configured for JBoss (3.0.4).                              #
#                                                               #
# (by default all these are empty)                              #
#################################################################
#jndicontextfactory=org.jnp.interfaces.NamingContextFactory
#jndicontextproviderurl=localhost:1099
#jndicontextcustom=java.naming.factory.url.pkgs;org.jboss.naming:org.jnp.interfaces

#jndicontextfactory=com.ibm.websphere.naming.WsnInitialContextFactory
#jndicontextproviderurl=iiop://localhost:900

################################################################
# P6 LOGGING SPECIFIC PROPERTIES                               #
################################################################

# filter what is logged
# please note this is a precondition for usage of: include/exclude/sqlexpression
# (default is false)
#filter=false

# comma separated list of strings to include
# please note that special characters escaping (used in java) has to be done for the provided regular expression
# (default is empty)
#include=
# comma separated list of strings to exclude
# (default is empty)
#exclude=

# sql expression to evaluate if using regex
# please note that special characters escaping (used in java) has to be done for the provided regular expression
# (default is empty)
#sqlexpression=

#list of categories to exclude: error, info, batch, debug, statement,
#commit, rollback, result and resultset are valid values
# (default is info,debug,result,resultset,batch)
#excludecategories=info,debug,result,resultset,batch

#whether the binary values (passed to DB or retrieved ones) should be logged with placeholder: [binary] or not.
# (default is false)
#excludebinary=false

# Execution threshold applies to the standard logging of P6Spy.
# While the standard logging logs out every statement
# regardless of its execution time, this feature puts a time
# condition on that logging. Only statements that have taken
# longer than the time specified (in milliseconds) will be
# logged. This way it is possible to see only statements that
# have exceeded some high water mark.
# This time is reloadable.
#
# executionThreshold=integer time (milliseconds)
# (default is 0)
#executionThreshold=

################################################################
# P6 OUTAGE SPECIFIC PROPERTIES                                #
################################################################
# Outage Detection
#
# This feature detects long-running statements that may be indicative of
# a database outage problem. If this feature is turned on, it will log any
# statement that surpasses the configurable time boundary during its execution.
# When this feature is enabled, no other statements are logged except the long
# running statements. The interval property is the boundary time set in seconds.
# For example, if this is set to 2, then any statement requiring at least 2
# seconds will be logged. Note that the same statement will continue to be logged
# for as long as it executes. So if the interval is set to 2, and the query takes
# 11 seconds, it will be logged 5 times (at the 2, 4, 6, 8, 10 second intervals).
#
# outagedetection=true|false
# outagedetectioninterval=integer time (seconds)
#
# (default is false)
#outagedetection=false
# (default is 60)
#outagedetectioninterval=30
```
>到这里，`p6spy`就可以工作了！

##### 编写相应的Control运行效果如下：
![](https://upload-images.jianshu.io/upload_images/7473008-f29e5e67c86697b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 可以看到默认的log格式可能不能满足需求，我们也可以自定义：
编写类实现com.p6spy.engine.spy.appender.MessageFormattingStrategy接口，重写formatMessage方法：
![](https://upload-images.jianshu.io/upload_images/7473008-e0493fb0003db993.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```java
import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;

/**
 * now + "|" + elapsed + "|" + category + "|connection " + connectionId + "|url " + url + "|" + P6Util.singleLine(prepared) + "|" + P6Util.singleLine(sql);
 * #01-07-20 14:37:38:871 | took 1ms | statement | connection 0| url jdbc:p6spy:mysql://localhost:3306/data?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=true
 */
public class P6SpyLogger implements MessageFormattingStrategy {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("=====================================================\n")
                .append("连接id：").append(connectionId).append("\n")
                .append("当前时间：").append(now).append("\n")
                .append("类别：").append(category).append("\n")
                .append("花费时间：").append(elapsed).append("\n")
//                .append("url：").append(url).append("\n")
                .append("预编译sql：").append(P6Util.singleLine(prepared)).append("\n")
                .append("最终执行的sql：").append(P6Util.singleLine(sql))
                .append("\n=====================================================\n");
        return sb.toString();
    }
}
```

---
##### 在`spy.properties`中将`logMessageFormat`改成你编写实现`MessageFormattingStrategy`接口类的全类名
`logMessageFormat=《你编写log格式类全类名》`

---


最后运行效果如下：
---
![](https://upload-images.jianshu.io/upload_images/7473008-0c585fa25b79e9c6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

