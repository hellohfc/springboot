,

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/248939fecae9546677429500cd0c45fb.jpg?raw=true)

\<HETF\>

基于HAP框架的cxf+spring接口实现与调用

Author: 胡馥春

Creation Date: 2017年10月31日

Last Updated: 2017年10月31日

Document Ref: 基于hap框架的cxf+spring接口实现与调用

Version: 1.0

1.  **Title, Subject, Last Updated Date, Reference Number**, **andVersion** are
    marked by a Word Bookmark so that they can be easily reproduced in the
    header and footer of documents. When you change any of these values, be
    careful not to accidentally delete the bookmark. **You can make bookmarks
    visible by selecting Tools-\>Options…View and checking the Bookmarks option
    in the Show region.**

2.  To add additional approval lines, press [Tab] from the last cell in the
    table above.

3.  You can delete any elements of this cover page that you do not need for your
    document. For example, Copy Number is only required if this is a controlled
    document and you need to track each copy that you distribute.

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/0901c56eb9f4fdfa9be80cd2e21346af.jpg?raw=true)

文档控制
--------

**更改记录**

| 日期       | 作者 | 版本 | 更改参考 |
|------------|------|------|----------|
|            |      |      |          |
| 2017-09-16 |      | 1.0  | 无前版本 |
|            |      |      |          |
|            |      |      |          |
|            |      |      |          |

内容目录

>   [文档控制 ii](#文档控制)

>   [1. 文档说明 2](#文档说明)

>   [1.1. 适用框架及技术 2](#适用框架及技术)

>   [1.1.1. 适用框架 2](#适用框架)

>   [2. cxf的基础知识 3](#cxf的基础知识)

>   [2.1. 什么是cxf 3](#什么是cxf)

>   [3.搭建基于hap框架的cxf接口 4](#搭建基于hap框架的cxf接口)

>   [3.1新建service和对应的实现类 4](#新建service和对应的实现类)

>   [1． 定义selectUser接口 5](#定义selectuser接口)

>   [2. 定义service的实现类 5](#定义service的实现类)

>   [3.2 hap中cxf配置详解 6](#hap中cxf配置详解)

>   [1. web.xml中的CXFServlet配置 6](#web.xml中的cxfservlet配置)

>   [2. cxfSecurity.xml中的配置 7](#cxfsecurity.xml中的配置)

>   [3. cxf-beans-demo.xml的配置 7](#cxf-beans-demo.xml的配置)

>   [4. ws security中回调函数的实现 7](#ws-security中回调函数的实现)

>   [3.2 启动项目，发布创建的soap接口 8](#启动项目发布创建的soap接口)

>   [3.3 采用SOAPui调用接口 9](#采用soapui调用接口)

>   [5.未结与已结问 12](#未结与已结问)

>   [未结问题 12](#未结问题)

>   [已结问题 12](#已结问题)

文档说明
--------

>   本文档主要是基于cxf+spring来实现接口的的发布与调用，其中关于接口的调用采用的是SOAPui软件。

>   本章主要介绍： 1.利用soap接口来对单表进行增删改查

>   2.框架中cxfseurity的原理

1.  适用框架及技术

    1.  适用框架

Hap或其他spring框架

1.  cxf的基础知识

    1.  什么是cxf

>   CXF的全称为Celtix+XFire,开始叫Apache CeltiXfire,后来改名为Apache
>   CXF,它包含了大量的功能特性，但主要集中在以下几个方面：

>   1.支持webService标准；cxf支持多种webService标准，包含SOAP、Basic Profile等

>   2.cxf实现了jax-ws
>   api，允许客户端和EndPoint的创建，而不需要Annotation注解，它既支持wsdl优先开发，也支持Java的代码优先开发模式

>   3.cxf拥有大量简单的api用来快速构建代码优先的service，各种maven的插件也更容易集成

>   4.是一种可插拔的架构，既支持xml也可以支持非xml的类型绑定，比如json和corba

3.搭建基于hap框架的cxf接口 
---------------------------

我们可以看到在hap的项目中，有一个demo.ws的文件夹，如下图，框架组在这个文件目录下定义了两个简单的hello
demo和一个基于ws-security标准的安全验证回调函数

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/6d5087943320688029e859b74aaa5ba7.png?raw=true)

### 3.1新建service和对应的实现类

在学习的时候为了和框架的区分开，新建了一个cxftest文件夹，在里面定义我们需要的接口和实现类

#### 定义selectUser接口

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/a88442add1ba960487e47f092a8ba39f.png?raw=true)

注意：

1.  [这里的 \@webservice](mailto:1.这里的@webservice) 注解表示此接口将要提供服务

2.  在service方法中的\@WebParam(name=”xxx”)注解是给传进来的参数一个别名XXX，如果不写将默认采用arg0,arg1的名称

#### 2. 定义service的实现类

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/59e700f0d90ccf808291e224353dcdce.png?raw=true)

注意：

这里的\@WebService注解中的serviceName属性表示对外发布的服务名，指定webService的名称，当不填时默认为jave类的简单名称+service;

EndpointInterface属性表示这个接口需要暴露用来调用的实现方法，因为一个类可以实现多个接口并且在类中也可以定义多个方法，所以这里采用endpointInterface来指定需要实现哪个service中的方法。

### 3.2 hap中cxf配置详解

#### 1. web.xml中的CXFServlet配置

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/b4fb05fa77fbcc14097c4028eb1f7878.png?raw=true)

这里就规定了在该容器中所有的webservice接口的访问地址为/ws

#### 2. cxfSecurity.xml中的配置

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/5df0da43dc16236138d7987ae65f9a93.png?raw=true)

我们可以看到在这个配置文件中，配置了单独的\<http\>元素，并且设置了security=“none”，这样的配置会让spring
security放弃对pattern为ws的所有权限处理，包括登录验证等。

#### 3. cxf-beans-demo.xml的配置

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/7c702eab5f3378cf9d98822467b86198.png?raw=true)

#### 4. ws security中回调函数的实现

根据上面的配置文件，我们可以很容易的找到ws security的位置。

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/a786e0dc40e14f45b7d07fdde71c42dd.png?raw=true)

加了这个拦截器，那么我们在调用这些webservice时就需要在header中传入当前登录系统的用户名和密码不然就无法调用到对应的接口以及报错

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/09fe92f6cfbc4e94d7da292096ef95c1.png?raw=true)

### 3.2 启动项目，发布创建的soap接口 

启动项目成功后，在地址栏输入 localhost：端口号/ws，如下图

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/79591d01e6b9863247a41ff2e5c1509f.png?raw=true)

表格中左边栏表示可以调用的webservice名称和可以调用的方法

右边栏中的endpoint
address表示web服务断点地址，wsdl是一种用来描述网络服务的语言，我们点击进去可以看到。

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/8ce49f93f5bb71929036be2757666e71.png?raw=true)

### 3.3 采用SOAPui调用接口

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/d7b246396cff17545059c09c6531df5d.png?raw=true)

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/2a53d8e9b00c458df4ebd3699269ce04.png?raw=true)

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/095877d1aeb2d3667736fd5f024bb789.png?raw=true)

Soappenv：header的具体配置：（这里根据ws
seurity回调函数中的定义，指定了用户名和密码为admin,将这个配置加在soap:body之前即可调用框架中定义的接口）

注意：

在使用soapui进行调用接口时，如果传入的参数是个list集合，只需要在左边的request1中传多个相同名称的参数即可。如图：

![](https://github.com/hellohfc/springboot/blob/master/%E5%9B%BE%E7%89%87/media/61bfd671ca98345991561efb032f121c.png?raw=true)

5.未结与已结问
--------------

### 未结问题

| 序号 | 问题 | 解决方案 | 负责人 | 目标日期 | 实际日期 |
|------|------|----------|--------|----------|----------|
|      |      |          |        |          |          |
|      |      |          |        |          |          |
|      |      |          |        |          |          |
|      |      |          |        |          |          |
|      |      |          |        |          |          |

### 已结问题

| 序号 | 问题 | 解决方案 | 负责人 | 目标日期 | 实际日期 |
|------|------|----------|--------|----------|----------|
|      |      |          |        |          |          |
|      |      |          |        |          |          |
|      |      |          |        |          |          |
|      |      |          |        |          |          |
|      |      |          |        |          |          |
