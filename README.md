

# Spring Scheduled Task

### 开启 Scheduler

开启Spring的Scheduler非常简单，一个注解`@EnableScheduling`即可：

```java
@Configuration
@EnableScheduling
public class SchedulingConfig {
}
```

如果是Springboot应用，则直接在启动类上面加上`@EnableScheduling`就可以使用了。



### 使用 Scheduler

注解`@Scheduled`只能用于满足下面两个条件的方法上：

（1）没有返回类型，或者说返回类型为`void`；

（2）没有参数；





# Quartz

### 简介

Quartz是OpenSymphony开源组织在Job scheduling领域又一个开源项目。

Quartz 是一个完全由 Java 编写的开源作业调度框架，为在 Java 应用程序中进行作业调度提供了简单却强大的机制。

Quartz 可以与[ J2EE](https://www.w3cschool.cn/java_interview_question/java_interview_question-wvr326ra.html) 与 J2SE 应用程序相结合也可以单独使用。

Quartz 允许程序开发人员根据时间的间隔来调度作业。

Quartz 实现了作业和触发器的多对多的关系，还能把多个作业与不同的触发器关联。



###  **核心**

#### **1）任务 Job**

我们想要调度的任务都必须实现 **org.quartz.job** 接口，然后实现接口中定义的 **execute( )** 方法即可



#### 2）JobDetail 

**JobDetail** 表示一个具体的可执行的调度程序，Job 是这个可执行程调度程序所要执行的内容，另外 **JobDetail**还包含了这个任务调度的方案和策略。



#### **2）触发器 Trigger**

**Trigger** 作为执行任务的调度器。我们如果想要凌晨1点执行备份数据的任务，那么 **Trigger** 就会设置凌晨1点执行该任务。其中 **Trigger** 又分为 **SimpleTrigger** 和 **CronTrigger** 两种



#### **3）调度器 Scheduler**

**Scheduler** 为任务的调度器，它会将任务 **Job** 及触发器 **Trigger** 整合起来，负责基于 **Trigger** 设定的时间来执行 **Job**



### 入门



[参考](https://zhuanlan.zhihu.com/p/306591082)



#### 引入依赖

```xml
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
</dependency>
```



#### 自定义任务 (Job)

```java
@Service
public class QuartzJobService implements IQuartzJobService, Job {
    @Autowired
    private ProducerFeignService producerFeignService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Boolean result = producerFeignService.incrementInventory(2);
        System.out.println("Quartz:" + result);
    }
}
```



#### 创建任务调度 (Scheduler = Job + Trigger)

```java
@Component
public class QuartzApplicationRunner implements ApplicationRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startJob();
    }

    public void startJob() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(QuartzJobService.class)
                .withIdentity("testJob", "testJobGroup")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testTrigger", "testTriggerGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        // 使用触发器调度任务的执行
        scheduler.scheduleJob(job, trigger);
        // 开启任务
        scheduler.start();
    }
}
```





# xxl-job

[分布式任务调度平台XXL-JOB (xuxueli.com)](https://www.xuxueli.com/xxl-job/#4.1 配置执行器)



## 简介

XXL-JOB是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用。



## 运行调度中心项目

### 下载

https://github.com/xuxueli/xxl-job/releases



### 初始化表

/xxl-job/doc/db/tables_xxl_job.sql



### 修改配置

修改数据源

/xxl-job/xxl-job-admin/src/main/resources/application.properties



启动 **xxl-job-admin** 模块



### 调度中心访问

调度中心访问地址：http://localhost:8080/xxl-job-admin (该地址执行器将会使用到，作为回调地址)

默认登录账号 “admin/123456”



## 在项目中配置 xxl-job

### 引入依赖

```xml
<dependency>
    <groupId>com.xuxueli</groupId>
    <artifactId>xxl-job-core</artifactId>
    <version>2.4.0</version>
</dependency>
```



### 项目配置

```yaml
### 调度中心部署根地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调"；为空则关闭自动注册；
xxl:
  job:
    admin:
      addresses: http://127.0.0.1:8080/xxl-job-admin
    ### 执行器通讯TOKEN [选填]：非空时启用；
    accessToken: 
    ### 执行器AppName [选填]：执行器心跳注册分组依据；为空则关闭自动注册
    executor:
      appname: xxl-job-executor-test
      ### 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 ”IP:PORT“ 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。
      address:
      ### 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"；
      ip:
      ### 执行器端口号 [选填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；
      port: 9999
      ### 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径； /data/applogs/xxl-job/jobhandler
      logpath:
      ### 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；
      logretentiondays: 30
```



```java
@Data
@Component
public class XxlJobConfigProperties {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.accessToken}")
    private String accessToken;
    @Value("${xxl.job.executor.appname}")
    private String appname;
    @Value("${xxl.job.executor.address}")
    private String address;
    @Value("${xxl.job.executor.ip}")
    private String ip;
    @Value("${xxl.job.executor.port}")
    private Integer port;
    @Value("${xxl.job.executor.logpath}")
    private String logPath;
    @Value("${xxl.job.executor.logretentiondays}")
    private Integer logRetentionDays;
}
```



```java
@Configuration
@Slf4j
public class XxlJobConfig {

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobConfigProperties properties) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(properties.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(properties.getAppname());
        xxlJobSpringExecutor.setIp(properties.getIp());
        xxlJobSpringExecutor.setPort(properties.getPort());
        xxlJobSpringExecutor.setAccessToken(properties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(properties.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(properties.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
```



## 在调度中心配置定时调度任务



### 配置执行器

|          |                                                       |
| -------- | ----------------------------------------------------- |
| AppName  | xxl-job-executor-test                                 |
| 名称     | 新建执行器                                            |
| 注册方式 | 手动录入                                              |
| 机器地址 | http://192.168.31.246:9998,http://192.168.31.246:9999 |



### 配置调度任务

