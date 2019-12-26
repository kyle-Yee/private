Tax Bank Platform
税银平台

用于税银贷项目的项目软件框架。
开发环境要求：JDK 8 + Apache Maven 3.3.3 + Eclipse Java EE IDE for Web Developers. Version: Mars.2 Release (4.5.2) + apache-tomcat-8.0.35。
项目使用主流技术包括：服务端 —— Spring4.1.6.RELEASE + SpringMVC4.1.6.RELEASE + Mybatis3.3.0 + Shiro1.2.4 + druid1.0.14 + ehcache2.6.11 等；前端 —— JQuery + Bootstrap3.3.5 + ACE1.3.4（基于bootstrap的响应式后台管理模板） + layer + JQuery validation 等。

作者：张孟志
日期：2016-06-30
Email：zhangmengzhi2005@126.com

V2.0 张孟志 2016-07-29
1、bug fixed;
2、修改项目为Maven管理，集成JUnit;

V1.2 张孟志 2016-07-27
1、bug fixed;
2、集成文本编辑器ueditor。在“理财产品”中演示使用;
3、集成 Apache CXF 。实现一个传统 WebService com.dcits.govsbu.sd.taxbankplatform.webservice.TraditionalSOAPWebService ， com.dcits.govsbu.sd.taxbankplatform.util.WebServiceClientUtil 测试使用。

V1.1 张孟志 2016-07-11
1、bug fixed;
2、完成数据导出功能，支持excel,csv,pdf,txt四种文件格式。在“用户登录信息”中演示使用；
3、重构邮件发送功能;
4、完成客户端（用户/浏览器）真实IP获取。

V1.0 张孟志 2016-06-30
1、基础架构来源于开源项目webside（https://git.oschina.net/wjggwm/webside）；
2、根据SAAS重构权限管理；
3、增加机构、个人权限分配机制；
4、实现数据隔离方案；
5、bug fixed;
6、进一步优化开发框架，引入MyBatis_Generator减少开发人员工作量；
7、引入guava-18.0.jar，实现工具类增强线程处理能力；
8、增加批处理功能，实现一个日志清理批处理；
9、实现纳税人注册。同时在“用户协议”中演示使用layer。
